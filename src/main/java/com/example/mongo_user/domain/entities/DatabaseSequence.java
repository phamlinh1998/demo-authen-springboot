package com.example.mongo_user.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequence")
@Data
public class DatabaseSequence {
    @Id
    private String id;

    private int seq;
}
