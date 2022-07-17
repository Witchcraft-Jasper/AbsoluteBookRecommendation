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
@Document(collection = "Recommend")
public class Recommend implements Serializable {
    private ObjectId id;
    @Field("user_id")
    private Integer userId;
    @Field("book_id_1")
    private Integer bookId1;
    @Field("book_id_2")
    private Integer bookId2;
    @Field("book_id_3")
    private Integer bookId3;
    @Field("book_id_4")
    private Integer bookId4;
    @Field("book_id_5")
    private Integer bookId5;
    @Field("book_id_6")
    private Integer bookId6;
}
