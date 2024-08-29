package com.pick.nalsoom.Dto.Favorites;

import com.pick.nalsoom.Domain.Favorites.Favorites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritesDto {

    private Long favoritesProperNum;
    private String favoritesLocation;
    private Long userProperNum;

    public Favorites toEntity() {
        return Favorites.builder()
        .favoritesProperNum(favoritesProperNum)
        .favoritesLocation(favoritesLocation)
        .userProperNum(userProperNum)
        .build();
    }
}
