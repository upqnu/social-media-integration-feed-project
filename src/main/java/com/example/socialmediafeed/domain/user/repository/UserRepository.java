package com.example.socialmediafeed.domain.user.repository;

import com.example.socialmediafeed.domain.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByCertificationNumber(String certificationNumber);
    boolean existsByEmail(String email);
}
