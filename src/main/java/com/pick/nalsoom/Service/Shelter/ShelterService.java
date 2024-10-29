package com.pick.nalsoom.Service.Shelter;

import com.pick.nalsoom.Domain.Shelter.Shelter;
import com.pick.nalsoom.Dto.Shelter.CoolingCentre.CoolingCentreDto;
import com.pick.nalsoom.Dto.Shelter.CoolingCentre.RowDto;
import com.pick.nalsoom.Dto.Shelter.FinedustShelter.ShuntPlaceDto;
import com.pick.nalsoom.Dto.Shelter.HeatingCentre.TbGtnCwPDto;
import com.pick.nalsoom.Repository.Shelter.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service("shelterService")
public class ShelterService {

    @Value("${seoul-data-key}")
    private String seoulDataKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ShelterRepository shelterRepository;

    //seoul api 의 shelter api 는 매 월 말일에 업데이트 됨 -> 매 월 1일에 Shelter data 업데이트
    @Scheduled(cron = "0 0 0 1 * ? *")
    public void updateSheltersData () {

        //api response result
        List<com.pick.nalsoom.Dto.Shelter.CoolingCentre.RowDto> coolingCentreData = null;
        List<com.pick.nalsoom.Dto.Shelter.HeatingCentre.RowDto> heatingCentreData = null;
        List<com.pick.nalsoom.Dto.Shelter.FinedustShelter.RowDto> fineDustShelterData = null;

        //api request & response
        coolingCentreData = requestCoolingCentreAPI();
        heatingCentreData = requestHeatingCentreAPI();
        fineDustShelterData = requestFineDustShelterAPI();

        //response dto -> shelter domain list
        List<Shelter> shelterList = new ArrayList<>();

        coolingCentreData.stream().map(coolingCentre -> {
            shelterList.add(Shelter.builder()
                    .shelterSn(coolingCentre.getRSeqNo())
                    .shelterType("TbGtnHwcwP")
                    .build());
            return true;
        });

        heatingCentreData.stream().map(heatingCentre -> {
            shelterList.add(Shelter.builder()
                    .shelterSn(heatingCentre.getRSeqNo())
                    .shelterType("TbGtnCwP")
                    .build());
            return true;
        });

        fineDustShelterData.stream().map(fineDustShelter -> {
            shelterList.add(Shelter.builder()
                    .shelterSn(fineDustShelter.getSn())
                    .shelterType("shuntPlace")
                    .build());
            return true;
        });

        //shelter repository truncate
        shelterRepository.deleteAll();

        //shelter domain -in-> shelter repository
        shelterRepository.saveAll(shelterList);

    }

    //cooling centre api request & response
    public List<com.pick.nalsoom.Dto.Shelter.CoolingCentre.RowDto> requestCoolingCentreAPI () {

        //api response result
        List<com.pick.nalsoom.Dto.Shelter.CoolingCentre.RowDto> coolingCentreData = new ArrayList<>();

        //api request condition
        restTemplate = new RestTemplate();
        String baseUrl = "http://openapi.seoul.go.kr:8088/";
        int pageStartNum = 1;
        int pageEndNum = 1000;
        int listEndNum = 0;

        //temp api request uri
        URI tempUri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(seoulDataKey + "/" + "JSON" + "/" + "TbGtnHwcwP" + "/" + 1 + "/" + 1 + "/")
                .encode()
                .build()
                .toUri();

        //temp api response
        ResponseEntity<CoolingCentreDto> tempApiResult = restTemplate.getForEntity(tempUri, CoolingCentreDto.class);

        //null 체크
        if(tempApiResult.getBody() == null) {
            return null;
        }

        //응답 결과 코드 검사
        if(!tempApiResult.getBody().getTbGtnHwcwP().getResult().getCode().equals("INFO-000")) {
            return null;
        }

        //리스트 마지막 숫자
        listEndNum = tempApiResult.getBody().getTbGtnHwcwP().getListTotalCount();

        while(true) {

            //페이지 마지막 숫자가 리스트 마지막 숫자까지만 요청하도록
            if(pageEndNum > listEndNum) {
                pageEndNum = listEndNum;
            }

            //api request uri
            URI uri = UriComponentsBuilder
                    .fromUriString(baseUrl)
                    .path(seoulDataKey + "/" + "JSON" + "/" + "TbGtnHwcwP" + "/" + pageStartNum + "/" + pageEndNum + "/")
                    .encode()
                    .build()
                    .toUri();

            //api response
            ResponseEntity<CoolingCentreDto> apiResult = restTemplate.getForEntity(uri, CoolingCentreDto.class);

            //응답 결과 코드 검사
            if(!apiResult.getBody().getTbGtnHwcwP().getResult().getCode().equals("INFO-000")) {
                return null;
            }

            //응답 내용 검사
            if (apiResult.getBody().getTbGtnHwcwP().getRow().isEmpty()) {
                return null;
            }
            //응답 누적
            coolingCentreData.addAll(apiResult.getBody().getTbGtnHwcwP().getRow());

            //마지막 요청까지 모두 응답 누적했다면 break
            if(pageEndNum == listEndNum) {
                break;
            }

            //페이지 증가
            pageStartNum += 1000;
            pageEndNum += 1000;

        }

        return coolingCentreData;
    }

