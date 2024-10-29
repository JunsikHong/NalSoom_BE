package com.pick.nalsoom.Controller;

import com.pick.nalsoom.Dto.Shelter.ShelterViewDto;
import com.pick.nalsoom.Service.Shelter.ShelterBoardService;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board/shelter")
public class ShelterBoardController {

    @Autowired
    private ShelterBoardService shelterBoardService;

    @GetMapping
    public ResponseEntity<List<ShelterViewDto>> getSheltersData() {
        List<ShelterViewDto> shelterViewDtoList = null;
        try {
            shelterViewDtoList = shelterBoardService.getSheltersData();
        } catch (NoSuchShelterException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().body(shelterViewDtoList);
    }

    @GetMapping("/boundIn")
    public ResponseEntity<List<ShelterViewDto>> getShelterData(
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
