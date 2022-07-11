package com.absolute.bookrecommend.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "BookDetails")
public class BookDetails implements Serializable {
    private ObjectId id;
    @Field("book_id")
    private Integer bookId;
    @Field("category_id")
    private Integer categoryId;
    @Field("shop_id")
    private Integer shopId;
    private String author;
    private String publish;
    @Field("publish_year")
    private Integer year;
    @Field("publish_date")
    private Date date;
    private Integer quantity;
    private String description;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    @Field("rating_num")
    private Integer ratingNum;
    @Field("avg_score")
    private Double avgScore;
}
