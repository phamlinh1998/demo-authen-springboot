package com.example.mongo_user.domain.services;


import com.example.mongo_user.app.dtos.LuckySpinGiftDTO;
import com.example.mongo_user.domain.entities.DatabaseSequence;
import com.example.mongo_user.domain.entities.LuckySpinGift;
import com.example.mongo_user.domain.repositories.LuckySpinGiftRepository;
import com.example.mongo_user.domain.utils.CacheKey;
import com.example.mongo_user.domain.utils.CacheManager;
import com.example.mongo_user.domain.utils.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class LuckySpinGiftService {
    @Autowired
    private LuckySpinGiftRepository luckySpinGiftRepository;

    @Autowired private CacheManager cacheManager;
    @Autowired
    protected MongoOperations mongoOperations;
    public List<LuckySpinGift> getLuckySpinGifts() throws IOException {
        List<LuckySpinGift> giftCache = cacheManager.getGift();
        if(giftCache == null){
            log.info("=========== cache");
            List<LuckySpinGift> luckySpinGifts = luckySpinGiftRepository.findAll();
            String key = CacheKey.testCache();
            cacheManager.set(key,JsonParser.toJson(luckySpinGifts));
            return luckySpinGifts;
        }else{
            log.info("===================");
        }

        return giftCache;
    }

    public Integer generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    public Integer create(LuckySpinGiftDTO dto) {

        LuckySpinGift luckySpinGift = new LuckySpinGift();
        luckySpinGift.setId(generateSequence(LuckySpinGift.SEQUENCE_NAME));
        luckySpinGift.setItemLabel(dto.getItemLabel());
        luckySpinGift.setItemValue(dto.getItemValue());
        luckySpinGiftRepository.save(luckySpinGift);
        return luckySpinGift.getId();
    }
}
