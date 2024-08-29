package com.pick.nalsoom.Domain.Shelter;

import com.pick.nalsoom.Dto.Shelter.ShelterDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHELTER")
@Builder
@Entity
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHELTER_PROPER_NUM")
    private Long shelterProperNum;

    @Column(name = "SHELTER_TYPE")
    private String shelterType;

    @Column(name = "AREA_CD")
    private String areaCD;

    public ShelterDto toDto() {
        return ShelterDto.builder()
                .shelterProperNum(shelterProperNum)
                .shelterType(shelterType)
                .areaCD(areaCD)
                .build();
    }
}
