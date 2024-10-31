package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.ShelterViewDto;
import com.pick.nalsoom.service.ShelterBoardService;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board/shelter")
public class ShelterBoardController {

    private final ShelterBoardService shelterBoardService;

    @Autowired
    public ShelterBoardController(ShelterBoardService shelterBoardService) {
        this.shelterBoardService = shelterBoardService;
    }

    @GetMapping
    public ResponseEntity<List<ShelterViewDto>> getShelterData(
            @RequestParam(name = "searchShelterType", defaultValue = "normal") String searchShelterType,
            @RequestParam(name = "searchSortBy", defaultValue = "good") String searchSortBy,
            @RequestParam(name = "searchSortDirection", defaultValue = "desc") String searchSortDirection,
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

        //정렬 방법 예외 처리
        if (!(searchSortDirection.equals("asc") || searchSortDirection.equals("desc"))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        //페이지 예외 처리
        if (searchPaging < 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        try {
            //정렬 타입이 distance 일 때 다른 비즈니스 로직 호출
            if (searchSortBy.equals("distance")) {
                shelterViewDtoList = shelterBoardService.getInBoundShelterData(searchShelterType, searchSortBy, searchSortDirection, shelterProperNum, searchPaging, searchSize);
            } else {
                shelterViewDtoList = shelterBoardService.getShelterData(searchShelterType, searchSortBy, searchSortDirection, searchPaging, searchSize);
            }
        } catch(NoSuchShelterException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok().body(shelterViewDtoList);
    }

}
