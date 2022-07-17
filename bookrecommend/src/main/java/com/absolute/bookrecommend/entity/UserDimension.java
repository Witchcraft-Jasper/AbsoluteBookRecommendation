package com.absolute.bookrecommend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "UserDimension")
public class UserDimension {
    private ObjectId id;
    @Field("user_id")
    private Integer userId;
    private Double orderNum;
    private Double avgMoney;
    private Double avgRating;
}
