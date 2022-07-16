package com.gulecugurcan.dto;

import com.gulecugurcan.entity.Post;
import lombok.*;

import java.util.Set;

@Setter
@Getter
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
