package com.gulecugurcan.util.request;

import com.gulecugurcan.dto.PostDTO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrUpdatePostRequest {
    private PostDTO postDTO;
}
