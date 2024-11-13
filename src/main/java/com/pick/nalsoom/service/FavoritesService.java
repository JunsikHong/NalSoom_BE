package com.pick.nalsoom.service;

import com.pick.nalsoom.repository.FavoritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("favoritesService")
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final UserService userService;

}
