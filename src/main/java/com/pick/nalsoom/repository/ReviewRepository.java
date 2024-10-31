package com.pick.nalsoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{


    List<Review> findTop10ByShelterProperNumOrderByReviewWriteTimeDesc(Long shelterProperNum);
    List<Review> findByUserProperNumOrderByReviewWriteTimeDesc (Long userProperNum);
    List<Review> findByUserProperNumAndShelterProperNum (Long userProperNum, Long shelterProperNum);
}
