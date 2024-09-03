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
@Table(name = "SHELTER_DETAIL_INFO_LIST")
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

    @Column(name = "GOOD_COUNT")
    private Long goodCount;

    @Column(name = "REVIEW_COUNT")
    private Long reviewCount;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;

    @Column(name = "RECENT_WRITE_TIME")
    private String recentWriteTime;

    public ShelterDto toDto() {
        return ShelterDto.builder()
                .shelterProperNum(shelterProperNum)
                .shelterType(shelterType)
                .areaCD(areaCD)
                .goodCount(goodCount)
                .reviewCount(reviewCount)
                .reveiwContent(reviewContent)
                .recentWriteTime(recentWriteTime)
                .build();
    }
}
