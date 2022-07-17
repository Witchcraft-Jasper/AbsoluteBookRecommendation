package com.absolute.bookrecommend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "BookDetail")
public class BookDetails implements Serializable {
    private ObjectId id;
    @Field("book_id")
    private Integer bookId;
    @Field("category_id")
    private Integer categoryId;
    private String author;
    private String publish;
    private Integer year;
    @Field("publish_date")
    private String date;
    private Integer quantity;
    @Field("book_description")
    private String description;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    @Field("shop_id")
    private Integer shopId;
    @Field("comment_num")
    private Integer ratingNum;
    @Field("avg_score")
    private Double avgScore;
}
