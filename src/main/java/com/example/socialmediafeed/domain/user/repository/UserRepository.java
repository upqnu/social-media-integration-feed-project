package com.example.socialmediafeed.domain.user.repository;

import com.example.socialmediafeed.domain.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByCertificationNumber(String certificationNumber);
    boolean existsByEmail(String email);
}
