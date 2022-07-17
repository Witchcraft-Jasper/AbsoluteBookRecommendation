package com.absolute.bookrecommend.controller;

import com.absolute.bookrecommend.result.Result;
import com.absolute.bookrecommend.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "书籍管理")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @ApiOperation(value = "获取热门书籍列表")
    @GetMapping("/api/hotBooks")
    public Result getHotBookList() {
        return bookService.getHotBookList();
    }

    @ApiOperation(value = "获取高分书籍列表")
    @GetMapping("/api/highScoreBooks")
    public Result getHighBookList() {
        return bookService.getHighBookList();
    }

    @ApiOperation(value = "获取指定用户推荐列表")
    @GetMapping("/api/recommendBooks")
    public Result getRecommendBookList(@RequestParam(value = "userId") Integer userId) {
        return bookService.getRecommendBookList(userId);
    }

    @ApiOperation(value = "获取指定类别书籍列表")
    @GetMapping("/api/category/{categoryId}")
    public Result getCategoryBookList(@PathVariable(value = "categoryId") Integer categoryId) {
        return bookService.getCategoryBookList(categoryId);
    }

    @ApiOperation(value = "获取书籍详情")
    @GetMapping("/api/book/details")
    public Result getBookDetails(@RequestParam(value = "bookId") Integer bookId) {
        return bookService.getBookDetails(bookId);
    }

    @ApiOperation(value = "根据名称查找书籍")
    @GetMapping("/api/book/search/{info}")
    public Result searchBooks(@PathVariable(value = "info") String info) {
        return bookService.searchBooks(info);
    }

    @ApiOperation(value = "展示点击量最高的推荐方法")
    @GetMapping("/api/hotRecommend")
    public Result hotRecommend() {
        return bookService.hotRecommend();
    }
}

