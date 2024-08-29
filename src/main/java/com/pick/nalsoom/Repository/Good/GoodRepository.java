package com.pick.nalsoom.Repository.Good;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.Domain.Good.Good;

public interface GoodRepository extends JpaRepository<Good, Long>{
    
}
