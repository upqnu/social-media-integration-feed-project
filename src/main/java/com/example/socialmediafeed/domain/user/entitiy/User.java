package com.example.socialmediafeed.domain.user.entitiy;


import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//todo: 테이블명 변경 필요
@Table(name = "client")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(255) comment '사용자 아이디'", unique = true)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @Column(name = "certification_number", length = 5, unique = true)
    private String certificationNumber;

    @Enumerated(value = EnumType.STRING)
    private IsActive isActive;

    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    @OneToOne
    @JoinColumn(name = "hashtag_id", unique = true)
    private Hashtag hashtag;

    @Builder
    public User(String username,
                String email,
                String password,
                String certificationNumber,
                Hashtag hashtag) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.certificationNumber = certificationNumber;
        this.isActive = IsActive.DISABLED;
        this.authority = Authority.ROLE_MEMBER;
        this.hashtag = hashtag;
    }

    public void validateCertificationNumber(String certificationNumber) {
        if(!this.certificationNumber.equals(certificationNumber))
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
    }

    public void activeAccount() {
        this.isActive = IsActive.ACTIVE;
    }

}