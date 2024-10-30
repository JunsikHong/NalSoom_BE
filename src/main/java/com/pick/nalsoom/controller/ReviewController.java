package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.ReviewDto;
import com.pick.nalsoom.service.ReviewService;
import com.pick.nalsoom.service.UserService;
import com.pick.nalsoom.jwt.UserDetailsImpl;
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

    @GetMapping("/{shelterProperNum}")
    public ResponseEntity<List<ReviewDto>> getReviewData (
            @PathVariable("shelterProperNum") Long shelterProperNum) {

        if(shelterProperNum == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.ok().body(reviewService.getReviewData(shelterProperNum));

    }

    @GetMapping ("my-review")
    public ResponseEntity<List<ReviewDto>> getMyReviewData (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> putReviewData(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("controller in");
        ReviewDto newReview = reviewService.putReviewData(userDetails, reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @PutMapping
    public ResponseEntity<ReviewDto> modifyReviewData(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return null;
    }

    @DeleteMapping("/{reviewProperNum}")
    public ResponseEntity<Object> deleteReviewData(
            @PathVariable String reviewProperNum,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return null;
    }

}
