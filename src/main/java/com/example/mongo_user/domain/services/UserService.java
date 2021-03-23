package com.example.mongo_user.domain.services;

import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.repositories.UserRepository;
import com.example.mongo_user.domain.utils.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    public ArrayList<UserDTO> getAll(){
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User item : userRepository.findAll()){
            userDTOS.add(UserMapper.toUserDTO(item));
        }
        return userDTOS;
    }

    public void createUser(UserDTO userDTO) {
        User user = User.builder().id(userDTO.getId()).name(userDTO.getName()).userName(userDTO.getUserName()).password(userDTO.getPassword()).roleName(userDTO.getRoleName()).build();

        userRepository.save(user);
    }

    public ResponseEntity<?> login(String use, String pass){
        for (User item : userRepository.findAll()){
            if (item.getUserName().equals(use)){
                String token = generateToken();

                cacheManager.setValue("token1", token);
                return ResponseEntity.ok("ok");
        }
        }
//        User user = userRepository.findByUserName(use);
//
//        System.out.println(user);
//        if(user !=null){
//            String token = generateToken();
//            System.out.println(token);
//            return token;
//        }
        return null;
    }

    public ResponseEntity<?> logout(String token){
        System.out.println(token);
        cacheManager.deleteValue(token);
        return ResponseEntity.ok("logout");
    }

    protected String generateToken() {
        String token = RandomStringUtils.randomAlphabetic(8);
        return token;
    }
}