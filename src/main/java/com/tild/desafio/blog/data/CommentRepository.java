package com.tild.desafio.blog.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tild.desafio.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
