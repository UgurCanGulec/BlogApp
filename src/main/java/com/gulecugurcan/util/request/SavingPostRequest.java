package com.gulecugurcan.util.request;

import com.gulecugurcan.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingPostRequest {
    private PostDTO postDTO;
}
