package com.forecast.dto.ResponseSimple;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    String token;


    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }
}
