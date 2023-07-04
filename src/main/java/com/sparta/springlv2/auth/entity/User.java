package com.sparta.springlv2.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시퀀스

    @Column(nullable = false, unique = true)
    private String username; // 사용자 이름

    @Column(nullable = false)
    private String password; // 사용자 패스워드

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password,String email,UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email =email;
        this.role = role;
    }
}

