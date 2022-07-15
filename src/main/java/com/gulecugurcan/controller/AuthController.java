package com.gulecugurcan.controller;

import com.gulecugurcan.dto.UserDTO;
import com.gulecugurcan.service.AuthService;
import com.gulecugurcan.util.request.LoginRequest;
import com.gulecugurcan.util.request.RegisterRequest;
import com.gulecugurcan.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    @Operation(description = "SignUp To Blog")
    public ResponseEntity<BaseResponse<UserDTO>> signUp(@RequestBody RegisterRequest registerRequest) {
        UserDTO userDTO = authService.signUp(registerRequest);
        return ResponseEntity.ok(new BaseResponse<>(userDTO));
    }

    @PostMapping("/login")
    @Operation(description = "Login To Blog")
    public ResponseEntity<BaseResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(new BaseResponse<>(token));
    }
}
