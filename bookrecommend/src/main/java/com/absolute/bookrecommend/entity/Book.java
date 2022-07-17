package com.absolute.bookrecommend.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "BookInfo")
public class Book implements Serializable {
    private ObjectId id;
    @Field("book_id")
    private Integer bookId;
    private String name;
    private String img;
    private Double price;
    @Field("ori_price")
    private Double oriPrice;
}

