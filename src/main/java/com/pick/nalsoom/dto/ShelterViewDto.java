package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.ShelterView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterViewDto {

    private Long shelterProperNum;
    private String shelterType;
    private String shelterSN;
    private Long goodCount;
    private Long reviewCount;
    private String reviewContent;
    private String recentWriteTime;

    public ShelterView toEntity() {
        return ShelterView.builder()
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

