package com.absolute.bookrecommend.service;

import com.absolute.bookrecommend.dao.ShopDao;
import com.absolute.bookrecommend.result.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopService {

    @Autowired
    ShopDao shopDao;

    public Result getShopDetails(Integer shopId) {
        return shopDao.getShopDetails(shopId);
    }

    public Result getShopBookList(Integer shopId) throws JSONException {
        return shopDao.getShopBookList(shopId);
    }
}
