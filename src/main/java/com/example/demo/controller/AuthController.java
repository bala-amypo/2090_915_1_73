package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        User user = userService.authenticate(
                request.getUsername(),
                request.getPassword()
        );
        return jwtTokenProvider.generateToken(user);
    }
}
