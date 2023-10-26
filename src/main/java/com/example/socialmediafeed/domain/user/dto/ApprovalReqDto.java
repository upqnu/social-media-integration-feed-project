package com.example.socialmediafeed.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApprovalReqDto {
    @NotEmpty(message = "인증번호를 입력해주세요")
    private String certificationNumber;
}