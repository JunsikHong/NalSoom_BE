package com.pick.nalsoom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pick.nalsoom.Domain.Good.Good;
import com.pick.nalsoom.Service.Good.GoodService;
import com.pick.nalsoom.Service.User.UserService;
import com.pick.nalsoom.jwt.UserDetailsImpl;


@RestController
@RequestMapping("/api/v1/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/getGoodData")
    public ResponseEntity<List<Good>> getGoodData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        Long userProperNum = userService.getUserProperNum(userDetailsImpl.getUsername());
        return ResponseEntity.ok(goodService.getGoodData(userProperNum));
    }
    
}
