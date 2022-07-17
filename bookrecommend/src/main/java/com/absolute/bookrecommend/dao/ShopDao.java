package com.absolute.bookrecommend.dao;

import com.absolute.bookrecommend.entity.*;
import com.absolute.bookrecommend.result.Result;
import com.absolute.bookrecommend.result.ResultCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取店铺详情
     * <br/>未测试
     *
     * @param shopId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 22:01
     */
    public Result getShopDetails(Integer shopId) {
        Query query = new Query(Criteria.where("shop_id").is(shopId));
        Shop shopDetails = mongoTemplate.findOne(query, Shop.class);
        if (shopDetails == null) return new Result(ResultCode.SUCCESS.getCode(), "店铺不存在");
        return new Result(ResultCode.SUCCESS.getCode(), "获取店铺详情成功", shopDetails);
    }

    /**
     * 获取店铺书籍列表
     * <br/>No problem
     *
     * @param shopId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 22:15
     */
    public Result getShopBookList(Integer shopId) {
        Query query = new Query(Criteria.where("shop_id").is(shopId)).limit(10);
        query.fields().include("book_id");
        List<ShopDetails> bookIds = mongoTemplate.find(query, ShopDetails.class);
        JSONObject results = new JSONObject();
        int i = 1;
        for (ShopDetails id : bookIds) {
            JSONObject obj = new JSONObject();
            Book book = findBookById(id.getBookId());
            BookDetails bookDetail = findBookDetailsById(id.getBookId());
            if (book == null || bookDetail == null) continue;
            obj.put("book", book);
            obj.put("bookDetail", bookDetail);
            results.put("obj" + i++, obj);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "获取店铺书籍列表成功", results);
    }

    /**
     * 工具方法，根据id返回书籍
     * <br/>No problem
     *
     * @param bookId
     * @return com.absolute.bookrecommend.POJO.Book
     * @author Ireland
     * @date 2022/7/10 22:06
     */
    public Book findBookById(Integer bookId) {
        Query query = Query.query(Criteria.where("book_id").is(bookId));
        return mongoTemplate.findOne(query, Book.class);
    }

    /**
     * 工具方法，根据id返回书籍详情
     * <br/>No problem
     *
     * @param bookId
     * @return com.absolute.bookrecommend.POJO.Book
     * @author Ireland
     * @date 2022/7/10 22:06
     */
    public BookDetails findBookDetailsById(Integer bookId) {
        Query query = Query.query(Criteria.where("book_id").is(bookId));
        return mongoTemplate.findOne(query, BookDetails.class);
    }
}
