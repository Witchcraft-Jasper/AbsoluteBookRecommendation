package com.absolute.bookrecommend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Comment")
public class Comment implements Serializable {
    private ObjectId id;
    @Field("user_id")
    private Integer userId;
    @Field("book_id")
    private Integer bookId;
    private Integer score;
    private String content;
    @Field("comment_date")
    private String date;
}
