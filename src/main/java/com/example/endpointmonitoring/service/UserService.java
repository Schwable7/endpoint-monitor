package com.example.endpointmonitoring.service;

import com.example.endpointmonitoring.dto.User;

public interface UserService {

    User getUserByNameAndPassword(String username, String password);
}
