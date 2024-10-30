package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.ShelterViewDto;
import com.pick.nalsoom.service.ShelterBoardService;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ShelterViewDto>> getShelterData() {
        List<ShelterViewDto> shelterViewDtoList = null;
        try {
            shelterViewDtoList = shelterBoardService.getSheltersData();
        } catch (NoSuchShelterException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().body(shelterViewDtoList);
    }

    @GetMapping("/boundIn")
    public ResponseEntity<List<ShelterViewDto>> getBoundInShelterData (
            @RequestParam("shelterSN") List<String> shelterSN,
            @RequestParam("type") List<String> type) {

        List<ShelterViewDto> shelterViewDtoList = null;

        //요청 인자 확인
        if(shelterSN.isEmpty() && type.isEmpty()) {
            throw new IllegalArgumentException("No Argument Exception");
        }

        try {
            shelterViewDtoList = shelterBoardService.getShelterData(shelterSN, type);
        } catch (NoSuchShelterException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok().body(shelterViewDtoList);

    }

}
