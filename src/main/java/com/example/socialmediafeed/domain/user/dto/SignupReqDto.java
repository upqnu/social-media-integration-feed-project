package com.example.socialmediafeed.domain.user.dto;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.user.entitiy.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupReqDto {
    @Email(message = "이메일 형식에 맞게 작성해 주세요")
    private String email;
    @Size(min = 8, message = "비밀번호는 8자 이상 입력해주세요")
    @Pattern(regexp = "^(?!\\d+$).+", message = "숫자로만 이뤄진 비밀번호는 사용할 수 없습니다")
    private String password;
    @NotEmpty(message = "해시태그를 입력해 주세요")
    private String hashtag;

    public static SignupReqDto of(String email, String password, String hashtag) {
        return new SignupReqDto(email, password, hashtag);
    }

    public User toEntity(PasswordEncoder encoder,
                         String certificationNumber) {
        return User.builder()
                .email(email)
                .password(encoder.encode(password))
                .certificationNumber(certificationNumber)
                .hashtag(toHashtagEntity())
                .build();
    }

    private Hashtag toHashtagEntity() {
        return Hashtag.builder()
                .name(hashtag)
                .build();
    }


}