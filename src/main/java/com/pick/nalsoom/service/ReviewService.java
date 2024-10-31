package com.pick.nalsoom.service;

import com.pick.nalsoom.domain.Review;
import com.pick.nalsoom.dto.ReviewDto;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import com.pick.nalsoom.repository.ReviewRepository;
import com.pick.nalsoom.utils.NoSuchReviewException;
import com.pick.nalsoom.utils.NoSuchShelterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ShelterService shelterService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserService userService, ShelterService shelterService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.shelterService = shelterService;
    }

    //userService 에 유저 정보에 대한 유효성 검사 위임 -> 유저 정보가 없을 시 no such user exception 발생

    //get -> 대피소에 대한 최신 리뷰 10개
    public List<ReviewDto> getReviewData(Long shelterProperNum) {
        return reviewRepository.findTop10ByShelterProperNumOrderByReviewWriteTimeDesc(shelterProperNum)
                .stream().map(Review::toDto).toList();
    }

    //get -> 대피소에 대한 최신 리뷰 10개
    //사용자 고유번호 기반 검색
    public List<ReviewDto> getReviewData(Long shelterProperNum, UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        return reviewRepository.findTop10ByShelterProperNumOrderByReviewWriteTimeDesc(shelterProperNum)
                .stream().map(review -> {
                    ReviewDto reviewDto = review.toDto();
                    if (review.getUserProperNum().equals(userProperNum)) {
                        reviewDto.setMyReview(true);
                    }
                    return reviewDto;
                }).toList();
    }

    //get -> 회원에 대한 최신 리뷰 10개
    //사용자 고유번호 기반 검색
    public List<ReviewDto> getMyReviewData(UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        return reviewRepository.findByUserProperNumOrderByReviewWriteTimeDesc(userProperNum)
                .stream().map(Review::toDto).toList();
    }

    //post -> 한 사용자가 하나의 대피소에 대해 여러 개의 리뷰 작성 할 수 있다
    //shelter 존재 여부 판단해서 없으면 No such shelter exception 발생
    //존재하지 않으면 review create
    public ReviewDto putReviewData(UserDetailsImpl userDetails, ReviewDto reviewDto) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        if(!shelterService.existShelterData(reviewDto.getShelterProperNum())) {
            throw new NoSuchShelterException("No such shelter");
        }

        reviewDto.setUserProperNum(userProperNum);
        return reviewRepository.save(reviewDto.toEntity()).toDto();
    }

    //put -> 본인 리뷰 데이터가 있을 때 수정 할 수 있다
    //리뷰 고유번호와 사용자 고유번호 기반 검색결과 데이터가 존재하지 않으면 no such review exception 발생
    //존재하면 review update
    public ReviewDto modifyReviewData(UserDetailsImpl userDetails, ReviewDto reviewDto) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        if(!shelterService.existShelterData(reviewDto.getShelterProperNum())) {
            throw new NoSuchShelterException("No such shelter");
        }

        if(reviewRepository.findByUserProperNumAndShelterProperNum(userProperNum, reviewDto.getShelterProperNum()).isEmpty()) {
            throw new NoSuchReviewException("No such review");
        }

        reviewDto.setUserProperNum(userProperNum);
        return reviewRepository.save(reviewDto.toEntity()).toDto();
    }

    //delete -> 본인 리뷰 데이터가 있을 때 삭제할 수 있다
    //리뷰 고유번호 기반 검색결과 데이터가 존재하지 않으면 noSuchReviewException 발생
    //존재하면 review delete
    public void deleteReviewData(UserDetailsImpl userDetails, Long reviewProperNum) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        if(!reviewRepository.existsById(reviewProperNum)) {
            throw new NoSuchReviewException("No such review");
        }

        reviewRepository.deleteById(reviewProperNum);
    }
}
