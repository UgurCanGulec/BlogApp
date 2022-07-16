package com.gulecugurcan.controller;

import com.gulecugurcan.dto.PostDTO;
import com.gulecugurcan.service.PostService;
import com.gulecugurcan.util.request.PostSearchRequest;
import com.gulecugurcan.util.request.SaveOrUpdatePostRequest;
import com.gulecugurcan.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    @Operation(description = "save new post")
    public ResponseEntity<BaseResponse<PostDTO>> createPosts(SaveOrUpdatePostRequest request) {
        PostDTO postDTO = postService.createPost(request.getPostDTO());
        return ResponseEntity.ok(new BaseResponse<>(postDTO));
    }

    @GetMapping("/post/{id}")
    @Operation(description = "get post by id")
    public ResponseEntity<BaseResponse<PostDTO>> getPostById(@PathVariable("id") Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return ResponseEntity.ok(new BaseResponse<>(postDTO));
    }

    @DeleteMapping("/post/{id}")
    @Operation(description = "delete post by id")
    public ResponseEntity<BaseResponse<Boolean>> deletePostById(@PathVariable("id") Long postId) {
        Boolean result = postService.deletePostById(postId);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @PutMapping("/update-post")
    @Operation(description = "update post")
    public ResponseEntity<BaseResponse<PostDTO>> updatePost(@RequestBody SaveOrUpdatePostRequest request) {
        PostDTO postDTO = postService.updatePost(request.getPostDTO());
        return ResponseEntity.ok(new BaseResponse<>(postDTO));
    }

    @GetMapping("/all")
    @Operation(description = "get all posts by filter")
    public ResponseEntity<BaseResponse<Page<PostDTO>>> getPosts(@ModelAttribute PostSearchRequest request, Pageable pageable) {
        Page<PostDTO> dtoList = postService.getPostList(request, pageable);
        return ResponseEntity.ok(new BaseResponse<>(dtoList));
    }
}
