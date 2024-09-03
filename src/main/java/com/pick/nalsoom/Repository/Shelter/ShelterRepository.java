package com.pick.nalsoom.Repository.Shelter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.Domain.Shelter.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long>{
    
    List<Shelter> findTop10ByOrderByShelterProperNumAsc();
}
