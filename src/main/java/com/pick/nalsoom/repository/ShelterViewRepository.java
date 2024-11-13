package com.pick.nalsoom.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.ShelterView;

public interface ShelterViewRepository extends JpaRepository<ShelterView, Long>{

    List<ShelterView> findByShelterType(String shelterType, Pageable pageable);
    List<ShelterView> findAllByShelterProperNumInAndShelterType(List<Long> shelterProperNum, String shelterType, Pageable pageable);

}

