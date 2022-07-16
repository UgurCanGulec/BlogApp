package com.gulecugurcan.dao;

import com.gulecugurcan.dto.PostDTO;
import com.gulecugurcan.dto.UserDTO;
import com.gulecugurcan.entity.Post;
import com.gulecugurcan.exception.PostInsertionFailException;
import com.gulecugurcan.exception.PostNotFoundException;
import com.gulecugurcan.exception.UserNotFoundException;
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
            com.gulecugurcan.entity.User createUser = userRepository.findByUsernameAndDeleteFlagFalse(user.getUsername())
                    .orElseThrow(PostInsertionFailException::new);
            post.setUser(createUser);
            post.setCreatedBy(createUser.getUsername());
            post = postRepository.save(post);
            return postMapper.postToDTO(post);
        }
        throw new PostInsertionFailException();
    }

    public PostDTO getPostById(Long postId) {
        Post post = postRepository.getPostByPostIdAndDeleteFlagFalse(postId).orElseThrow(PostNotFoundException::new);
        return postMapper.postToDTO(post);
    }

    public void deletePost(Long postId, UserDTO userDTO) {
        Post post = postRepository.getPostByPostIdAndDeleteFlagFalse(postId).orElseThrow(PostNotFoundException::new);
        post.setDeleteFlag(Boolean.TRUE);
        post.setUpdatedBy(userDTO.getUsername());
        postRepository.save(post);
    }

    public PostDTO updatePost(PostDTO postDTO, User user) {
        Post post = postRepository.getPostByPostIdAndDeleteFlagFalse(postDTO.getPostId()).orElseThrow(PostNotFoundException::new);
        com.gulecugurcan.entity.User updateUser = userRepository.findByUsernameAndDeleteFlagFalse(user.getUsername())
                .orElseThrow(UserNotFoundException::new);
        post.setUser(updateUser);
        post.setContent(postDTO.getContent());
        post.setTitle(post.getTitle());
        post.setUpdatedBy(updateUser.getUsername());
        return postMapper.postToDTO(post);
    }
}
