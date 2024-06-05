package com.mycoll.mycollections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycoll.mycollections.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
