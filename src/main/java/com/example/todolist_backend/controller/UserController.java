package com.example.todolist_backend.controller;


import com.example.todolist_backend.config.UserAuthenticationProvider;
import com.example.todolist_backend.dto.LoginResponseDTO;
import com.example.todolist_backend.model.User;
import com.example.todolist_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserController(UserService userService, UserAuthenticationProvider userAuthenticationProvider, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@AuthenticationPrincipal User user) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setToken(userAuthenticationProvider.createToken(user));
        return ok(responseDTO);
    }

    @PostMapping("register")
    public void register(@RequestBody User user){
        User newUser = new User();
        newUser.setLogin(user.getLogin());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(newUser);
    }
}
