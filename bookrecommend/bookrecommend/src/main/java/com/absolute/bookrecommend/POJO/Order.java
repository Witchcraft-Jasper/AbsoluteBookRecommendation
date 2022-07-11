package com.absolute.bookrecommend.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Order")
public class Order {
    private ObjectId id;
    @Field("order_id")
    private Integer orderId;
    @Field("user_id")
    private Integer userId;
    private Date time;
    private Double payment;
    List<Map<Book, Pair<Integer, Double>>> books;
}
