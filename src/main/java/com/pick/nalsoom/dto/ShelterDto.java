package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.Shelter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterDto {

    private Long shelterProperNum;
    private String ShelterType;
    private String ShelterSn;

    public Shelter toEntity() {
        return Shelter.builder()
                .shelterProperNum(shelterProperNum)
                .shelterType(ShelterType)
                .shelterSn(ShelterSn)
                .build();
    }
}
