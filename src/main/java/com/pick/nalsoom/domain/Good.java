package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.GoodDto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "GOOD")
@Builder
@Entity
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOOD_PROPER_NUM")
    private Long goodProperNum;

    @CreationTimestamp
    @Column(name = "GOOD_TIME", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp goodTime;

    @Column(name = "USER_PROPER_NUM")
    private Long userProperNum;

    @Column(name = "SHELTER_PROPER_NUM")
    private Long shelterProperNum;

    public GoodDto toDto() {
        return GoodDto.builder()
                .goodProperNum(goodProperNum)
                .goodTime(goodTime)
                .userProperNum(userProperNum)
                .shelterProperNum(shelterProperNum)
                .build();
    }
}
