package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.GoodDto;

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
@Table(name = "GOOD")
@Builder
@Entity
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOOD_PROPER_NUM")
    private Long goodProperNum;

    @Column(name = "GOOD_TIME", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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
