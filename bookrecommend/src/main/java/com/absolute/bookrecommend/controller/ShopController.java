package com.absolute.bookrecommend.controller;

import com.absolute.bookrecommend.result.Result;
import com.absolute.bookrecommend.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "店铺管理")
@RestController
public class ShopController {

    @Autowired
    ShopService shopService;

    @ApiOperation(value = "获取店铺详情")
    @GetMapping("/api/shop/details")
    public Result getBookDetails(@RequestParam(value = "shopId") Integer shopId) {
        return shopService.getShopDetails(shopId);
    }

    @ApiOperation(value = "获取店铺书籍列表")
    @GetMapping("/api/shop/bookList")
    public Result getShopBookList(@RequestParam(value = "shopId") Integer shopId) throws JSONException {
        return shopService.getShopBookList(shopId);
    }
}
