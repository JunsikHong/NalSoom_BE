package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.UserDto;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_PROPER_NUM")
    private Long userProperNum;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    public UserDto toDto() {
        return UserDto.builder()
                .userProperNum(userProperNum)
                .userId(userId)
                .userPw(userPw)
                .userEmail(userEmail)
                .build();
    }
}
