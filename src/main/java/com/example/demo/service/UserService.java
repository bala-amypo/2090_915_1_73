package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user, String roleName) {

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // find or create role
        User.Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    User.Role r = new User.Role();
                    r.setName(roleName);
                    return roleRepository.save(r);
                });

        user.getRoles().add(role);

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
