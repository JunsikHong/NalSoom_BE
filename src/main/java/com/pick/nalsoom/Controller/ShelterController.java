package com.pick.nalsoom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
