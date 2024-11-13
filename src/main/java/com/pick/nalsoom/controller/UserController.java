package com.pick.nalsoom.controller;

import com.pick.nalsoom.utils.InvalidTokenException;
import com.pick.nalsoom.utils.NoSuchUserException;
import com.pick.nalsoom.utils.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pick.nalsoom.dto.UserDto;
import com.pick.nalsoom.service.UserService;
import com.pick.nalsoom.jwt.JwtToken;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //이메일 발송
    @PostMapping("/sendEmail")
    public ResponseEntity<Object> sendEmail(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(null);
    }

    //이메일 인증번호검사
    @PostMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail(@RequestBody String entity) {
        return ResponseEntity.ok().body(null);
    }

    //중복검사
    @PostMapping("/idDuplicateCheck")
    public ResponseEntity<Object> idDuplicateCheck (@RequestBody UserDto userDto) {
        if(userDto.getUserId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            userService.idDuplicateCheck(userDto.getUserId());
        } catch (UserDuplicateException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok().body(null);
    }

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody UserDto userDto) {
        if(userDto.getUserId().isEmpty() || userDto.getUserPw().isEmpty() || userDto.getUserEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            userService.join(userDto);
        } catch (UserDuplicateException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    
    //로그인
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserDto userDto) {
        JwtToken token = null;

        if(userDto.getUserId().isEmpty() || userDto.getUserPw().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            token = userService.login(userDto);
        } catch (NoSuchUserException | InvalidTokenException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(token);
    }
    
}
