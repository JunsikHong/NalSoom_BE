package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.ShelterViewDto;

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
@Table(name = "SHELTER_DETAIL_INFO_LIST")
@Builder
@Entity
public class ShelterView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHELTER_PROPER_NUM")
    private Long shelterProperNum;

    @Column(name = "SHELTER_TYPE")
    private String shelterType;

    @Column(name = "SHELTER_SN")
    private String shelterSN;

    @Column(name = "GOOD_COUNT")
    private Long goodCount;

    @Column(name = "REVIEW_COUNT")
    private Long reviewCount;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;

    @Column(name = "RECENT_WRITE_TIME")
    private String recentWriteTime;

    public ShelterViewDto toDto() {
        return ShelterViewDto.builder()
                .shelterProperNum(shelterProperNum)
                .shelterType(shelterType)
                .shelterSN(shelterSN)
                .goodCount(goodCount)
                .reviewCount(reviewCount)
                .reviewContent(reviewContent)
                .recentWriteTime(recentWriteTime)
                .build();
    }
}

