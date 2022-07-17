package com.absolute.bookrecommend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Pageview")
public class PageView implements Serializable {
    private ObjectId id;
    private String page;
    private Integer click;
}
