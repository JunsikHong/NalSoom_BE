package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.ShelterDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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

    @Column(name = "SHELTER_SN")
    private String shelterSn;

    public ShelterDto toDto() {
        return ShelterDto.builder()
                .shelterProperNum(shelterProperNum)
                .ShelterType(shelterType)
                .ShelterSn(shelterSn)
                .build();
    }
}
