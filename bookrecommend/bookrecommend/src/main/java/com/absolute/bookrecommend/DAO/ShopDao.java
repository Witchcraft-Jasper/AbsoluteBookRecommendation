package com.absolute.bookrecommend.DAO;

import com.absolute.bookrecommend.POJO.Book;
import com.absolute.bookrecommend.POJO.ShopDetails;
import com.absolute.bookrecommend.Result.Result;
import com.absolute.bookrecommend.Result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        ShopDetails shopDetails = mongoTemplate.findOne(query, ShopDetails.class);
        return new Result(ResultCode.SUCCESS.getCode(), "获取店铺详情成功", shopDetails);
    }

    /**
     * 获取店铺书籍列表
     * <br/>未测试
     *
     * @param shopId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 22:15
     */
    public Result getShopBookList(Integer shopId) {
        Query query = new Query(Criteria.where("shop_id").is(shopId));
        query.fields().include("books");
        List<Integer> bookIds = Objects.requireNonNull(mongoTemplate.findOne(query, ShopDetails.class)).getBookIds();
        ArrayList<Book> results = new ArrayList<>(bookIds.size());
        for (Integer id : bookIds) {
            Book book = BookDao.findByBookId(id);
            results.add(book);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "获取店铺书籍列表成功", results);
    }
}
