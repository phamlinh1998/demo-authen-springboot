package com.example.mongo_user.app.controllers;

import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/user")
    public ResponseEntity<?> getListUser(){
        ArrayList<UserDTO> userDTOS = userService.getAll();
        return ResponseEntity.ok(userDTOS);
    }
    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO.getUserName(),userDTO.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity Logout(@RequestHeader String token){
        return userService.logout(token);
    }

}