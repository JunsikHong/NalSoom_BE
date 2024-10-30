package com.pick.nalsoom.service;

import com.pick.nalsoom.domain.Review;
import com.pick.nalsoom.dto.ReviewDto;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import com.pick.nalsoom.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("reviewService")
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }

    public List<ReviewDto> getReviewData(Long shelterProperNumList) {
        return reviewRepository.findTop10ByShelterProperNumOrderByReviewWriteTimeDesc(shelterProperNumList)
                .stream().map(Review::toDto).collect(Collectors.toList());
    }

    public List<ReviewDto> getMyReviewData(UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        return null;
    }

    public ReviewDto putReviewData(UserDetailsImpl userDetails, ReviewDto reviewDto) {
        System.out.println("service in");
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        reviewDto.setUserProperNum(userProperNum);
        return reviewRepository.save(reviewDto.toEntity()).toDto();
    }

    public ReviewDto modifyReviewData(UserDetailsImpl userDetails, ReviewDto reviewDto) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        return null;
    }

    public void deleteReviewData(UserDetailsImpl userDetails, String reviewProperNum) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());


    }
}
