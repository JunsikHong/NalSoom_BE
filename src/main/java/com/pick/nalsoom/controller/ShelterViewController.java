package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.ShelterViewDto;
import com.pick.nalsoom.service.ShelterViewService;
import com.pick.nalsoom.utils.NoSuchShelterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board/shelter")
@RequiredArgsConstructor
public class ShelterViewController {

    private final ShelterViewService shelterViewService;

    @GetMapping
    public ResponseEntity<List<ShelterViewDto>> getShelterData(
            @RequestParam(name = "searchShelterType", defaultValue = "normal") String searchShelterType,
            @RequestParam(name = "searchSortBy", defaultValue = "good") String searchSortBy,
            @RequestParam(name = "shelterProperNum", required = false) List<Long> shelterProperNum,
            @RequestParam(name = "searchPaging", defaultValue = "0") int searchPaging,
            @RequestParam(name = "searchSize", defaultValue = "10") int searchSize
    ) {
        //검색 결과
        List<ShelterViewDto> shelterViewDtoList = null;

        //대피소 타입 예외 처리
        if (!(searchShelterType.equals("normal") || searchShelterType.equals("TbGtnHwcwP") || searchShelterType.equals("TbGtnCwP") || searchShelterType.equals("shuntPlace"))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        //정렬 타입 예외 처리
        if (!(searchSortBy.equals("good") || searchSortBy.equals("review") || searchSortBy.equals("distance"))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        //페이지 예외 처리
        if (searchPaging < 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        //정렬 타입이 distance 인데 대피소 데이터가 없을 때
        if(searchSortBy.equals("distance") && shelterProperNum != null && shelterProperNum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        try {
            if (shelterProperNum != null && !shelterProperNum.isEmpty()) { //정렬 타입이 distance 일 때
                shelterViewDtoList = shelterViewService.getShelterData(searchShelterType, searchSortBy, shelterProperNum, searchPaging, searchSize);
            } else { //정렬 타입이 good, review 일 때
                shelterViewDtoList = shelterViewService.getShelterData(searchShelterType, searchSortBy, searchPaging, searchSize);
            }
        } catch(NoSuchShelterException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok().body(shelterViewDtoList);
    }

}

