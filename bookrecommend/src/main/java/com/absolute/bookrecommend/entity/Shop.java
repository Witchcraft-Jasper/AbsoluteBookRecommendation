package com.absolute.bookrecommend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Shop")
public class Shop implements Serializable {
    private ObjectId id;
    @Field("shop_id")
    private Integer shopId;
    @Field("shop_name")
    private String shopName;
    @Field("shop_description")
    private String introduction;
    @Field("shop_img")
    private String img;
    @Field("shop_score")
    private Double score;
    @Field("rating_num")
    private Integer ratingNum;
}
