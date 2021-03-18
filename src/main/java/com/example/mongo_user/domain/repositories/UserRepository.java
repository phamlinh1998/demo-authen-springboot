package com.example.mongo_user.domain.repositories;

import com.example.mongo_user.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    User findByUserName(String user);
}
