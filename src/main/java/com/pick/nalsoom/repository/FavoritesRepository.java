package com.pick.nalsoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long>{

    
}