package com.pick.nalsoom.Service.Shelter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.pick.nalsoom.Domain.Shelter.ShelterView;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.Dto.Shelter.ShelterViewDto;
import com.pick.nalsoom.Repository.Shelter.ShelterBoardViewRepository;

import javax.swing.text.html.Option;

@Service("shelterBoardService")
public class ShelterBoardService {

    @Autowired
    private ShelterBoardViewRepository shelterBoardViewRepository;

    public List<ShelterViewDto> getSheltersData() {
        List<ShelterView> shelterList = shelterBoardViewRepository.findTop10ByOrderByGoodCountDesc();

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
                .mapToObj(i -> shelterBoardViewRepository
                        .findByShelterSNAndShelterType(shelterSN.get(i), type.get(i)))
                .filter(Objects::nonNull)
                .map(ShelterView::toDto)
                .collect(Collectors.toList());
    }

}
