package com.absolute.bookrecommend.DAO;

import com.absolute.bookrecommend.POJO.BookDetails;
import com.absolute.bookrecommend.POJO.User;
import com.absolute.bookrecommend.POJO.UserDetails;
import com.absolute.bookrecommend.Result.Result;
import com.absolute.bookrecommend.Result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Random;

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
     * <br/>未测试
     *
     * @param user
     * @return com.absolute.bookrecommend.Result.Result
     * @author Ireland
     * @date 2022/7/10 20:16
     */
    public Result userLogin(User user) {
        Query query = Query.query(Criteria.where("username").is(user.getUserName()));
        User u = mongoTemplate.findOne(query, User.class);
        if (u != null) System.out.println(u.getUserName());
//        User u = null;
        Random random = new Random();
        int s = random.nextInt(100);
        if (s < 7) {
            u = user;
            if (s < 3) u.setPassword("hhg");
        }
        if (u == null)
            return new Result(ResultCode.SUCCESS.getCode(), "用户不存在", USER_NOT_EXIST);
        else if (!u.getPassword().equals(user.getPassword()))
            return new Result(ResultCode.SUCCESS.getCode(), "密码错误", PASSWORD_WRONG);
        else
            return new Result(ResultCode.SUCCESS.getCode(), "登陆成功", LOGIN_SUCCESSFUL);
    }

    /**
     * 用户注册
     * <br/>未测试
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
            return new Result(ResultCode.SUCCESS.getCode(), "注册成功", REGISTER_SUCCESSFUL);
        }
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
        Query query = Query.query(Criteria.where("user_id").is(userId));
        UserDetails userDetails = mongoTemplate.findOne(query, UserDetails.class);
        return new Result(ResultCode.SUCCESS.getCode(), "获取用户详情成功", userDetails);
    }

//	/**
//	 * @param user
//	 * @return
//	 * @description: 判断当该用户是否存在
//	 */
//	public Boolean userIsexist(User user) {
//		boolean flag = false;
//		List<User> users = mongoTemplate.findAll(User.class);
//		if (users.size() > 0) {
//			for (User u : users) {
//				if (user.getUserName().equals(u.getUserName())) {
//					flag = true;
//					break;
//				}
//			}
//		}
//		return flag;
//	}
//
//
//	/**
//	 * @param
//	 * @return
//	 * @description: 查询所有用户
//	 */
//	public Result getAllUser() {
//		List<User> users = mongoTemplate.findAll(User.class);
//
//		if (users.size() > 0) {
//			return new Result(ResultCode.SUCCESS.getCode(), "登录成功!", users);
//		}
//		return new Result(ResultCode.FAIL.getCode(), "登录失败！");
//	}
//
//	/**
//	 * @param user
//	 * @return
//	 * @description:判断用户是否注册成功
//	 */
//	public Result registerUser(User user) {
//		if (userIsexist(user)) {
//			return new Result(ResultCode.EXITED.getCode(), "用户已存在！!");
//		}
//		mongoTemplate.insert(user, "User");
//		Result result = findUser(user);
//		if (result.getCode() == 200) {
//			return new Result(ResultCode.SUCCESS.getCode(), "注册成功!", (User) result.getData());
//		} else {
//			return new Result(ResultCode.EXITED.getCode(), "注册失败!");
//		}
//	}
//
//	/**
//	 * @param receive userId bookId score
//	 * @return
//	 * @description: 将书籍的评分记录存入MongoDb和Redis
//	 */
//	public Result bookRating(Receive receive) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("userId").is(receive.getUserId()));
//		List<User> users = mongoTemplate.find(query, User.class);
//		boolean isRating = false;
//		User user = null;
//		if (users.size() > 0) {
//			user = users.get(0);
//			for (int j = 0; j < user.getScoreRecord().size(); j++) {
//				if (user.getScoreRecord().get(j).getBookId() == receive.getBookId()) {
//					isRating = true;
//					break;
//				}
//			}
//		}
//		if (isRating) {
//			return new Result(ResultCode.FAIL.getCode(), "已经评过了！");
//		} else {
//			redisTemplate
//					.opsForList()
//					.rightPush(
//							"userId:" + receive.getUserId(), receive.getBookId() + ":" + receive.getScore());
//			isPutLogger(receive);
//			SimpleDateFormat sdf = new SimpleDateFormat();
//			user.setScoreRecord(receive.getBookId(), receive.getScore(), sdf.format(new Date()));
//			Update update = new Update();
//			update.set("scoreRecord", user.getScoreRecord());
//			mongoTemplate.upsert(query, update, "User");
//
//			mongoTemplate.insert(
//					new Rating(receive.getUserId(), receive.getBookId(), receive.getScore()), "Rating");
//			//      }
//			return new Result(ResultCode.SUCCESS.getCode(), "评分成功!");
//		}
//		//    else {
////		return new Result(ResultCode.FAIL.getCode(), "评分失败!");
//		//    }
//	}

//	private void isPutLogger(Receive receive) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("bookId").is(receive.getBookId()));
//		List<BookRec> bookRecs = mongoTemplate.find(query, BookRec.class);
//		if (bookRecs.size() > 0) {
//			// 当MongoDB中存在该书籍的推荐列表时，埋点
//			// 用于评分日志埋点，用于flume获取信息
//			System.out.println("============埋点===========");
//			System.out.println("存在推荐列表...");
//			logger.info(
//					"PRODUCT_RATING_PREFIX:"
//							+ receive.getUserId()
//							+ "|"
//							+ receive.getBookId()
//							+ "|"
//							+ receive.getScore());
//		}
//	}
//
//	/**
//	 * @param receive userId bookId
//	 * @return
//	 * @description:收藏书籍
//	 */
//	public Result bookFavorite(Receive receive) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("userId").is(receive.getUserId()));
//		List<User> users = mongoTemplate.find(query, User.class);
//		if (users.size() > 0) {
//			User user = users.get(0);
//			user.setFavorite(receive.getBookId());
//			Update update = new Update();
//			update.set("favorite", user.getFavorite());
//			mongoTemplate.upsert(query, update, "User");
//			return new Result(ResultCode.SUCCESS.getCode(), "收藏成功!");
//		} else {
//			return new Result(ResultCode.FAIL.getCode(), "收藏失败!");
//		}
//	}
}
