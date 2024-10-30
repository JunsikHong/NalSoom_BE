package com.pick.nalsoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    
}
