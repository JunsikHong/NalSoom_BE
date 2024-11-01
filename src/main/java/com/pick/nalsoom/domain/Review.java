package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.ReviewDto;

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

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEW")
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_PROPER_NUM")
    private Long reviewProperNum;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;

    @Column(name = "REVIEW_WRITE_TIME" , nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp reviewWriteTime;

    @Column(name = "USER_PROPER_NUM")
    private Long userProperNum;

    @Column(name = "SHELTER_PROPER_NUM")
    private Long shelterProperNum;

    public ReviewDto toDto() {
        return ReviewDto.builder()
                .reviewProperNum(reviewProperNum)
                .reviewContent(reviewContent)
                .reviewWriteTime(reviewWriteTime)
                .userProperNum(userProperNum)
                .shelterProperNum(shelterProperNum)
                .build();
    }

}
