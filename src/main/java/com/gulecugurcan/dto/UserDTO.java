package com.gulecugurcan.dto;

import com.gulecugurcan.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String encryptedPassword;
    private String email;
    private Set<Post> posts;
}
