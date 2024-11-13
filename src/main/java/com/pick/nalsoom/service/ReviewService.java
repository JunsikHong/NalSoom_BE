package com.pick.nalsoom.service;

import com.pick.nalsoom.domain.Review;
import com.pick.nalsoom.dto.ReviewDto;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import com.pick.nalsoom.repository.ReviewRepository;
import com.pick.nalsoom.utils.NoSuchReviewException;
import com.pick.nalsoom.utils.NoSuchShelterException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("reviewService")
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ShelterService shelterService;

    //userService 에 유저 정보에 대한 유효성 검사 위임 -> 유저 정보가 없을 시 no such user exception 발생

    //login x
    //get -> 대피소에 대한 리뷰
    public List<ReviewDto> getReviewData(Long shelterProperNum, int reviewPaging, int reviewPagingSize) {

        shelterService.findOneShelter(shelterProperNum).orElseThrow(() -> {
            throw new NoSuchShelterException("No such shelter");
        });

        Sort sort = Sort.by(Sort.Direction.fromString("desc"), "reviewWriteTime");
        Pageable pageable = PageRequest.of(reviewPaging, reviewPagingSize, sort);

        return reviewRepository.findByShelterProperNum(shelterProperNum, pageable)
                .stream().map(Review::toDto).toList();
    }

    //login o
    //get -> 사용자에 대한 리뷰
    public List<ReviewDto> getMyReviewData(UserDetailsImpl userDetails, Long shelterProperNum, int reviewPaging, int reviewPagingSize) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        shelterService.findOneShelter(shelterProperNum).orElseThrow(() -> {
            throw new NoSuchShelterException("No such shelter");
        });

        Sort sort = Sort.by(Sort.Direction.fromString("desc"), "reviewWriteTime");
        Pageable pageable = PageRequest.of(reviewPaging, reviewPagingSize, sort);

        return reviewRepository.findByUserProperNum(userProperNum, pageable)
                .stream().map(Review::toDto).toList();
    }

    //post -> 한 사용자가 하나의 대피소에 대해 여러 개의 리뷰 작성 할 수 있다
    //shelter 존재 여부 판단해서 없으면 No such shelter exception 발생
    //존재하지 않으면 review create
    public ReviewDto createReviewData(UserDetailsImpl userDetails, ReviewDto reviewDto) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        shelterService.findOneShelter(reviewDto.getShelterProperNum()).orElseThrow(() -> {
            throw new NoSuchShelterException("No such shelter");
        });

        reviewDto.setUserProperNum(userProperNum);
        return reviewRepository.save(reviewDto.toEntity()).toDto();
    }

    //put -> 본인 리뷰 데이터가 있을 때 수정 할 수 있다
    //리뷰 고유번호와 사용자 고유번호 기반 검색결과 데이터가 존재하지 않으면 no such review exception 발생
    //존재하면 review update
    public ReviewDto modifyReviewData(UserDetailsImpl userDetails, ReviewDto reviewDto) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        Review reviewFound = null;

        shelterService.findOneShelter(reviewDto.getShelterProperNum()).orElseThrow(() -> {
            throw new NoSuchShelterException("No such shelter");
        });

        reviewFound = findOneReview(reviewDto.getReviewProperNum()).orElseThrow(() -> {
            throw new NoSuchReviewException("No such review");
        });

        reviewFound.setReviewContent(reviewDto.getReviewContent());

        return reviewRepository.save(reviewFound).toDto();
    }

    //delete -> 본인 리뷰 데이터가 있을 때 삭제할 수 있다
    //리뷰 고유번호 기반 검색결과 데이터가 존재하지 않으면 noSuchReviewException 발생
    //존재하면 review delete
    public void deleteReviewData(UserDetailsImpl userDetails, Long reviewProperNum) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        findOneReview(reviewProperNum).orElseThrow(() -> {
            throw new NoSuchReviewException("No such review");
        });

        reviewRepository.deleteById(reviewProperNum);
    }

    public Optional<Review> findOneReview (Long reviewProperNum) {
        return reviewRepository.findById(reviewProperNum);
    }

}
