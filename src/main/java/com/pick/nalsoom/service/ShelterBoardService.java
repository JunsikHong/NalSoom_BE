package com.pick.nalsoom.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.pick.nalsoom.domain.ShelterView;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<ShelterViewDto> getShelterData(String searchShelterType, String searchSortBy, String searchSortDirection, int searchPaging, int searchSize) {

        //대피소 타입이 기본 일 때 계절 반영
        if (searchShelterType.equals("normal")) {
            searchShelterType = determineShelterType(searchShelterType);
        }

        //정렬 타입 -> 컬럼 명
        searchSortBy = determineSortBy(searchSortBy);

        Sort sort = Sort.by(Sort.Direction.fromString(searchSortDirection), searchSortBy);
        Pageable pageable = PageRequest.of(searchPaging, searchSize, sort);
        List<ShelterView> shelterList = shelterBoardRepository.findByShelterType(searchShelterType, pageable);
        return shelterList.stream().map(ShelterView::toDto).collect(Collectors.toList());
    }

    public List<ShelterViewDto> getInBoundShelterData(String searchShelterType, String searchSortBy, String searchSortDirection, List<Long> shelterProperNum, int searchPaging, int searchSize) {

        //sn, type 유효성 검사
        if (!shelterProperNum.isEmpty()) {
            throw new NoSuchShelterException("No such shelter");
        }

        //대피소 타입이 기본 일 때 계절 반영
        if (searchShelterType.equals("normal")) {
            determineShelterType(searchShelterType);
        }

        Sort sort = Sort.by(Sort.Direction.fromString(searchSortDirection), searchSortBy);
        Pageable pageable = PageRequest.of(searchPaging, searchSize, sort);

        List<ShelterView> shelterViewDtoList = shelterBoardRepository.findAllByShelterProperNumInAndShelterType(shelterProperNum, searchShelterType, pageable);
        return shelterViewDtoList.stream().map(ShelterView::toDto).collect(Collectors.toList());
    }

    public String determineShelterType (String searchShelterType) {
        String result = "";
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        if (currentMonth.equals(Month.MARCH) || currentMonth.equals(Month.APRIL) || currentMonth.equals(Month.MAY) ||
                currentMonth.equals(Month.SEPTEMBER) || currentMonth.equals(Month.OCTOBER) || currentMonth.equals(Month.NOVEMBER)) {
            result = "shuntPlace"; // 봄,가을 -> 미세먼지 대피소
        } else if (currentMonth.equals(Month.JUNE) || currentMonth.equals(Month.JULY) || currentMonth.equals(Month.AUGUST)) {
            result = "TbGtnHwcwP"; // 여름 -> 무더위 쉼터
        } else {
            result = "TbGtnCwP"; // 겨울 -> 한파 쉼터
        }
        return result;
    }

    public String determineSortBy (String searchSortBy) {
        String result = "";
        if (searchSortBy.equals("good")) {
            result = "goodCount";
        } else if (searchSortBy.equals("review")) {
            result = "reviewCount";
        }
        return result;
    }
}
