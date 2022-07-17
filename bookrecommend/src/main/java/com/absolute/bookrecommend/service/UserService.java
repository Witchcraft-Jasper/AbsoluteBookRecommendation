package com.absolute.bookrecommend.service;


import com.absolute.bookrecommend.dao.UserDao;
import com.absolute.bookrecommend.entity.*;
import com.absolute.bookrecommend.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


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

    public Result modifyPassword(User user) {
        return userDao.modifyPassword(user);
    }

    public Result getUserDetails(Integer userId) {
        return userDao.getUserDetails(userId);
    }

    public Result updateUserDetails(UserDetails userDetails) {
        return userDao.updateUserDetails(userDetails);
    }

    public Result getShoppingCar(Integer userId) {
        return userDao.getShoppingCar(userId);
    }

    public Result updateShoppingCar(Integer userId, Integer bookId, Integer num, Integer operation) {
        return userDao.updateShoppingCar(userId, bookId, num, operation);
    }

    public Result insertComment(Comment comment) {
        return userDao.insertComment(comment);
    }

    public Result generateOrder(List<Order> orders) {
        return userDao.generateOrder(orders);
    }
}
