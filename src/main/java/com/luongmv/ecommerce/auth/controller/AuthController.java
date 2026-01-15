package com.luongmv.ecommerce.auth.controller;

import com.luongmv.ecommerce.auth.dto.LoginRequest;
import com.luongmv.ecommerce.auth.dto.LoginResponse;
import com.luongmv.ecommerce.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}
