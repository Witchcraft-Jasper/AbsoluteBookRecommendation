function getAndInitIndexHtml() {
    sessionStorage.setItem("userId", "1");
    getHotBooks();
    getHighScoresBooks();
    getBooksByCategory(1);
}

//获取高分书籍
function getHighScoresBooks() {
    $.ajax({
        type: "get",
        url: "./highScoresBooks",
        async: true,
        timeout: 3000,
        success: function (obj) {
            console.log(obj);
            let body = $.parseJSON((obj));
            let content = body.data;
            console.log(content);
            sessionStorage.setItem("highScoresBooks", content);
            for (let i = 0; i < 6; i++) {
                let str = ".high_scores_book" + (i + 1);
                $(str).attr("id", content[i].bookId + "_high_scores_book");
                let book = $(str).children('.col-lg-3');
                console.log(content.toString());
                // console.log(content[i].toString());
                book.children('.product__content').children('h4').children('a').text(content[i].name);
                book.children('.product__content').children('.prize').children('li').text("￥" + content[i].price);
                book.children('.product__content').children('.prize').children('.old_prize').text("￥" + content[i].oriPrice);
                book.children('.product__thumb').children('.first__img').children('img').attr("src", content[i].img);
            }
        }
    });
}

//获取热门书籍
function getHotBooks() {
    $.ajax({
        type: "get",
        url: "./hotBooks",
        async: true,
        timeout: 3000,
        success: function (obj) {
            console.log(obj);
            let body = $.parseJSON((obj));
            let content = body.data;
            console.log(content);
            sessionStorage.setItem("hotBooks", content);
            for (let i = 0; i < 6; i++) {
                let str = ".hot_book" + (i + 1);
                $(str).attr("id", content[i].bookId + "_hot_book");
                let book = $(str).children('.col-lg-3');
                console.log(content.toString());
                // console.log(content[i].toString());
                book.children('.product__content').children('h4').children('a').text(content[i].name);
                book.children('.product__content').children('.prize').children('li').text("￥" + content[i].price);
                book.children('.product__content').children('.prize').children('.old_prize').text("￥" + content[i].oriPrice);
                book.children('.product__thumb').children('.first__img').children('img').attr("src", content[i].img);
            }
        }
    });
}


function getBooksByCategory(kind) {
    $.ajax({
        type: "get",
        url: "./booksCategory",
        data: {categoryId: kind},
        async: true,
        success: function (obj) {
            console.log(obj);
            let body = $.parseJSON((obj));
            let content = body.data;
            console.log(content);
            sessionStorage.setItem("booksCategory" + kind, content);

            for (let i = 0; i < 10; i++) {
                let str = ".cate_book" + i.toString();
                $(str).attr("id", content[i].bookId + "_hot_book");
                let books = $(str).children('.product__content');
                books.children('h4').children('a').text(content[i].name)
                books.children('.prize').children('li').text(content[i].price);
                books.children('.prize').children('.old_prize').text(content[i].oldPrice);
                $(str).children('.product__thumb').children('.first__img').children('img').attr("src", content[i].img);
            }
        }
    });
}

function onClickCart(obj) {
    let book_div = $(obj).parents('.col-12').eq(0).parent()
    let bookId = parseInt(book_div.attr('id'))
    let userId = sessionStorage.getItem("userId")
    console.log(bookId)
    alert(userId)
    editShopCart(userId,bookId,1,1)
}

function onClickBook(obj) {
    let pos = $(obj).parents('.col-12').eq(0).parent()
    let bookId = parseInt(pos.attr('id'))
    sessionStorage.setItem("bookId", bookId.toString());
}

function onClickSearch() {
    let info = $('.search_text').val();
    sessionStorage.setItem("searchInfo", info);
    sessionStorage.setItem("jumpStatus", "0");
}