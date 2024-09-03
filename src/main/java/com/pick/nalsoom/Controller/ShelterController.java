package com.pick.nalsoom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pick.nalsoom.Domain.Shelter.Shelter;
import com.pick.nalsoom.Service.Shelter.ShelterService;

@RestController
@RequestMapping("/api/v1/shelter")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @GetMapping("/getShelterData")
    public void getShelterData() {
        shelterService.getCoolingCentreData();
        shelterService.getHeatingCentreData();
        shelterService.getFinedustShelterData();
    }
    
    @GetMapping("/getBoardData")
    public ResponseEntity<List<Shelter>> getBoardData() {
        return ResponseEntity.ok(shelterService.getBoardData());
    }
    
}
