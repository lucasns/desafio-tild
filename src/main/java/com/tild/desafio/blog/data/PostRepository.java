package com.tild.desafio.blog.data;

import com.tild.desafio.blog.model.Post;
import com.tild.desafio.blog.model.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTags(Tag tag);
    List<Post> findByTagsIn(List<Tag> tags);
}
