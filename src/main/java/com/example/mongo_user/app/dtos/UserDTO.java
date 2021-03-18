package com.example.mongo_user.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {

    private int id;
    private String name;
    private String userName;
    private String password;
    private String roleName;
}