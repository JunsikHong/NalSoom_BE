package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.ComplaintDto;
import com.pick.nalsoom.jwt.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/complaint")
public class ComplaintController {

    @PostMapping
    public ResponseEntity<Object> postComplaintData (
            @RequestBody ComplaintDto complaintDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        System.out.println(complaintDto.toString());
        //메일 서비스로 ->
        System.out.println("신고 내용이 접수되었습니다.");
        return ResponseEntity.ok().body(null);
    }

}
