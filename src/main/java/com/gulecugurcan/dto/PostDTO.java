package com.gulecugurcan.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private String username;
}
