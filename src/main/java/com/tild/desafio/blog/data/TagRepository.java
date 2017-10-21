package com.tild.desafio.blog.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tild.desafio.blog.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByName(String name);
    List<Tag> findByNameIn(List<String> names);
}
