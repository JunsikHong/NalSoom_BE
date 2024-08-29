package com.pick.nalsoom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pick.nalsoom.Dto.User.UserDto;
import com.pick.nalsoom.Service.User.UserService;
import com.pick.nalsoom.jwt.JwtToken;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    //아이디 중복검사
    @PostMapping("/idDuplicateCheck")
    public ResponseEntity<Boolean> idDuplicateCheck(@RequestBody UserDto userDto) {
        boolean result = userService.idDuplicateCheck(userDto.getUserId());
        return ResponseEntity.ok().body(result);
    }

    //이메일 발송
    @PostMapping("/sendEmail")
    public Boolean sendEmail(@RequestBody UserDto userDto) {
        return true;
    }

    //이메일 인증번호검사
    @PostMapping("/checkEmail")
    public Boolean checkEmail(@RequestBody String entity) {
        return true;
    }
    
    //회원가입
    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody UserDto userDto) {
        boolean result = userService.join(userDto);
        return ResponseEntity.ok().body(result);
    }
    
    //로그인
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserDto userDto) {
        JwtToken token = userService.login(userDto);
        return ResponseEntity.ok(token);
    }
    
}
