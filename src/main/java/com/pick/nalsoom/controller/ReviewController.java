package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.ReviewDto;
import com.pick.nalsoom.service.ReviewService;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import com.pick.nalsoom.utils.NoSuchReviewException;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //Review 기능 RestFul API 설계
    //기본적으로 회원기능이기에 헤더에 유저정보를 담아서 요청한다
    //모든 요청 header 토큰 검사 진행 -> AuthenticationPrincipal 로 토큰 자체에 대한 유효성 검사 진행 -> 토큰 검사 진행 결과 만료 혹은 위변조 시 noSuchUserException 발생
    //CRUD 할 자원은 Review 으로 모두 동일하기에 url 자원 표시는 review 으로 통일

    //예외1) not login 상태에서도 요청을 보낼 수 있기에 대응하기 위해 public / private 으로 구분
    //예외2) 마이페이지 에서 대피소 별이 아닌 자신의 리뷰 데이터 요청 보낼 수 있기에 대응하기 위해 my-review 생성

    //not login
    @GetMapping("/public/{shelterProperNum}")
    public ResponseEntity<List<ReviewDto>> getReviewData (
            @PathVariable("shelterProperNum") Long shelterProperNum) {

        if(shelterProperNum == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok().body(reviewService.getReviewData(shelterProperNum));

    }

    //login
    @GetMapping("/private/{shelterProperNum}")
    public ResponseEntity<List<ReviewDto>> getReviewData (
            @PathVariable("shelterProperNum") Long shelterProperNum,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(shelterProperNum == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok().body(reviewService.getReviewData(shelterProperNum, userDetails));

    }

    @GetMapping ("my-review")
    public ResponseEntity<List<ReviewDto>> getMyReviewData (
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(reviewService.getMyReviewData(userDetails));
    }

    @PostMapping
    public ResponseEntity<ReviewDto> putReviewData(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewDto newReview = null;

        if(reviewDto.getShelterProperNum() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String reviewContent = reviewDto.getReviewContent();
        if(reviewContent.length() < 5 || reviewContent.length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            newReview = reviewService.putReviewData(userDetails, reviewDto);
        } catch (NoSuchShelterException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @PutMapping
    public ResponseEntity<ReviewDto> modifyReviewData(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewDto modifiedReview = null;

        if(reviewDto.getShelterProperNum() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String reviewContent = reviewDto.getReviewContent();
        if(reviewContent.length() < 5 || reviewContent.length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            modifiedReview = reviewService.modifyReviewData(userDetails, reviewDto);
        } catch (NoSuchShelterException | NoSuchReviewException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok().body(modifiedReview);
    }

    @DeleteMapping("/{reviewProperNum}")
    public ResponseEntity<Object> deleteReviewData(
            @PathVariable ("reviewProperNum") Long reviewProperNum,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(reviewProperNum == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            reviewService.deleteReviewData(userDetails, reviewProperNum);
        } catch (NoSuchReviewException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}