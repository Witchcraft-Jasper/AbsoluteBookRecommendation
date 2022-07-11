package com.absolute.bookrecommend.DAO;


import com.absolute.bookrecommend.POJO.*;
import com.absolute.bookrecommend.Result.Result;
import com.absolute.bookrecommend.Result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class BookDao {

    private static final int HOT_MAX_NUM = 10;
    private static final int HIGH_MAX_NUM = 6;
    private static final int CATEGORY_MAX_NUM = 10;
    private static final int RECOMMENDER_MAX_NUM = 10;

    @Autowired
    private static MongoTemplate mongoTemplate;

    /**
     * 工具方法，根据id返回书籍
     * <br/>未测试
     *
     * @param bookId
     * @return com.absolute.bookrecommend.POJO.Book
     * @author Ireland
     * @date 2022/7/10 22:06
     */
    public static Book findByBookId(Integer bookId) {
        Query query = Query.query(Criteria.where("book_id").is(bookId));
        return mongoTemplate.findOne(query, Book.class);
    }

    /**
     * 获取热门书籍（根据评分数量降序排列）HOT_MAX_NUM本。
     * <br/>未测试
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
     * <br/>未测试
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
     * 获取指定类别的书籍中的随机CATEGORY_MAX_NUM本。
     * <br/>未测试
     *
     * @param categoryId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 13:49
     */
    public Result getCategoryBookList(Integer categoryId) {
        Query query = new Query(Criteria.where("category_id").is(categoryId));
        Category category = mongoTemplate.findOne(query, Category.class);
        assert category != null;
        query.fields().include("book_id");
        ArrayList<BookDetails> bookIds = new ArrayList<>(mongoTemplate.find(query, BookDetails.class));
        ArrayList<Book> results = new ArrayList<>(bookIds.size());
        for (BookDetails id : bookIds) {
            Book book = findByBookId(id.getBookId());
            results.add(book);
        }
        Collections.shuffle(results);
        return new Result(
                ResultCode.SUCCESS.getCode(),
                "获取" + category.getCategoryName() + "书籍成功",
                results.subList(0, Math.min(results.size(), CATEGORY_MAX_NUM)));
    }

    /**
     * 获取指定书籍id的详情信息
     * <br/>未测试
     *
     * @param bookId
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 15:21
     */
    public Result getBookDetails(Integer bookId) {
        Query query = Query.query(Criteria.where("book_id").is(bookId));
        BookDetails bookDetails = mongoTemplate.findOne(query, BookDetails.class);
        return new Result(ResultCode.SUCCESS.getCode(), "获取书籍详情成功", bookDetails);
    }


//    /**
//     * @param
//     * @return
//     * @description: 获取书籍列表，并初始化书籍表（过滤一些不完整的数据）
//     */
//    private ArrayList<Book> getBookList() {
////    initMongoDB();
//        List<Book> books = mongoTemplate.findAll(Book.class);
//        return new ArrayList<>(books);
//    }

//    /**
//     * @param results
//     * @param bookId
//     * @return
//     * @description: 根据推荐列表的bookId获取对应的书籍
//     */
//    public void findBook(ArrayList<Book> results, int bookId) {
//        ArrayList<Book> lists = getBookList();
//        for (Book book : lists) {
//            if (bookId == book.getBookId()) {
//                results.add(book);
//            }
//        }
//    }
//
//    /**
//     * @param userId
//     * @return
//     * @description:获取用户的推荐列表
//     */
//    public Result getUserBookList(int userId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//        List<UserRec> userRecs = mongoTemplate.find(query, UserRec.class);
//        ArrayList<Book> results = new ArrayList<>();
//        if (userRecs.size() > 0) {
//            for (int i = 0; i < userRecs.get(0).getRecs().size(); i++) {
//                findBook(results, userRecs.get(0).getRecs().get(i).getBookId());
//            }
//            return new Result(ResultCode.SUCCESS.getCode(), "获取推荐书籍成功！", results);
//        } else {
//            return new Result(ResultCode.FAIL.getCode(), "获取推荐书籍失败，当前用户尚无推荐记录！");
//        }
//    }
//
//    /**
//     * @description: 获取用户的收藏列表
//     * @param userId
//     * @return
//     */
//	public Result getFavoriteBooks(int userId) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("userId").is(userId));
//		List<User> users = mongoTemplate.find(query, User.class);
//		ArrayList<Book> results = new ArrayList<>();
//		if (users.size() > 0) {
//			for (int i = 0; i < users.get(0).getFavorite().size(); i++) {
//				findBook(results, users.get(0).getFavorite().get(i).getBookId());
//			}
//			return new Result(ResultCode.SUCCESS.getCode(), "获取成功!", results);
//		} else {
//			return new Result(ResultCode.FAIL.getCode(), "获取失败!");
//		}
//	}

//    /**
//     * @param bookId
//     * @return
//     * @description:根据bookId获取书籍详情
//     */
//    public Result getBookByBookId(int bookId) {
//        Query query1 = new Query();
//        query1.addCriteria(Criteria.where("bookId").is(bookId));
//        List<Book> books = mongoTemplate.find(query1, Book.class);
//        Book book = new Book();
//        if (books.size() > 0) {
//            book = books.get(0);
//            return new Result(
//                    ResultCode.SUCCESS.getCode(), "获取成功!未评分", book);
//        }
///*    List<AverageBooks> avgbooks = mongoTemplate.find(query1, AverageBooks.class);
//
//    Query query2 = new Query();
//    query2.addCriteria(Criteria.where("userId").is(userId));
//    List<User> users = mongoTemplate.find(query2, User.class);
//    boolean isRating = false;
//    int myScore = 0;
//    if (users.size() > 0) {
//      User user = users.get(0);
//      for (int i = 0; i < user.getScoreRecord().size(); i++) {
//        if (bookId == user.getScoreRecord().get(i).getBookId()) {
//          System.out.println(user.getScoreRecord().get(i).getBookId());
//          isRating = true;
//          myScore = user.getScoreRecord().get(i).getScore();
//          break;
//        }
//      }
//    }*/
///*    if (avgbooks.size() > 0) {
//      if (!isRating) {
//        return new Result(
//            ResultCode.SUCCESS.getCode(), "获取成功!未评分", book, avgbooks.get(0).getAvgScore());
//      } else {
//        return new Result(
//            ResultCode.SUCCESS.getCode(),
//            "获取成功!已评分",
//            book,
//            avgbooks.get(0).getAvgScore(),
//            myScore,
//            0,
//            0);
//      }
//    }*/
//        return new Result(ResultCode.FAIL.getCode(), "获取失败!", book);
//    }

//    /**
//     * @param bookId
//     * @return
//     * @description: 获取该书籍的相似推荐列表
//     */
//    public Result getBookRecsByBookId(int bookId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("bookId").is(bookId));
//        List<BookRec> lists = mongoTemplate.find(query, BookRec.class);
//        ArrayList<Book> books = new ArrayList<>();
//        if (lists.size() > 0) {
//            for (int i = 0; i < lists.get(0).getRecs().size(); i++) {
//                findBook(books, lists.get(0).getRecs().get(i).getBookId());
//                if (i > RECOMMENDER_MAX_NUM) {
//                    break;
//                }
//            }
//            return new Result(ResultCode.SUCCESS.getCode(), "获取成功!", books);
//        }
//        return new Result(ResultCode.FAIL.getCode(), "该书籍当前不存在相似推荐书籍！", books);
//    }

//    /**
//     * @param userId
//     * @return
//     * @description:获取该用户的实时推荐列表
//     */
//    public Result getStreamRecsByBookId(int userId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//        List<StreamRec> lists = mongoTemplate.find(query, StreamRec.class);
//        ArrayList<Book> books = new ArrayList<>();
//        if (lists.size() > 0) {
//            for (int i = 0; i < lists.get(0).getRecs().size(); i++) {
//                findBook(books, lists.get(0).getRecs().get(i).getBookId());
//                if (i > RECOMMENDER_MAX_NUM) {
//                    break;
//                }
//            }
//        }
//        return new Result(ResultCode.SUCCESS.getCode(), "获取成功!", books);
//    }
}
