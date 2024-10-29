package com.pick.nalsoom.Controller;

import java.util.List;

import com.pick.nalsoom.Dto.Good.GoodDto;
import com.pick.nalsoom.utils.GoodDuplicateException;
import com.pick.nalsoom.utils.NoSuchGoodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<GoodDto>> getGoodData(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        return ResponseEntity.ok(goodService.getGoodData(userProperNum));
    }
    
    @PostMapping
    public ResponseEntity<GoodDto> putGoodData(@RequestBody GoodDto goodDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());
        GoodDto newGoodDto = null;

        try {
            goodDto.setUserProperNum(userProperNum);
            newGoodDto = goodService.putGoodData(goodDto);
        } catch (GoodDuplicateException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newGoodDto);
    }

    @DeleteMapping("/{goodProperNum}")
    public ResponseEntity<Object> deleteGoodData(@PathVariable("goodProperNum") String goodProperNum, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userProperNum = userService.getUserProperNum(userDetails.getUsername());

        try {
            goodService.deleteGoodData(Long.parseLong(goodProperNum));
        } catch (NoSuchGoodException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }
}
