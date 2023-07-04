package com.sparta.springlv2.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.springlv2.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}