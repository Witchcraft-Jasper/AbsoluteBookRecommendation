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
@Document(collection = "Order")
public class Order {
    private ObjectId id;
    @Field("order_id")
    private Integer orderId;
    @Field("user_id")
    private Integer userId;
    @Field("book_id")
    private Integer bookId;
    private Integer quantity;
    private Double sum;
    private String date;
}
