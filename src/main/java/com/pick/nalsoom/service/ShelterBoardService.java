package com.pick.nalsoom.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.pick.nalsoom.domain.ShelterView;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.dto.ShelterViewDto;
import com.pick.nalsoom.repository.ShelterBoardRepository;

@Service("shelterBoardService")
public class ShelterBoardService {

    private final ShelterBoardRepository shelterBoardRepository;
    private final UserService userService;

    @Autowired
    public ShelterBoardService(ShelterBoardRepository shelterBoardRepository, UserService userService) {
        this.shelterBoardRepository = shelterBoardRepository;
        this.userService = userService;
    }

    public List<ShelterViewDto> getSheltersData() {
        List<ShelterView> shelterList = shelterBoardRepository.findTop10ByOrderByGoodCountDesc();

        if (shelterList.isEmpty()) {
            throw new NoSuchShelterException("No such shelter");
        }

        return shelterList.stream()
                .map(ShelterView::toDto)
                .collect(Collectors.toList());
    }

    public List<ShelterViewDto> getShelterData(List<String> shelterSN, List<String> type) {
        if (shelterSN.size() != type.size()) {
            throw new IllegalArgumentException("Shelter SN and type lists must be of the same size.");
        }

        return IntStream.range(0, shelterSN.size())
                .mapToObj(i -> shelterBoardRepository
                        .findByShelterSNAndShelterType(shelterSN.get(i), type.get(i)))
                .filter(Objects::nonNull)
                .map(ShelterView::toDto)
                .collect(Collectors.toList());
    }

}
