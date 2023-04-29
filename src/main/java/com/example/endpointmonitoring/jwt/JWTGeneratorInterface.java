package com.example.endpointmonitoring.jwt;


import com.example.endpointmonitoring.dto.User;

import java.util.Map;
public interface JWTGeneratorInterface {
    Map<String, String> generateToken(User user);
}