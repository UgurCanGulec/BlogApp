package com.gulecugurcan.repository;

import com.gulecugurcan.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> getPostByPostIdAndDeleteFlagFalse(Long postId);
}
