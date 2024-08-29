package com.pick.nalsoom.Dto.Review;

import com.pick.nalsoom.Domain.Review.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long reviewProperNum;
    private String reviewContent;
    private String reviewWriteTime;
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
