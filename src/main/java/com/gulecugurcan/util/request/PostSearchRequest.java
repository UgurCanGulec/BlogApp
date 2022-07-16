package com.gulecugurcan.util.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequest {
    private String postTitle;
    private String username;
}
