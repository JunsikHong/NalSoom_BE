package com.pick.nalsoom.Repository.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.Domain.Review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
