package com.pick.nalsoom.Service.Shelter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pick.nalsoom.Domain.Shelter.Shelter;
import com.pick.nalsoom.Dto.Shelter.ShelterDto;
import com.pick.nalsoom.Repository.Shelter.ShelterRepository;

@Service("shelterService")
public class ShelterService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ShelterRepository shelterRepository;

    @Value("${seoul-data-key}")
    private String seoulDataKey;

    // 한파쉼터 데이터 얻기
    public void getHeatingCentreData() {
        ArrayList<Shelter> heatingCentreList = new ArrayList<>();

        for (int num = 0; num < 3; num++) {
            int pageStartNum = 1;
            int pageEndNum = 1000;
            URI url = URI.create("http://openapi.seoul.go.kr:8088/" + seoulDataKey + "/JSON/TbGtnHwcwP/" + pageStartNum + "/" + pageEndNum + "/");
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            try {
                JSONParser jsonParser = new JSONParser();

                // response -> TbGtnHwcwP
                JSONObject responseParse = (JSONObject) jsonParser.parse(response.getBody().toString());
                JSONObject TbGtnHwcwP = (JSONObject) responseParse.get("TbGtnHwcwP");

                // TbGtnHwcwP -> row
                JSONObject TbGtnHwcwPParse = (JSONObject) jsonParser.parse(TbGtnHwcwP.toString());
                JSONArray row = (JSONArray) TbGtnHwcwPParse.get("row");

                // row -> rowList
                JSONArray rowList = (JSONArray) jsonParser.parse(row.toString());

                // rowList -> rowParse -> rowData -> AREA_CDList
                for (int i = 0; i < rowList.size(); i++) {
                    JSONObject rowParse = (JSONObject) rowList.get(i);
                    JSONObject rowData = (JSONObject) jsonParser.parse(rowParse.toString());
                    ShelterDto shelterDto = new ShelterDto();
                    shelterDto.setShelterType("heatingCentre");
                    shelterDto.setAreaCD(rowData.get("AREA_CD").toString());
                    heatingCentreList.add(shelterDto.toEntity());
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            pageStartNum += 1000;
            pageEndNum += 1000;
        }

        shelterRepository.saveAll(heatingCentreList);
    }

    // 무더위쉼터 데이터 얻기
    public void getCoolingCentreData() {
        ArrayList<Shelter> coolingCentreList = new ArrayList<>();

        for (int num = 0; num < 3; num++) {
            int pageStartNum = 1;
            int pageEndNum = 1000;
            URI url = URI.create("http://openapi.seoul.go.kr:8088/" + seoulDataKey + "/JSON/TbGtnCwP/" + pageStartNum + "/" + pageEndNum + "/");
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            try {
                JSONParser jsonParser = new JSONParser();

                // response -> TbGtnHwcwP
                JSONObject responseParse = (JSONObject) jsonParser.parse(response.getBody().toString());
                JSONObject TbGtnCwP = (JSONObject) responseParse.get("TbGtnCwP");

                // TbGtnHwcwP -> row
                JSONObject TbGtnCwPParse = (JSONObject) jsonParser.parse(TbGtnCwP.toString());
                JSONArray row = (JSONArray) TbGtnCwPParse.get("row");

                // row -> rowList
                JSONArray rowList = (JSONArray) jsonParser.parse(row.toString());

                // rowList -> rowParse -> rowData -> AREA_CDList
                for (int i = 0; i < rowList.size(); i++) {
                    JSONObject rowParse = (JSONObject) rowList.get(i);
                    JSONObject rowData = (JSONObject) jsonParser.parse(rowParse.toString());
                    ShelterDto shelterDto = new ShelterDto();
                    shelterDto.setShelterType("coolingCentre");
                    shelterDto.setAreaCD(rowData.get("AREA_CD").toString());
                    coolingCentreList.add(shelterDto.toEntity());
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            pageStartNum += 1000;
            pageEndNum += 1000;
        }

        shelterRepository.saveAll(coolingCentreList);
    }

    // 미세먼지쉼터 데이터 얻기
    public void getFinedustShelterData() {
        ArrayList<Shelter> finedustShelterList = new ArrayList<>();

        int pageStartNum = 1;
        int pageEndNum = 155;
        URI url = URI.create("http://openapi.seoul.go.kr:8088/" + seoulDataKey + "/JSON/shuntPlace/" + pageStartNum + "/" + pageEndNum + "/");
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        try {
            JSONParser jsonParser = new JSONParser();

            // response -> TbGtnHwcwP
            JSONObject responseParse = (JSONObject) jsonParser.parse(response.getBody().toString());
            JSONObject shuntPlace = (JSONObject) responseParse.get("shuntPlace");

            // TbGtnHwcwP -> row
            JSONObject shuntPlaceParse = (JSONObject) jsonParser.parse(shuntPlace.toString());
            JSONArray row = (JSONArray) shuntPlaceParse.get("row");

            // row -> rowList
            JSONArray rowList = (JSONArray) jsonParser.parse(row.toString());

            // rowList -> rowParse -> rowData -> AREA_CDList
            for (int i = 0; i < rowList.size(); i++) {
                JSONObject rowParse = (JSONObject) rowList.get(i);
                JSONObject rowData = (JSONObject) jsonParser.parse(rowParse.toString());
                ShelterDto shelterDto = new ShelterDto();
                shelterDto.setShelterType("finedustShelter");
                shelterDto.setAreaCD(rowData.get("SN").toString());
                finedustShelterList.add(shelterDto.toEntity());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        shelterRepository.saveAll(finedustShelterList);
    }

    // 게시판 정보
    public List<Shelter> getBoardData() {
        return shelterRepository.findTop10ByOrderByShelterProperNumAsc();
    }
}
