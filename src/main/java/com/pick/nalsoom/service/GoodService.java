package com.pick.nalsoom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pick.nalsoom.dto.GoodDto;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import com.pick.nalsoom.utils.GoodDuplicateException;
import com.pick.nalsoom.utils.NoSuchGoodException;
import com.pick.nalsoom.utils.NoSuchShelterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.domain.Good;
import com.pick.nalsoom.repository.GoodRepository;

@Service("goodService")
@RequiredArgsConstructor
public class GoodService {

    private final GoodRepository goodRepository;
    private final UserService userService;
    private final ShelterService shelterService;

    //userService 에 유저 정보에 대한 유효성 검사 위임 -> 유저 정보가 없을 시 no such user exception 발생

    //get -> 본인 좋아요 데이터만 검색
    //사용자 고유번호 기반 검색
    public List<GoodDto> getMyGoodData(UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        return goodRepository.findByUserProperNum(userProperNum)
                .stream().map(Good::toDto).toList();
    }

    //post -> 한 사용자가 하나의 대피소에 대해 한 번의 좋아요만 누를 수 있다
    //shelter 존재 여부 판단해서 없으면 No such shelter exception 발생
    //사용자 고유번호와 대피소 고유번호 기반 검색결과 이미 데이터가 존재 하면 goodDuplicationException 발생
    //존재하지 않으면 good create
    public GoodDto createGoodData(UserDetailsImpl userDetails, GoodDto goodDto) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        shelterService.findOneShelter(goodDto.getShelterProperNum()).orElseThrow(() -> {
            throw new NoSuchShelterException("No such shelter");
        });

        if(!goodRepository.findByUserProperNumAndShelterProperNum(userProperNum, goodDto.getShelterProperNum()).isEmpty()) {
            throw new GoodDuplicateException("Good Already exists");
        }

        goodDto.setUserProperNum(userProperNum);
        return goodRepository.save(goodDto.toEntity()).toDto();
    }

    //delete -> 본인 좋아요 데이터가 있을 때 삭제할 수 있다
    //좋아요 고유번호 기반 검색결과 데이터가 존재하지 않으면 noSuchGoodException 발생
    //존재하면 good delete
    public void deleteGoodData(UserDetailsImpl userDetails, long goodProperNum) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        Good goodFound = goodRepository.findById(goodProperNum).orElseThrow(() -> {
            throw new NoSuchGoodException("No Such Good");
        });

        goodRepository.delete(goodFound);
    }
}
