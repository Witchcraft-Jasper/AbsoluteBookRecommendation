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
@Document(collection = "BookCategory")
public class Category implements Serializable {
    private ObjectId id;
    @Field("category_id")
    private Integer categoryId;
    @Field("name")
    private String categoryName;
}
