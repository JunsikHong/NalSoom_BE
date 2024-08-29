package com.pick.nalsoom.Dto.User;

import com.pick.nalsoom.Domain.User.User;

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
    private String userPw;
    private String userEmail;

    public User toEntity() {
        return User.builder()
                .userProperNum(userProperNum)
                .userId(userId)
                .userPw(userPw)
                .userEmail(userEmail)
                .build();
    }
}
