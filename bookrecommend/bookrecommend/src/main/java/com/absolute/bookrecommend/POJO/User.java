package com.absolute.bookrecommend.POJO;

import com.absolute.bookrecommend.Annotation.AutoIncKey;
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
@Document(collection = "User")
public class User implements Serializable {
    private ObjectId id;
    @AutoIncKey
    @Field("user_id")
    private Integer userId;
    @Field("username")
    private String userName;
    private String password;
}
