package com.luongmv.ecommerce.user.controller;

import com.luongmv.ecommerce.user.dto.UserRegisterRequest;
import com.luongmv.ecommerce.user.dto.UserResponse;
import com.luongmv.ecommerce.user.entity.User;
import com.luongmv.ecommerce.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.luongmv.ecommerce.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public UserResponse register(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        return userService.register(request);
    }

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(
                user.getId(),
                user.getEmail()
        );
    }
}
