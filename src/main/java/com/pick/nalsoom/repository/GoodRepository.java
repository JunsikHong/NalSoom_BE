package com.pick.nalsoom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.Good;

public interface GoodRepository extends JpaRepository<Good, Long>{

    List<Good> findByUserProperNum(Long userProperNum);
    List<Good> findByUserProperNumAndShelterProperNum(Long userProperNum, Long shelterProperNum);
}
