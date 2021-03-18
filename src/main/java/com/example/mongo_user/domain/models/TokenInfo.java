package com.example.mongo_user.domain.models;

import lombok.Data;

@Data
public class TokenInfo {

    private String userName;
    private String password;

}
