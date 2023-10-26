package com.example.socialmediafeed.domain.user.service;

import com.example.socialmediafeed.domain.user.dto.SignupReqDto;
import com.example.socialmediafeed.domain.user.dto.SignupResDto;
import com.example.socialmediafeed.domain.user.repository.HashtagRepoForUser;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSignupService {

    private final HashtagRepoForUser hashtagRepoForUser;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public SignupResDto signUp(SignupReqDto signupReqDto) {
        //SignupReqDto 검증
        validate(signupReqDto);

        //5자의 무작위 번호 생성 및 중복 검사: 문자, 숫자 포함
        String randomNumber = generateNotUsedRandomNumber();

        //DB에 해당 정보 저장
        return SignupResDto.from(userRepository.save(signupReqDto.toEntity(encoder, randomNumber)));
    }

    //todo: exception을 business exception으로 갈아야 합니다.
    private void validate(SignupReqDto signupReqDto) {
        //해당 해쉬태그가 이미 존재하는 해쉬태그인지 검사
        if(hashtagRepoForUser.existsByName(signupReqDto.getHashtag()))
            throw new EntityExistsException();
        //헤당 이메일이 존재하는 이메일인지 확인
        if(userRepository.existsByEmail(signupReqDto.getEmail()))
            throw new EntityExistsException();
    }

    private String generateNotUsedRandomNumber() {
        String randomNumber = RandomStringUtils.random(5, true, true);

        String result = randomNumber;
        if(userRepository.existsByCertificationNumber(result)) result = randomNumber;

        log.info("Certification Number::: {}",result);

        return result;
    }

}