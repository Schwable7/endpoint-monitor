package com.example.endpointmonitoring.controller;

import com.example.endpointmonitoring.dto.User;
import com.example.endpointmonitoring.jwt.JWTGeneratorInterface;
import com.example.endpointmonitoring.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;
    private final JWTGeneratorInterface jwtGenerator;

    @Autowired
    public LoginController(UserService userService, JWTGeneratorInterface jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        try {
            if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password is empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(jwtGenerator.generateToken(userData));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
