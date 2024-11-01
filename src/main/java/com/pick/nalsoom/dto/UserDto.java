package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long userProperNum;
    private String userId;
    private String userName;
    private String userPw;
    private String userEmail;
    private String userAddress;

    public User toEntity() {
        return User.builder()
                .userProperNum(userProperNum)
                .userId(userId)
                .userName(userName)
                .userPw(userPw)
                .userEmail(userEmail)
                .userAddress(userAddress)
                .build();
    }
}
