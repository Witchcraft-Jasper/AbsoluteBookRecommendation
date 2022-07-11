package com.absolute.bookrecommend.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "ShopDetails")
public class ShopDetails implements Serializable {
    private ObjectId id;
    @Field("shop_id")
    private Integer shopId;
    private String img;
    @Field("books")
    private List<Integer> bookIds;
    private Double score;
    @Field("rating_num")
    private Integer ratingNum;
}
