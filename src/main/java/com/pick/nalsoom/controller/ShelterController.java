package com.pick.nalsoom.controller;

import com.pick.nalsoom.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shelter")
@RequiredArgsConstructor
public class ShelterController {

    private final ShelterService shelterService;

//    @GetMapping
//    public void updateShelter() {
//        shelterService.updateSheltersData();
//    }
}
