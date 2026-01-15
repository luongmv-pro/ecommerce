package com.luongmv.ecommerce.user.controller;

import com.luongmv.ecommerce.user.dto.UserRegisterRequest;
import com.luongmv.ecommerce.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.luongmv.ecommerce.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        return userService.register(request);
    }
}
