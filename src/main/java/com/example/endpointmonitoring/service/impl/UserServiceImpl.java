package com.example.endpointmonitoring.service.impl;

import com.example.endpointmonitoring.converter.UserConverter;
import com.example.endpointmonitoring.dto.User;
import com.example.endpointmonitoring.entity.UserEntity;
import com.example.endpointmonitoring.repository.UserRepository;
import com.example.endpointmonitoring.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsernameAndPassword(username, password);
        if (optionalUserEntity.isEmpty()) {
            throw new EntityNotFoundException("Username or Password is invalid");
        }
        return userConverter.convertEntityToDto(optionalUserEntity.get());
    }


}
