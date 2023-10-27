package com.example.socialmediafeed.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApprovalReqDto {
    @NotEmpty(message = "인증번호를 입력해주세요")
    private String certificationNumber;

    public static ApprovalReqDto from(String certificationNumber) {
        return new ApprovalReqDto(certificationNumber);
    }

}