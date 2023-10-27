package com.example.socialmediafeed.domain.user.service;

import com.example.socialmediafeed.domain.user.dto.SignInReqDto;
import com.example.socialmediafeed.domain.user.entitiy.IsActive;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void signIn(SignInReqDto signInReqDto) {
        User user = userRepository.findByEmail(signInReqDto.getEmail())
                .orElseThrow(EntityNotFoundException::new);

        if(user.getIsActive().equals(IsActive.DISABLED))
            throw new IllegalArgumentException("가입 승인이 되지 않은 계정입니다");

        if(!encoder.matches(signInReqDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");

    }

}
