package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.exception.UnauthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "User authentication and registration")
public class AuthController {
    
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, 
                         AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        
        User registeredUser = userService.registerUser(user, registerRequest.getRole());
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            // Find user by username or email
            User user;
            try {
                user = userService.findByUsername(authRequest.getUsernameOrEmail());
            } catch (Exception e) {
                // Try to find by email if username fails
                user = userService.findByUsername(authRequest.getUsernameOrEmail());
            }
            
            // Verify password
            if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                throw new UnauthorizedException("Invalid credentials");
            }
            
            // Generate token
            String token = jwtTokenProvider.generateToken(user);
            
            // Create response
            AuthResponse response = new AuthResponse();
            response.setToken(token);
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRoles(user.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList()));
            
            return ResponseEntity.ok(response);
            
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}