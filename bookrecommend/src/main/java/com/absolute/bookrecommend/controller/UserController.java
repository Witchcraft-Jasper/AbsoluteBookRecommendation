package com.absolute.bookrecommend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.absolute.bookrecommend.service.UserService;
import com.absolute.bookrecommend.entity.*;
import com.absolute.bookrecommend.result.Result;

import java.util.List;

@RestController
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "用户登录")
    @RequestMapping("/api/user/login")
    public Result userLogin(@RequestBody User user) {
        return userService.userLogin(user);
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping("/api/user/register")
    public Result userRegister(@RequestBody User user) {
        return userService.userRegister(user);
    }

    @ApiOperation(value = "修改用户密码")
    @RequestMapping("/api/user/update/password")
    public Result modifyPassword(@RequestBody User user) {
        return userService.modifyPassword(user);
    }

    @ApiOperation(value = "获取用户详情")
    @GetMapping("/api/user/details")
    public Result getUserDetails(@RequestParam(value = "userId") String userId) {
        return userService.getUserDetails(Integer.parseInt(userId));
    }

    @ApiOperation(value = "更新用户详情")
    @RequestMapping("/api/user/update/details")
    public Result updateUserDetails(@RequestBody UserDetails userDetails) {
        return userService.updateUserDetails(userDetails);
    }

    @ApiOperation(value = "获取用户的购物车信息")
    @RequestMapping("/api/user/shoppingCar")
    public Result getShoppingCar(@RequestParam(value = "userId") Integer userId) {
        return userService.getShoppingCar(userId);
    }

    @ApiOperation(value = "更新用户的购物车信息")
    @RequestMapping("/api/user/update/shoppingCar")
    public Result updateShoppingCar(@RequestParam Integer userId, @RequestParam Integer bookId, @RequestParam Integer num, @RequestParam Integer operation) {
        return userService.updateShoppingCar(userId, bookId, num, operation);
    }

    @ApiOperation(value = "插入评论信息")
    @RequestMapping("/api/user/insertComment")
    public Result insertComment(@RequestBody Comment comment) {
        return userService.insertComment(comment);
    }

    @ApiOperation(value = "生成订单信息")
    @RequestMapping("/api/user/generateOrder")
    public Result generateOrder(@RequestBody List<Order> orders) {
        return userService.generateOrder(orders);
    }
}
