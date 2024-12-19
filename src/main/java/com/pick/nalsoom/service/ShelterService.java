package com.pick.nalsoom.service;

import com.pick.nalsoom.domain.Shelter;
import com.pick.nalsoom.dto.ShelterApi.CoolingCentre.CoolingCentreDto;
import com.pick.nalsoom.dto.ShelterApi.FinedustShelter.FineDustShelterDto;
import com.pick.nalsoom.dto.ShelterApi.HeatingCentre.HeatingCentreDto;
import com.pick.nalsoom.repository.ShelterRepository;
import com.pick.nalsoom.utils.NoSuchShelterException;
import lombok.RequiredArgsConstructor;
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
import java.util.Optional;

@Service("shelterService")
@RequiredArgsConstructor
public class ShelterService {

    @Value("${seoul-data-key}")
    private String seoulDataKey;

    private final ShelterRepository shelterRepository;
    private final RestTemplate restTemplate;

    //shelter find
    public Optional<Shelter> findOneShelter (Long shelterProperNum) {
        return shelterRepository.findById(shelterProperNum);
    }

    //seoul api 의 shelter api 는 매 월 말일에 업데이트 됨 -> 매 월 1일에 Shelter data 업데이트
    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateSheltersData () {
        //final result
        List<Shelter> shelterList = new ArrayList<>();

        //api response result
        List<com.pick.nalsoom.dto.ShelterApi.CoolingCentre.RowDto> coolingCentreData = null;
        List<com.pick.nalsoom.dto.ShelterApi.HeatingCentre.RowDto> heatingCentreData = null;
        List<com.pick.nalsoom.dto.ShelterApi.FinedustShelter.RowDto> fineDustShelterData = null;

        //api request & response
        coolingCentreData = requestCoolingCentreAPI();
        heatingCentreData = requestHeatingCentreAPI();
        fineDustShelterData = requestFineDustShelterAPI();

        //response dto -> shelter domain list
        for (com.pick.nalsoom.dto.ShelterApi.CoolingCentre.RowDto row : coolingCentreData) {
            shelterList.add(new Shelter().builder().shelterSn(row.getRestareaNm()).shelterType("TbGtnHwcwP").build());
        }

        int idx = 0;
        for (com.pick.nalsoom.dto.ShelterApi.HeatingCentre.RowDto row : heatingCentreData) {
            idx = row.getSno().indexOf(".");
            shelterList.add(new Shelter().builder().shelterSn(row.getSno().substring(0,idx)).shelterType("TbGtnCwP").build());
        }

        for (com.pick.nalsoom.dto.ShelterApi.FinedustShelter.RowDto row : fineDustShelterData) {
            idx = row.getSno().indexOf(".");
            shelterList.add(new Shelter().builder().shelterSn(row.getSno().substring(0,idx)).shelterType("shuntPlace").build());
        }

        shelterRepository.deleteAll();
        shelterRepository.saveAll(shelterList);

    }

    //cooling centre api request & response
    public List<com.pick.nalsoom.dto.ShelterApi.CoolingCentre.RowDto> requestCoolingCentreAPI () {

        //api response result
        List<com.pick.nalsoom.dto.ShelterApi.CoolingCentre.RowDto> coolingCentreData = new ArrayList<>();

        //api request condition
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
    public List<com.pick.nalsoom.dto.ShelterApi.HeatingCentre.RowDto> requestHeatingCentreAPI () {
        //api response result
        List<com.pick.nalsoom.dto.ShelterApi.HeatingCentre.RowDto> heatingCentreData = new ArrayList<>();

        //api request condition
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
        ResponseEntity<HeatingCentreDto> tempApiResult = restTemplate.getForEntity(tempUri, HeatingCentreDto.class);

        //null 체크
        if(tempApiResult.getBody() == null) {
            return null;
        }

        //응답 결과 코드 검사
        if(!tempApiResult.getBody().getTbGtnCwP().getResult().getCode().equals("INFO-000")) {
            return null;
        }

        //리스트 마지막 숫자
        listEndNum = tempApiResult.getBody().getTbGtnCwP().getListTotalCount();

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
            ResponseEntity<HeatingCentreDto> apiResult = restTemplate.getForEntity(uri, HeatingCentreDto.class);

            //응답 결과 코드 검사
            if(!tempApiResult.getBody().getTbGtnCwP().getResult().getCode().equals("INFO-000")) {
                return null;
            }

            //응답 내용 검사
            if (apiResult.getBody().getTbGtnCwP().getRow().isEmpty()) {
                return null;
            }

            //응답 누적
            heatingCentreData.addAll(apiResult.getBody().getTbGtnCwP().getRow());

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
    public List<com.pick.nalsoom.dto.ShelterApi.FinedustShelter.RowDto> requestFineDustShelterAPI() {

        //api response result
        List<com.pick.nalsoom.dto.ShelterApi.FinedustShelter.RowDto> fineDustShelterData = new ArrayList<>();

        //api request condition
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
        ResponseEntity<FineDustShelterDto> tempApiResult = restTemplate.getForEntity(tempUri, FineDustShelterDto.class);

        //null 체크
        if(tempApiResult.getBody() == null) {
            return null;
        }

        //응답 결과 코드 검사
        if(!tempApiResult.getBody().getShuntPlace().getResult().getCode().equals("INFO-000")) {
            return null;
        }

        //리스트 마지막 숫자
        listEndNum = tempApiResult.getBody().getShuntPlace().getListTotalCount();

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
            ResponseEntity<FineDustShelterDto> apiResult = restTemplate.getForEntity(uri, FineDustShelterDto.class);

            //응답 결과 코드 검사
            if(!tempApiResult.getBody().getShuntPlace().getResult().getCode().equals("INFO-000")) {
                return null;
            }

            //응답 내용 검사
            if (apiResult.getBody().getShuntPlace().getRow().isEmpty()) {
                return null;
            }

            //응답 누적
            fineDustShelterData.addAll(apiResult.getBody().getShuntPlace().getRow());

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
