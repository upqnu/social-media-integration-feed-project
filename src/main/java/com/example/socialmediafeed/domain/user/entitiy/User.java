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

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @Column(name = "certification_number", length = 5, unique = true)
    private String certificationNumber;

    @Enumerated(value = EnumType.STRING)
    private IsActive isActive;

    @OneToOne
    @JoinColumn(name = "hashtag_id", unique = true)
    private Hashtag hashtag;

    @Builder
    public User(String email,
                String password,
                String certificationNumber,
                Hashtag hashtag) {
        this.email = email;
        this.password = password;
        this.certificationNumber = certificationNumber;
        this.isActive = IsActive.DISABLED;
        this.hashtag = hashtag;
    }

}