package com.pick.nalsoom.Dto.Good;

import com.pick.nalsoom.Domain.Good.Good;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodDto {

    private Long goodProperNum;
    private String goodTime;
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
