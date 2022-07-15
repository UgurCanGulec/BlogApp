package com.gulecugurcan.service;

import com.gulecugurcan.dao.UserDAO;
import com.gulecugurcan.dto.UserDTO;
import com.gulecugurcan.mapper.UserMapper;
import com.gulecugurcan.security.JSONWebTokenProvider;
import com.gulecugurcan.util.request.LoginRequest;
import com.gulecugurcan.util.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JSONWebTokenProvider jsonWebTokenProvider;

    public UserDTO signUp(RegisterRequest registerRequest) {
        UserDTO userDTO = userMapper.mapToUserForRegistration(registerRequest);
        userDTO.setEncryptedPassword(this.encodePassword(registerRequest.getPassword()));
        return userDAO.saveUser(userDTO);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jsonWebTokenProvider.generateToken(authenticate);
    }
}
