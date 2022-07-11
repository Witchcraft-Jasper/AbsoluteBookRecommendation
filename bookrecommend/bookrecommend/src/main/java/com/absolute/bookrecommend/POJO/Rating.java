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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Rating")
public class Rating implements Serializable {
    private ObjectId id;
    @Field("user_id")
    private Integer userId;
    @Field("book_id")
    private Integer bookId;
    private Double score;
    private String comment;
    @Field("time")
    private Date ratingTime;
}
