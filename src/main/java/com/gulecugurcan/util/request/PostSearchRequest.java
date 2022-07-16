package com.gulecugurcan.util.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequest {
    private String postTitle;
    private String username;
}
