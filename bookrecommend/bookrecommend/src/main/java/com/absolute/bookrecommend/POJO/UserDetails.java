package com.absolute.bookrecommend.POJO;

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
@Document(collection = "UserDetails")
public class UserDetails implements Serializable {
    private ObjectId id;
    @Field("user_id")
    private Integer userId;
    private Integer gender;
    private Integer age;
    private Integer phone;
    private String address;
}
