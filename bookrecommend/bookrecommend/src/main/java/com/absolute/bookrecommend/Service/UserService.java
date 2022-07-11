package com.absolute.bookrecommend.Service;


import com.absolute.bookrecommend.DAO.UserDao;
import com.absolute.bookrecommend.POJO.User;
import com.absolute.bookrecommend.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserService {

    @Autowired
    UserDao userDao;

    public Result userLogin(User user) {
        return userDao.userLogin(user);
    }

    public Result userRegister(User user) {
        return userDao.userRegister(user);
    }

    public Result getUserDetails(Integer userId) {
        return userDao.getUserDetails(userId);
    }
//	public Result findUser(User user) {
//		return userDao.findUser(user);
//	}
//
//	public Result getAllUser() {
//		return userDao.getAllUser();
//	}
//
//	public Result registerUser(User user) {
//		return userDao.registerUser(user);
//	}
//
//	public Result bookRating(Receive receive) {
//		return userDao.bookRating(receive);
//	}
//
//	public Result bookFavorite(Receive receive) {
//		return userDao.bookFavorite(receive);
//	}
}
