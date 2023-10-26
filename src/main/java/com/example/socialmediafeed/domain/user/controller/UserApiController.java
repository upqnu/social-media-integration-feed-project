package com.example.socialmediafeed.domain.user.controller;

import com.example.socialmediafeed.domain.user.dto.ApprovalReqDto;
import com.example.socialmediafeed.domain.user.dto.SignupReqDto;
import com.example.socialmediafeed.domain.user.dto.SignupResDto;
import com.example.socialmediafeed.domain.user.service.UserApprovalService;
import com.example.socialmediafeed.domain.user.service.UserSignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserApiController {

    private final UserSignupService userSignupService;
    private final UserApprovalService userApprovalService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResDto> signUp(@RequestBody
                                         @Valid
                                         SignupReqDto signupReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignupService.signUp(signupReqDto));
    }

    @PatchMapping("/{id}/approval")
    public ResponseEntity<Object> approval(@PathVariable(name = "id")
                                           Long id,
                                           @RequestBody
                                           @Valid
                                           ApprovalReqDto approvalReqDto) {
        userApprovalService.approve(id, approvalReqDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}