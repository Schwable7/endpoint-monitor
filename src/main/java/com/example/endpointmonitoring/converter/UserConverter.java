package com.example.endpointmonitoring.converter;

import com.example.endpointmonitoring.dto.User;
import com.example.endpointmonitoring.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public User convertEntityToDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    public UserEntity convertDtoToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
