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
@Document(collection = "ShopDetail")
public class ShopDetails implements Serializable {
    private ObjectId id;
    @Field("shop_id")
    private Integer shopId;
    @Field("book_id")
    private Integer bookId;
}
