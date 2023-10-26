package com.example.socialmediafeed.domain.user.service;

import com.example.socialmediafeed.domain.user.dto.ApprovalReqDto;
import com.example.socialmediafeed.domain.user.entitiy.User;
import com.example.socialmediafeed.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserApprovalService {

    private final UserRepository userRepository;

    @Transactional
    public void approve(Long id, ApprovalReqDto approvalReqDto) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        user.validateCertificationNumber(approvalReqDto.getCertificationNumber());
        user.activeAccount();
    }

}
