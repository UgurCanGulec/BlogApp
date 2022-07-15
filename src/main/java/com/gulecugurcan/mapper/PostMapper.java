package com.gulecugurcan.mapper;

import com.gulecugurcan.dto.PostDTO;
import com.gulecugurcan.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.username", target = "username")
    PostDTO postToDTO(Post post);

    @Mapping(source = "username", target = "user.username")
    Post dtoToPost(PostDTO postDTO);
}
