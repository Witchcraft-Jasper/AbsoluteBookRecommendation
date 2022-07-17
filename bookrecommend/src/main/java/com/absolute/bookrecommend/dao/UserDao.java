package com.absolute.bookrecommend.dao;

import com.absolute.bookrecommend.entity.*;
import com.absolute.bookrecommend.result.Result;
import com.absolute.bookrecommend.result.ResultCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UserDao {

    private static final int USER_NOT_EXIST = 1;
    private static final int PASSWORD_WRONG = 2;
    private static final int LOGIN_SUCCESSFUL = 3;
    private static final int USER_REGISTERED = 4;
    private static final int REGISTER_SUCCESSFUL = 5;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 用户登录
     * <br/>没问题
     *
     * @param user
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 20:16
     */
    public Result userLogin(User user) {
        System.out.println(user.getUserName() + " " + user.getPassword());
        Query query = Query.query(Criteria.where("username").is(user.getUserName()));
        User u = mongoTemplate.findOne(query, User.class);
        if (u == null) {
            System.out.println("user not exists");
            return new Result(ResultCode.SUCCESS.getCode(), "用户不存在", USER_NOT_EXIST);
        } else if (!u.getPassword().equals(user.getPassword())) {
            return new Result(ResultCode.SUCCESS.getCode(), "密码错误", PASSWORD_WRONG);
        } else {
            return new Result(ResultCode.SUCCESS.getCode(), "登陆成功", LOGIN_SUCCESSFUL, u.getUserId());
        }
    }

    /**
     * 用户注册
     * <br/>没问题
     *
     * @param user
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 21:07
     */
    public Result userRegister(User user) {
        Query query = Query.query(Criteria.where("username").is(user.getUserName()));
        if (mongoTemplate.exists(query, User.class))
            return new Result(ResultCode.SUCCESS.getCode(), "用户已被注册", USER_REGISTERED);
        else {
            mongoTemplate.save(user, "User");
            query.fields().include("user_id");
            Integer userId = Objects.requireNonNull(mongoTemplate.findOne(query, User.class)).getUserId();
            // 生成空的详情加到UserDetail表里
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(userId);
            mongoTemplate.insert(userDetails, "UserDetail");
            // 生成空的购物车到ShoppingCar表里
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setUserId(userId);
            mongoTemplate.insert(shoppingCar, "ShoppingCar");
            // 生成空的推荐列表到Recommend表里
            Recommend recommend = new Recommend();
            recommend.setUserId(user.getUserId());
            mongoTemplate.insert(recommend, "Recommend");
            return new Result(ResultCode.SUCCESS.getCode(), "注册成功", REGISTER_SUCCESSFUL, userId);
        }
    }

    /**
     * 修改用户密码
     * <br/>未测试
     *
     * @param user
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/13 10:06
     */
    public Result modifyPassword(User user) {
        if (user == null) return new Result(ResultCode.SUCCESS.getCode(), "信息为空，修改失败");
        Query query = Query.query(Criteria.where("user_id").is(user.getUserId()));
        Update update = new Update().set("password", user.getPassword());
        mongoTemplate.updateFirst(query, update, User.class);
        return new Result(ResultCode.SUCCESS.getCode(), "修改密码成功");
    }

    /**
     * 根据指定用户id获取详情
     * <br/>未测试
     *
     * @param userId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 16:58
     */
    public Result getUserDetails(Integer userId) {
        System.out.println(userId);
        Query query = Query.query(Criteria.where("user_id").is(userId));
        UserDetails userDetails = mongoTemplate.findOne(query, UserDetails.class);
        if (userDetails == null) return new Result(ResultCode.SUCCESS.getCode(), "用户不存在");
        return new Result(ResultCode.SUCCESS.getCode(), "获取用户详情成功", userDetails);
    }

    /**
     * 修改用户详情
     * <br/>未测试
     *
     * @param userDetails
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/13 9:32
     */
    public Result updateUserDetails(UserDetails userDetails) {
        if (userDetails == null) return new Result(ResultCode.SUCCESS.getCode(), "信息为空，修改失败");
        Integer userId = userDetails.getUserId();
        Query query = Query.query(Criteria.where("user_id").is(userId));
        if (mongoTemplate.remove(query, "UserDetail").getDeletedCount() == 0)
            return new Result(ResultCode.SUCCESS.getCode(), "没有该用户，修改失败");
        mongoTemplate.insert(userDetails, "UserDetail");
        return new Result(ResultCode.SUCCESS.getCode(), "修改成功");
    }

    /**
     * 获取购用户的购物车信息
     * <br/>未测试
     *
     * @param userId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/12 9:31
     */
    public Result getShoppingCar(Integer userId) {
        Query query = Query.query(Criteria.where("user_id").is(userId));
        ShoppingCar shoppingCar = mongoTemplate.findOne(query, ShoppingCar.class);
        if (shoppingCar == null) return new Result(ResultCode.SUCCESS.getCode(), "用户不存在");
        List<BookNum> books = shoppingCar.getBooks();
        JSONObject results = new JSONObject();
        for (int i = 0; i < books.size(); i++) {
            BookNum bookNum = books.get(i);
            JSONObject obj = new JSONObject();
            Book book = findBookById(bookNum.getBookId());
            BookDetails bookDetail = findBookDetailsById(bookNum.getBookId());
            if (book == null || bookDetail == null) continue;
            obj.put("book", book);
            obj.put("bookDetail", bookDetail);
            obj.put("num", bookNum.getNum());
            results.put("obj" + i, obj);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "获取购物车信息成功", results);
    }

    /**
     * <br/>更新购物车信息
     *
     * @param userId
     * @param bookId
     * @param num
     * @param operation （0改成num个，1添加num个）
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/12 14:45
     */
    public Result updateShoppingCar(Integer userId, Integer bookId, Integer num, Integer operation) {
        Query query = new Query(Criteria.where("user_id").is(userId));
        ShoppingCar shoppingCar = mongoTemplate.findOne(query, ShoppingCar.class);
        if (shoppingCar == null) return new Result(ResultCode.SUCCESS.getCode(), "用户不存在");
        List<BookNum> books = shoppingCar.getBooks();
        BookNum bookNum = null;
        for (BookNum book : books) {
            if (book.getBookId().equals(bookId)) {
                bookNum = book;
                break;
            }
        }
        if (bookNum == null) {
            // 购物车中没有这条，需要新加，operation是0/1不影响√
            bookNum = new BookNum(bookId, num);
            Update update = new Update().push("books", bookNum);
            mongoTemplate.upsert(query, update, ShoppingCar.class);
        } else if (num == 0) {
            // 数量变为0，需要从表中删除√
            Update update = new Update().pull("books", bookNum);
            mongoTemplate.updateFirst(query, update, ShoppingCar.class);
        } else if (operation == 0) {
            // 购物车有这条，且operation是set，直接设置为num
            Update update = new Update().pull("books", bookNum);
            mongoTemplate.updateFirst(query, update, ShoppingCar.class);
            bookNum.setNum(num);
            update.push("books", bookNum);
            mongoTemplate.updateFirst(query, update, ShoppingCar.class);
        } else {
            // 购物车有这条，且operation是add，在原数据上加num
            Update update = new Update().pull("books", bookNum);
            mongoTemplate.updateFirst(query, update, ShoppingCar.class);
            bookNum.setNum(bookNum.getNum() + num);
            update.push("books", bookNum);
            mongoTemplate.updateFirst(query, update, ShoppingCar.class);
        }
        return new Result(ResultCode.SUCCESS.getCode(), "更新购物车信息成功");
    }

    /**
     * 写入订单信息
     * <br/>
     *
     * @param comment
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/14 14:19
     */
    public Result insertComment(Comment comment) {
        if (comment == null) return new Result(ResultCode.SUCCESS.getCode(), "信息为空，插入失败");
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        comment.setDate(sdf.format(new Date()));
        mongoTemplate.insert(comment, "Comment");
        Query query = new Query(Criteria.where("book_id").is(comment.getBookId()));
        query.fields().include("avg_score", "rating_num");
        BookDetails bookDetail = mongoTemplate.findOne(query, BookDetails.class);
        assert bookDetail != null;
        bookDetail.setAvgScore((bookDetail.getAvgScore() * bookDetail.getRatingNum() + comment.getScore()) / bookDetail.getRatingNum() + 1);
        bookDetail.setRatingNum(bookDetail.getRatingNum() + 1);
        Update update = new Update().set("avg_score", bookDetail.getAvgScore()).set("rating_num", bookDetail.getRatingNum());
        mongoTemplate.updateFirst(query, update, BookDetails.class);
        return new Result(ResultCode.SUCCESS.getCode(), "评论成功");
    }

    /**
     * 生成并写入订单信息
     * <br/>未测试
     *
     * @param orders
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/13 9:17
     */
    public Result generateOrder(List<Order> orders) {
        if (orders.size() == 0) return new Result(ResultCode.SUCCESS.getCode(), "信息为空，生成失败");
        // 强行自增order_id
        Query query = new Query().with(Sort.by(Sort.Order.desc("order_id")));
        query.fields().include("order_id");
        Integer id = Objects.requireNonNull(mongoTemplate.findOne(query, Order.class)).getOrderId() + 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        for (Order order : orders) {
            order.setOrderId(id);
            order.setDate(sdf.format(new Date()));
            mongoTemplate.insert(order, "Order");
        }
        return new Result(ResultCode.SUCCESS.getCode(), "生成订单成功");
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

    /**
     * 工具方法，在空的ShoppingCar表里添加用户，且其books为空，已经用不到了。
     * <br/>No problem
     *
     * @param
     * @return void
     * @author Ireland
     * @date 2022/7/14 9:16
     */
    public void generateShoppingCar() {
        Query query = new Query();
        query.fields().include("user_id");
        List<User> users = mongoTemplate.find(query, User.class);
        List<ShoppingCar> list = new ArrayList<>(users.size());
        for (User user : users) {
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setUserId(user.getUserId());
            shoppingCar.setBooks(new ArrayList<>());
            list.add(shoppingCar);
        }
        mongoTemplate.insert(list, "ShoppingCar");
    }

    /**
     * 工具方法，生成用户的三个维度数据写到数据库里，聚类用，用不到了。
     * <br/>No problem
     *
     * @param
     * @return void
     * @author Ireland
     * @date 2022/7/14 15:55
     */
    public void userDimension() {
//        List<UserDimension> list = mongoTemplate.findAll(UserDimension.class);
//        for (UserDimension u : list) {
//            Query query = Query.query(Criteria.where("user_id").is(u.getUserId()));
//            Update update = new Update().set("avgMoney", (u.getAvgMoney() - 237.39305479499427) / 169.39830089980165)
//                    .set("orderNum", (u.getOrderNum() - 2.4994996497548283) / 1.5787556740863329)
//                    .set("avgRating", (u.getAvgRating() - 3.8026960622014947) / 0.5302973763829282);
//            mongoTemplate.updateFirst(query, update, UserDimension.class);
//        }

//        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("_class").avg("avgRating").as("avg").stdDevPop("avgRating").as("std"));
//        DBObject u = mongoTemplate.aggregate(aggregation, "UserDimension", BasicDBObject.class).getMappedResults().get(0);
//        System.out.println(u.get("avg"));
//        System.out.println(u.get("std"));

//        Aggregation aggregation1 = Aggregation.newAggregation(Aggregation.group("user_id").avg("score").as("avgRating"), Aggregation.sort(Sort.by(Sort.Order.asc("_id"))));
//        List<BasicDBObject> results1 = mongoTemplate.aggregate(aggregation1, "Comment", BasicDBObject.class).getMappedResults();
//        Aggregation aggregation2 = Aggregation.newAggregation(Aggregation.group("order_id").avg("user_id").as("user_id").sum("sum").as("sum"),
//                Aggregation.group("user_id").count().as("orderNum").avg("sum").as("avgMoney"), Aggregation.sort(Sort.by(Sort.Order.asc("_id"))));
//        List<BasicDBObject> results2 = mongoTemplate.aggregate(aggregation2, "Order", BasicDBObject.class).getMappedResults();
//        for (DBObject obj1 : results1) {
//            Long id = (Long) obj1.get("_id");
//            for (DBObject obj2 : results2) {
//                if (((Double) obj2.get("_id")).longValue() == id) {
//                    obj1.put("orderNum", obj2.get("orderNum"));
//                    obj1.put("avgMoney", obj2.get("avgMoney"));
//                    break;
//                }
//            }
//            UserDimension userDimension = new UserDimension();
//            userDimension.setUserId(id.intValue());
//            userDimension.setAvgMoney(obj1.get("avgMoney") == null ? 0.0 : (Double) obj1.get("avgMoney"));
//            userDimension.setAvgMoney((userDimension.getAvgMoney() - 237.39305479499427) / 169.39830089980165);
//            userDimension.setAvgRating(obj1.get("avgRating") == null ? 0.0 : (Double) obj1.get("avgRating"));
//            userDimension.setAvgRating((userDimension.getAvgRating() - 3.8026960622014947) / 0.5302973763829282);
//            userDimension.setOrderNum(obj1.get("orderNum") == null ? 0 : ((Integer) obj1.get("orderNum")).doubleValue());
//            userDimension.setOrderNum((userDimension.getOrderNum() - 2.4994996497548283) / 1.5787556740863329);
//            mongoTemplate.insert(userDimension, "UserDimension");
    }
}
