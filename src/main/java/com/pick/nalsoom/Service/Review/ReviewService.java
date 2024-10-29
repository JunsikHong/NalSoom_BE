package com.pick.nalsoom.Service.Review;

import com.pick.nalsoom.Dto.Review.ReviewDto;
import com.pick.nalsoom.Repository.Review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewDto> getReviewData() {

        return null;
    }

    public List<ReviewDto> getMyReviewData() {

        return null;
    }

    public ReviewDto putReviewData(ReviewDto reviewDto) {

        return null;
    }

    public ReviewDto modifyReviewData(ReviewDto reviewDto) {

        return null;
    }

    public void deleteReviewData(String reviewProperNum) {


    }
}
