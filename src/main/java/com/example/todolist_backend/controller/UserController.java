package com.example.todolist_backend.controller;


import com.example.todolist_backend.config.UserAuthenticationProvider;
import com.example.todolist_backend.dto.LoginResponseDTO;
import com.example.todolist_backend.model.User;
import com.example.todolist_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UserController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public UserController(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@AuthenticationPrincipal User user) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setToken(userAuthenticationProvider.createToken(user));
        return ok(responseDTO);
    }
}
