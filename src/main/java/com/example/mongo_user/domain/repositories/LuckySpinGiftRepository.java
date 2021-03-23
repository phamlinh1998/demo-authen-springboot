package com.example.mongo_user.domain.repositories;


import com.example.mongo_user.domain.entities.LuckySpinGift;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LuckySpinGiftRepository extends MongoRepository<LuckySpinGift,Integer> {
}
