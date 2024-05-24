package com.pick.nalsoom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pick.nalsoom.Dto.User.UserDto;
import com.pick.nalsoom.Service.User.UserService;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String getMethodName() {
        return "서버";
    }
    
    //아이디 중복검사
    @PostMapping("/idDuplicateCheck")
    public ResponseEntity<Boolean> idDuplicateCheck(@RequestBody String userId) {
        boolean result = userService.idDuplicateCheck(userId);
        return ResponseEntity.ok().body(result);
    }

    //이메일 발송
    @PostMapping("/emailCheck")
    public Boolean emailCheck(@RequestBody String email) {
        System.out.println(email);
        
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
    public Boolean login(@RequestBody UserDto userDto) {
        
        
        return true;
    }
    
    
}
