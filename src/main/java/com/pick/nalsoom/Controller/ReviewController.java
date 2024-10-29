package com.pick.nalsoom.Controller;

import com.pick.nalsoom.Dto.Review.ReviewDto;
import com.pick.nalsoom.Service.Review.ReviewService;
import com.pick.nalsoom.Service.User.UserService;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviewData (
            @RequestParam("shelterProperNum") List<String> shelterProperNum) {
        return ResponseEntity.ok().body(reviewService.getReviewData());
    }

    @GetMapping ("my-review")
    public ResponseEntity<List<ReviewDto>> getMyReviewData (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        return ResponseEntity.ok().body(reviewService.getMyReviewData());
    }

    @PostMapping
    public ResponseEntity<ReviewDto> putReviewData(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        return null;
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
