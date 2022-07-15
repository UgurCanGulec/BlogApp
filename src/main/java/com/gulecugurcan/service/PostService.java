package com.gulecugurcan.service;

import com.gulecugurcan.dao.PostDAO;
import com.gulecugurcan.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostDAO postDAO;
    private final AuthService authService;

    public PostDTO createPost(PostDTO postDTO) {
        User user = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("Oturum açmış kullanıcı bulunamadı !"));
        return postDAO.createPost(postDTO, user);
    }
}
