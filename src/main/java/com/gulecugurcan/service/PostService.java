package com.gulecugurcan.service;

import com.gulecugurcan.constants.ErrorMessageConstants;
import com.gulecugurcan.dao.PostDAO;
import com.gulecugurcan.dao.UserDAO;
import com.gulecugurcan.dto.PostDTO;
import com.gulecugurcan.dto.UserDTO;
import com.gulecugurcan.exception.PostNotFoundException;
import com.gulecugurcan.util.request.PostSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final AuthService authService;

    public PostDTO createPost(PostDTO postDTO) {
        User user = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException(ErrorMessageConstants.NO_USER_FOUND_LOGGED_ID));
        return postDAO.createPost(postDTO, user);
    }

    public PostDTO getPostById(Long postId) {
        return postDAO.getPostById(postId);
    }

    public Boolean deletePostById(Long postId) {
        User user = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException(ErrorMessageConstants.NO_USER_FOUND_LOGGED_ID));
        UserDTO deleteUser = userDAO.findUserByUsername(user.getUsername());
       postDAO.deletePost(postId, deleteUser);
       return Boolean.TRUE;
    }

    public PostDTO updatePost(PostDTO postDTO) {
        User user = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException(ErrorMessageConstants.NO_USER_FOUND_LOGGED_ID));
        return postDAO.updatePost(postDTO, user);
    }

    public Page<PostDTO> getPostList(PostSearchRequest request, Pageable pageable) {
        Page<PostDTO> result = postDAO.findByCriteria(request, pageable);
        if (!result.hasContent()) {
            throw new PostNotFoundException();
        }
        return result;
    }
}
