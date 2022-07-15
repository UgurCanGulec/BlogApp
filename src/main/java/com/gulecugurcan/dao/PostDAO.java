package com.gulecugurcan.dao;

import com.gulecugurcan.dto.PostDTO;
import com.gulecugurcan.entity.Post;
import com.gulecugurcan.exception.PostInsertionFailException;
import com.gulecugurcan.mapper.PostMapper;
import com.gulecugurcan.repository.PostRepository;
import com.gulecugurcan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    public PostDTO createPost(PostDTO postDTO, User user) {
        if (postDTO != null) {
            Post post = postMapper.dtoToPost(postDTO);
            com.gulecugurcan.entity.User createUser = userRepository.findByUsername(user.getUsername()).orElseThrow(PostInsertionFailException::new);
            post.setUser(createUser);
            post.setCreatedBy(createUser.getUsername());
            post = postRepository.save(post);
            return postMapper.postToDTO(post);
        }
        throw new PostInsertionFailException();
    }
}
