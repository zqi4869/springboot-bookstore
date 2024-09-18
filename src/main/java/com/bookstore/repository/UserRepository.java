package com.bookstore.repository;

import com.bookstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByUsername(String username);
}
