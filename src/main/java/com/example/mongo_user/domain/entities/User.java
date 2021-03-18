package com.example.mongo_user.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Document(collection = "User")
public class User {
    @Id
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private String roleName;
}