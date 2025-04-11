package com.forecast.controller;

import com.forecast.dto.Request.AuthRequest;
import com.forecast.dto.ResponseSimple.AuthResponse;
import com.forecast.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    Logger logger = Logger.getLogger(AuthController.class.getName());

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        if (!"admin".equals(request.getUsername()) || !"password".equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", List.of("ADMIN", "USER"));

        String token = jwtService.generateToken(request.getUsername(), extraClaims);

        logger.info("Token: " + token);

        return ResponseEntity.ok(new AuthResponse(token));
    }

}