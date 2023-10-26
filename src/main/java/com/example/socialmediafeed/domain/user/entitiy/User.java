package com.example.socialmediafeed.domain.user.entitiy;


import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "hashtag_id", unique = true)
    private Hashtag hashtag;

    @Column(name = "certification_number", length = 5, unique = true)
    private String certificationNumber;

    @Enumerated(value = EnumType.STRING)
    private IsActive isActive;

}