    //heating centre api request & response
    public List<com.pick.nalsoom.Dto.Shelter.HeatingCentre.RowDto> requestHeatingCentreAPI () {
        //api response result
        List<com.pick.nalsoom.Dto.Shelter.HeatingCentre.RowDto> heatingCentreData = new ArrayList<>();

        //api request condition
        restTemplate = new RestTemplate();
        String baseUrl = "http://openapi.seoul.go.kr:8088/";
        int pageStartNum = 1;
        int pageEndNum = 1000;
        int listEndNum = 0;

        //temp api request uri
        URI tempUri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(seoulDataKey + "/" + "JSON" + "/" + "TbGtnCwP" + "/" + 1 + "/" + 2 + "/")
                .encode()
                .build()
                .toUri();

        //temp api response
        ResponseEntity<TbGtnCwPDto> tempApiResult = restTemplate.getForEntity(tempUri, TbGtnCwPDto.class);

        //null 체크
        if(tempApiResult.getBody() == null) {
            return null;
        }

        //응답 결과 코드 검사
        if(!tempApiResult.getBody().getResult().getCode().equals("INFO-000")) {
            return null;
        }

        //리스트 마지막 숫자
        listEndNum = tempApiResult.getBody().getListTotalCount();

        while(true) {

            //페이지 마지막 숫자가 리스트 마지막 숫자까지만 요청하도록
            if(pageEndNum > listEndNum) {
                pageEndNum = listEndNum;
            }

            //api request uri
            URI uri = UriComponentsBuilder
                    .fromUriString(baseUrl)
                    .path(seoulDataKey + "/" + "JSON" + "/" + "TbGtnCwP" + "/" + pageStartNum + "/" + pageEndNum + "/")
                    .encode()
                    .build()
                    .toUri();

            //api response
            ResponseEntity<TbGtnCwPDto> apiResult = restTemplate.getForEntity(uri, TbGtnCwPDto.class);

            //응답 결과 코드 검사
            if(!tempApiResult.getBody().getResult().getCode().equals("INFO-000")) {
                return null;
            }

            //응답 내용 검사
            if (apiResult.getBody().getRow().isEmpty()) {
                return null;
            }

            //응답 누적
            heatingCentreData.addAll(apiResult.getBody().getRow());

            //마지막 요청까지 모두 응답 누적했다면 break
            if(pageEndNum == listEndNum) {
                break;
            }

            //페이지 증가
            pageStartNum += 1000;
            pageEndNum += 1000;
        }

        return heatingCentreData;
    }

    //fine dust shelter api request & response
    public List<com.pick.nalsoom.Dto.Shelter.FinedustShelter.RowDto> requestFineDustShelterAPI() {

        //api response result
        List<com.pick.nalsoom.Dto.Shelter.FinedustShelter.RowDto> fineDustShelterData = new ArrayList<>();

        //api request condition
        restTemplate = new RestTemplate();
        String baseUrl = "http://openapi.seoul.go.kr:8088/";
        int pageStartNum = 1;
        int pageEndNum = 1000;
        int listEndNum = 0;

        //temp api request uri
        URI tempUri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(seoulDataKey + "/" + "JSON" + "/" + "shuntPlace" + "/" + 1 + "/" + 2 + "/")
                .encode()
                .build()
                .toUri();

        //temp api response
        ResponseEntity<ShuntPlaceDto> tempApiResult = restTemplate.getForEntity(tempUri, ShuntPlaceDto.class);

        //null 체크
        if(tempApiResult.getBody() == null) {
            return null;
        }

        //응답 결과 코드 검사
        if(!tempApiResult.getBody().getResult().getCode().equals("INFO-000")) {
            return null;
        }

        //리스트 마지막 숫자
        listEndNum = tempApiResult.getBody().getListTotalCount();

        while(true) {

            //페이지 마지막 숫자가 리스트 마지막 숫자까지만 요청하도록
            if(pageEndNum > listEndNum) {
                pageEndNum = listEndNum;
            }

            //api request uri
            URI uri = UriComponentsBuilder
                    .fromUriString(baseUrl)
                    .path(seoulDataKey + "/" + "JSON" + "/" + "shuntPlace" + "/" + pageStartNum + "/" + pageEndNum + "/")
                    .encode()
                    .build()
                    .toUri();

            //api response
            ResponseEntity<ShuntPlaceDto> apiResult = restTemplate.getForEntity(uri, ShuntPlaceDto.class);

            //응답 결과 코드 검사
            if(!tempApiResult.getBody().getResult().getCode().equals("INFO-000")) {
                return null;
            }

            //응답 내용 검사
            if (apiResult.getBody().getRow().isEmpty()) {
                return null;
            }

            //응답 누적
            fineDustShelterData.addAll(apiResult.getBody().getRow());

            //마지막 요청까지 모두 응답 누적했다면 break
            if(pageEndNum == listEndNum) {
                break;
            }

            //페이지 증가
            pageStartNum += 1000;
            pageEndNum += 1000;
        }

        return fineDustShelterData;
    }
}
