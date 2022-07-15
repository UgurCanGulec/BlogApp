package com.gulecugurcan.service;

import com.gulecugurcan.dao.UserDAO;
import com.gulecugurcan.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userDAO.findUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                userDTO.getUsername(),
                userDTO.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                this.getAuthorities("ROLE_USER"));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }
}
