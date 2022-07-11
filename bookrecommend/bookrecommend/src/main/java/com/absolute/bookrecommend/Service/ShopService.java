package com.absolute.bookrecommend.Service;

import com.absolute.bookrecommend.DAO.ShopDao;
import com.absolute.bookrecommend.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopService {

    @Autowired
    ShopDao shopDao;

    public Result getShopDetails(Integer shopId) {
        return shopDao.getShopDetails(shopId);
    }

    public Result getShopBookList(Integer shopId) {
        return shopDao.getShopBookList(shopId);
    }
}
