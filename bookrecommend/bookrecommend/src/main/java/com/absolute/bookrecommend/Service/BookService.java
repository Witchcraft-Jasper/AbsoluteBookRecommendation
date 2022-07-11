package com.absolute.bookrecommend.Service;


import com.absolute.bookrecommend.DAO.BookDao;
import com.absolute.bookrecommend.Result.Result;
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

    public Result getCategoryBookList(Integer categoryId) {
        return bookDao.getCategoryBookList(categoryId);
    }

    public Result getBookDetails(Integer bookId) {
        return bookDao.getBookDetails(bookId);
    }
//	public Result getBookList(int state, int page, int size) {
//		return bookDao.getBookList(state,page,size);
//	}

//    public Result getUserBookList(int userId) {
//        return bookDao.getUserBookList(userId);
//    }

//	public Result getFavoriteBooks(int userId) {
//		return bookDao.getFavoriteBooks(userId);
//	}

//    public Result getBookByBookId(int bookId) {
//        return bookDao.getBookByBookId(bookId);
//    }

//    public Result getBookRecsByBookId(int bookId) {
//        return bookDao.getBookRecsByBookId(bookId);
//    }

//    public Result getStreamRecsByBookId(int userId) {
//        return bookDao.getStreamRecsByBookId(userId);
//    }
}

