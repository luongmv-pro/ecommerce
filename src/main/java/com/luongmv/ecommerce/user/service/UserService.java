package com.luongmv.ecommerce.user.service;

import com.luongmv.ecommerce.user.dto.UserRegisterRequest;
import com.luongmv.ecommerce.user.dto.UserResponse;
import com.luongmv.ecommerce.user.entity.User;
import com.luongmv.ecommerce.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponse register(UserRegisterRequest request) {

        // 1. Check email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 2. Hash password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. Create entity
        User user = new User(request.getEmail(), encodedPassword);

        // 4. Save
        User savedUser = userRepository.save(user);

        // 5. Map response
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail()
        );
    }
}
