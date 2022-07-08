// 获取热门书籍
$.ajax({
    type: "get",
    url: "../js/data.json",
    async: true,
    success: function (str) {
        for (let i = 0; i < 6; i++) {
            var str1 = ".book" + (i + 1).toString();
            var books1 = $(str1).children('.col-lg-3').children('.product__content');
            books1.children('h4').children('a').text(str.books[i % 7].name)
            books1.children('.prize').children('li').text(str.books[i % 7].price);
            books1.children('.prize').children('.old_prize').text(str.books[i % 7].oldPrice);
            $(str1).children('.col-lg-3').children('.product__thumb').children('.first__img').children('img').attr("src", str.books[i % 7].url);
        }
        // const pos = $('#nav-k1.single__product').children('.col-md-4');
        // for (let i = 6; i < ; i++) {
        //     const str2 = ".book" + (i + 7).toString();
        //     const books2 = pos.children(str2).children('.product__content');
        //     books2.children('h4').children('a').text(str.books[i % 7].name)
        //     books2.children('.prize').children('li').text(str.books[i % 7].price);
        //     books2.children('.prize').children('.old_prize').text(str.books[i % 7].oldPrice);
        //     $(str2).children('.product__thumb').children('.first__img').children('img').attr("src", str.books[i % 7].url);
        // }
    }
});


//获取当前书籍
$.ajax({
    type: "get",
    url: "../js/data.json",
    async: true,
    success: function (data) {
        console.log(data.toString());
        for (let i = 6; i < 56; i++) {
            const str2 = ".book" + (i + 1).toString();
            const books2 = $(str2).children('.product__content');
            books2.children('h4').children('a').text(str.books[i % 7].name)
            books2.children('.prize').children('li').text(str.books[i % 7].price);
            books2.children('.prize').children('.old_prize').text(str.books[i % 7].oldPrice);
            $(str2).children('.product__thumb').children('.first__img').children('img').attr("src", str.books[i % 7].url);
        }
    }
});


function showBooksByCategory(kind) {
    console.log("2");
    htmlobj = $.ajax({
        type: "get",
        url: "../js/data.json",
        // data:{type:kind},
        async: true,
        success: function (str) {
            const str1 = "#nav-k" + kind.toString();
            const pos = $(str1).children('.owl-theme').children('.single__product').children('col-lg-3');
            for (let i = 0; i < 10; i++) {
                const str2 = ".book" + (i + 7).toString();
                const books2 = pos.children(str2).children('.product__content');
                books2.children('h4').children('a').text(str.books[i % 8].name)
                books2.children('.prize').children('li').text(str.books[i % 8].price);
                books2.children('.prize').children('.old_prize').text(str.books[i % 8].oldPrice);
                $(str2).children('.product__thumb').children('.first__img').children('img').attr("src", str.books[i % 8].url);
            }

        }
    });
}