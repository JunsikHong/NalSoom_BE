package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long reviewProperNum;
    private String reviewContent;
    private Timestamp reviewWriteTime;
    private Long userProperNum;
    private Long shelterProperNum;

    public Review toEntity() {
        return Review.builder()
                .reviewProperNum(reviewProperNum)
                .reviewContent(reviewContent)
                .reviewWriteTime(reviewWriteTime)
                .userProperNum(userProperNum)
                .shelterProperNum(shelterProperNum)
                .build();
    }
}
