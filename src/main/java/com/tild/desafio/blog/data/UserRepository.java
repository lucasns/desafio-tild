package com.tild.desafio.blog.data;

import com.tild.desafio.blog.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameIgnoreCaseContaining(String name);
}
