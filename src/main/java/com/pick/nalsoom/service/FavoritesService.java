package com.pick.nalsoom.service;

import com.pick.nalsoom.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("favoritesService")
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final UserService userService;

    @Autowired
    public FavoritesService(FavoritesRepository favoritesRepository, UserService userService) {
        this.favoritesRepository = favoritesRepository;
        this.userService = userService;
    }
}
