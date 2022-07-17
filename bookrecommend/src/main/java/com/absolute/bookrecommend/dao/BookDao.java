package com.absolute.bookrecommend.dao;


import com.absolute.bookrecommend.entity.*;
import com.absolute.bookrecommend.result.Result;
import com.absolute.bookrecommend.result.ResultCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class BookDao {

    private static final int HOT_MAX_NUM = 10;
    private static final int HIGH_MAX_NUM = 6;
    private static final int CATEGORY_MAX_NUM = 10;
    private static final int RECOMMENDER_MAX_NUM = 6;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 工具方法，根据id返回书籍
     * <br/>No problem
     *
     * @param bookId
     * @return com.absolute.bookrecommend.POJO.Book
     * @author Ireland
     * @date 2022/7/10 22:06
     */
    public Book findByBookId(Integer bookId) {
        Query query = Query.query(Criteria.where("book_id").is(bookId));
        return mongoTemplate.findOne(query, Book.class);
    }

    /**
     * 获取热门书籍（根据评分数量降序排列）HOT_MAX_NUM本。
     * <br/>No problem
     *
     * @param
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 13:27
     */
    public Result getHotBookList() {
        Query query = new Query().with(Sort.by(Sort.Order.desc("rating_num"))).limit(HOT_MAX_NUM);
        query.fields().include("book_id");
        ArrayList<BookDetails> bookIds = new ArrayList<>(mongoTemplate.find(query, BookDetails.class));
        ArrayList<Book> results = new ArrayList<>(bookIds.size());
        for (BookDetails id : bookIds) {
            Book book = findByBookId(id.getBookId());
            results.add(book);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "获取最热书籍成功", results);
    }

    /**
     * 获取高分书籍（根据评分平均数降序排列）HIGH_MAX_NUM本。
     * <br/>No problem
     *
     * @param
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 13:57
     */
    public Result getHighScoreBookList() {
        Query query = new Query().with(Sort.by(Sort.Order.desc("avg_score"))).limit(HIGH_MAX_NUM);
        query.fields().include("book_id");
        ArrayList<BookDetails> bookIds = new ArrayList<>(mongoTemplate.find(query, BookDetails.class));
        ArrayList<Book> results = new ArrayList<>(bookIds.size());
        for (BookDetails id : bookIds) {
            Book book = findByBookId(id.getBookId());
            results.add(book);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "获取高分书籍成功", results);
    }

    /**
     * 根据id个性化推荐
     * <br/>
     *
     * @param userId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/15 11:04
     */
    public Result getRecommendBookList(Integer userId) {
        Query query = new Query(Criteria.where("user_id").is(userId));
        Recommend recommend = mongoTemplate.findOne(query, Recommend.class);
        if (recommend == null) return new Result(ResultCode.SUCCESS.getCode(), "用户不存在");
        if (recommend.getBookId1() == null) {
            Aggregation random = Aggregation.newAggregation(Aggregation.sample(RECOMMENDER_MAX_NUM));
            List<Book> results = mongoTemplate.aggregate(random, "BookInfo", Book.class).getMappedResults();
            return new Result(ResultCode.SUCCESS.getCode(), "新用户随机个性化推荐", results);
        }
        Query query1 = Query.query(Criteria.where("book_id").is(recommend.getBookId1()));
        Query query2 = Query.query(Criteria.where("book_id").is(recommend.getBookId2()));
        Query query3 = Query.query(Criteria.where("book_id").is(recommend.getBookId3()));
        Query query4 = Query.query(Criteria.where("book_id").is(recommend.getBookId4()));
        Query query5 = Query.query(Criteria.where("book_id").is(recommend.getBookId5()));
        Query query6 = Query.query(Criteria.where("book_id").is(recommend.getBookId6()));
        Book book1 = mongoTemplate.findOne(query1, Book.class);
        Book book2 = mongoTemplate.findOne(query2, Book.class);
        Book book3 = mongoTemplate.findOne(query3, Book.class);
        Book book4 = mongoTemplate.findOne(query4, Book.class);
        Book book5 = mongoTemplate.findOne(query5, Book.class);
        Book book6 = mongoTemplate.findOne(query6, Book.class);
        List<Book> results = new ArrayList<>();
        results.add(book1);
        results.add(book2);
        results.add(book3);
        results.add(book4);
        results.add(book5);
        results.add(book6);
        return new Result(ResultCode.SUCCESS.getCode(), "推荐成功", results);
    }

    /**
     * 获取指定类别的书籍中的随机CATEGORY_MAX_NUM本。
     * <br/>No problem
     *
     * @param categoryId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 13:49
     */
    public Result getCategoryBookList(Integer categoryId) {
        Query query = new Query(Criteria.where("category_id").is(categoryId));
        Category category = mongoTemplate.findOne(query, Category.class);
        if (category == null) return new Result(ResultCode.SUCCESS.getCode(), "该类别不存在");
        // 聚合查询：指定id、限定字段、限制个数且随机。
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(Criteria.where("category_id").is(categoryId)));
        operations.add(Aggregation.project("book_id"));
        operations.add(Aggregation.sample(CATEGORY_MAX_NUM));
        Aggregation random = Aggregation.newAggregation(operations);
        List<BookDetails> bookIds = mongoTemplate.aggregate(random, "BookDetail", BookDetails.class).getMappedResults();
        ArrayList<Book> results = new ArrayList<>(bookIds.size());
        for (BookDetails id : bookIds) {
            Book book = findByBookId(id.getBookId());
            results.add(book);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "获取" + category.getCategoryName() + "书籍成功", results);
    }

    /**
     * 获取指定书籍id的详情信息
     * <br/>No problem
     *
     * @param bookId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 15:21
     */
    public Result getBookDetails(Integer bookId) {
        Query query = Query.query(Criteria.where("book_id").is(bookId));
        BookDetails bookDetails = mongoTemplate.findOne(query, BookDetails.class);
        query.fields().include("name");
        Book book = mongoTemplate.findOne(query, Book.class);
        if (book == null || bookDetails == null) return new Result(ResultCode.SUCCESS.getCode(), "书籍不存在");
        JSONObject obj = new JSONObject();
        obj.put("bookDetail", bookDetails);
        obj.put("name", book.getName());
        return new Result(ResultCode.SUCCESS.getCode(), "获取书籍详情成功", obj);
    }

    /**
     * 模糊查找书籍名称
     * <br/>No problem
     *
     * @param info
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/13 16:34
     */
    public Result searchBooks(String info) {
        Pattern pattern = Pattern.compile("^.*" + info + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("name").regex(pattern));
        List<Book> books = mongoTemplate.find(query, Book.class);
        if (books.size() == 0) return new Result(ResultCode.SUCCESS.getCode(), "没有找到书籍");
        return new Result(ResultCode.SUCCESS.getCode(), "查找书籍成功", books);
    }

    /**
     * 返回点击量最高的推荐方式。
     * <br/>
     * @param
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/15 12:30
     */
    public Result hotRecommend() {
        Query query = new Query().with(Sort.by(Sort.Order.desc("click"))).limit(1);
        String url = Objects.requireNonNull(mongoTemplate.findOne(query, PageView.class)).getPage();
        return new Result(ResultCode.SUCCESS.getCode(), "点击量最高的推荐方式为", url);
    }

    /**
     * 工具方法，统计Comment表中的每本书的评分和数量计算平均分写入BookDetail表中，已经用不到了。
     * <br/>No problem
     *
     * @param
     * @return void
     * @author Ireland
     * @date 2022/7/12 11:26
     */
    public void calBookScore() {
        Query query = new Query().with(Sort.by(Sort.Order.asc("book_id")));
        query.fields().include("book_id").include("score");
        List<Comment> commentList = mongoTemplate.find(query, Comment.class);
        BookDetails book = new BookDetails();
        System.out.println(commentList.size());
        for (Comment c : commentList) {
            int id = c.getBookId();
            if (book.getBookId() == null) {
                book.setBookId(id);
                book.setAvgScore(c.getScore().doubleValue());
                book.setRatingNum(1);
            } else if (book.getBookId() != id) {
                //写入该书数据
                Query q = new Query(Criteria.where("book_id").is(book.getBookId()));
                Update update = Update.update("rating_num", book.getRatingNum()).set("avg_score", book.getAvgScore());
                mongoTemplate.upsert(q, update, BookDetails.class);
                //更新成新的书
                book.setBookId(id);
                book.setAvgScore(c.getScore().doubleValue());
                book.setRatingNum(1);
            } else {
                //更新该书评分信息
                book.setAvgScore((book.getAvgScore() * book.getRatingNum() + c.getScore())
                        / (book.getRatingNum() + 1));
                book.setRatingNum(book.getRatingNum() + 1);
            }
        }
    }
}
