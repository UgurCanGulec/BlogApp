package com.gulecugurcan.dao;

import com.gulecugurcan.dto.UserDTO;
import com.gulecugurcan.entity.User;
import com.gulecugurcan.mapper.UserMapper;
import com.gulecugurcan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.dtoToUser(userDTO);
        user = userRepository.save(user);
        return userMapper.userToDTO(user);
    }

    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsernameAndDeleteFlagFalse(username).orElseThrow(() -> new UsernameNotFoundException(username + " isimli kullanıcı bulunamadı !"));
        return userMapper.userToDTO(user);
    }
}
