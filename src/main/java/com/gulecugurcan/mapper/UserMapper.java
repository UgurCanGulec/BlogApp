package com.gulecugurcan.mapper;

import com.gulecugurcan.dto.UserDTO;
import com.gulecugurcan.entity.User;
import com.gulecugurcan.util.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO mapToUserForRegistration(RegisterRequest registerRequest);

    @Mapping(source = "encryptedPassword", target = "password")
    User dtoToUser(UserDTO userDTO);

    UserDTO userToDTO(User user);
}
