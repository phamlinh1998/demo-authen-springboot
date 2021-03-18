package com.example.mongo_user.domain.services;

import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {
    @Autowired
    private UserRepository userRepository;

    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleName(user.getRoleName());
        return userDTO;
    }

}