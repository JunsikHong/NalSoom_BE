package com.pick.nalsoom.Dto.Shelter;

import com.pick.nalsoom.Domain.Shelter.Shelter;

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
    private String shelterType;
    private String areaCD;

    public Shelter toEntity() {
        return Shelter.builder()
                .shelterProperNum(shelterProperNum)
                .shelterType(shelterType)
                .areaCD(areaCD)
                .build();
    }

}
