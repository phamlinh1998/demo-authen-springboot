package com.example.mongo_user.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection = "lucky_spin_gift")
public class LuckySpinGift {
    @Transient
    public static final String SEQUENCE_NAME = "lucky_spin_gift_sequence";

    @Id
    private Integer id;

    @Field(name = "item_label")
    private String itemLabel;

    @Field(name = "item_value")
    private String itemValue;

}
