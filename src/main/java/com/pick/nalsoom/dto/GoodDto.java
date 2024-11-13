package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.Good;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodDto {

    private Long goodProperNum;
    private Timestamp goodTime;
    private Long userProperNum;
    private Long shelterProperNum;

    public Good toEntity() {
        return Good.builder()
                .goodProperNum(goodProperNum)
                .goodTime(goodTime)
                .userProperNum(userProperNum)
                .shelterProperNum(shelterProperNum)
                .build();
    }
}
