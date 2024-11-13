package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.FavoritesDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "FAVORITES")
@Builder
@Entity
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAVORITES_PROPER_NUM")
    private Long favoritesProperNum;

    @Column(name = "FAVORITES_LOCATION")
    private String favoritesLocation;

    @Column(name = "USER_PROPER_NUM")
    private Long userProperNum;

    public FavoritesDto toDto() {
        return FavoritesDto.builder()
            .favoritesProperNum(favoritesProperNum)
            .favoritesLocation(favoritesLocation)
            .userProperNum(userProperNum)
            .build();
    }
}
