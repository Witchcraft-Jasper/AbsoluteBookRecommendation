package com.absolute.bookrecommend.service;

import com.absolute.bookrecommend.dao.BookDao;
import com.absolute.bookrecommend.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    @Autowired
    BookDao bookDao;

    public Result getHotBookList() {
        return bookDao.getHotBookList();
    }

    public Result getHighBookList() {
        return bookDao.getHighScoreBookList();
    }

    public Result getRecommendBookList(Integer userId) {
        return bookDao.getRecommendBookList(userId);
    }

    public Result getCategoryBookList(Integer categoryId) {
        return bookDao.getCategoryBookList(categoryId);
    }

    public Result getBookDetails(Integer bookId) {
        return bookDao.getBookDetails(bookId);
    }

    public Result searchBooks(String info) {
        return bookDao.searchBooks(info);
    }

    public Result hotRecommend(){return bookDao.hotRecommend();}
}

