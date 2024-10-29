package com.pick.nalsoom.Controller;

import com.pick.nalsoom.Service.Shelter.ShelterBoardService;
import com.pick.nalsoom.Service.Shelter.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    //temp
    @GetMapping
    public void updateShelter() {
        shelterService.updateSheltersData();
    }
}
