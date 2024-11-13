package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.ReviewDto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @CreationTimestamp
    @Column(name = "REVIEW_WRITE_TIME" , nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
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
