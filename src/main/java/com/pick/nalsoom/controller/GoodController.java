package com.pick.nalsoom.controller;

import java.util.List;

import com.pick.nalsoom.dto.GoodDto;
import com.pick.nalsoom.utils.GoodDuplicateException;
import com.pick.nalsoom.utils.NoSuchGoodException;
import com.pick.nalsoom.utils.NoSuchShelterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.pick.nalsoom.service.GoodService;
import com.pick.nalsoom.jwt.UserDetailsImpl;

@RestController
@RequestMapping("/api/v1/good")
@RequiredArgsConstructor
public class GoodController {

    private final GoodService goodService;

    //Good 기능 RestFul API 설계
    //기본적으로 회원기능이기에 헤더에 유저정보를 담아서 요청한다
    //모든 요청 header 토큰 검사 진행 -> AuthenticationPrincipal 로 토큰 자체에 대한 유효성 검사 진행 -> 토큰 검사 진행 결과 만료 혹은 위변조 시 noSuchUserException 발생
    //CRUD 할 자원은 Good 으로 모두 동일하기에 url 자원 표시는 good 으로 통일

    //get -> url 에 조회 할 조건을 담아서 보낸다
    @GetMapping
    public ResponseEntity<List<GoodDto>> getGoodData(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(goodService.getMyGoodData(userDetails));
    }

    //post -> body 에 생성할 데이터를 담아서 보낸다
    @PostMapping
    public ResponseEntity<GoodDto> putGoodData(
            @RequestBody GoodDto goodDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        GoodDto newGoodDto = null;

        if(goodDto.getShelterProperNum() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        try {
            newGoodDto = goodService.createGoodData(userDetails, goodDto);
        } catch (NoSuchShelterException | GoodDuplicateException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newGoodDto);
    }

    //delete -> url 에 삭제 할 조건을 담아서 보낸다
    @DeleteMapping("/{goodProperNum}")
    public ResponseEntity<Object> deleteGoodData(
            @PathVariable("goodProperNum") String goodProperNum,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        try {
            goodService.deleteGoodData(userDetails, Long.parseLong(goodProperNum));
        } catch (NoSuchGoodException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
