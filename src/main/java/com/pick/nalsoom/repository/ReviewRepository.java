package com.pick.nalsoom.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{


    List<Review> findByShelterProperNum(Long shelterProperNum, Pageable pageable);
    List<Review> findByUserProperNum (Long userProperNum, Pageable pageable);
}
