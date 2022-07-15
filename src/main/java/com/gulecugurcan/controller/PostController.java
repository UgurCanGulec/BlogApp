package com.gulecugurcan.controller;

import com.gulecugurcan.dto.PostDTO;
import com.gulecugurcan.service.PostService;
import com.gulecugurcan.util.request.SavingPostRequest;
import com.gulecugurcan.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    @Operation(description = "save new post")
    public ResponseEntity<BaseResponse<PostDTO>> createPosts(SavingPostRequest request) {
        PostDTO postDTO = postService.createPost(request.getPostDTO());
        return ResponseEntity.ok(new BaseResponse<>(postDTO));
    }
}
