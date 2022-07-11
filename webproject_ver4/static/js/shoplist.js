function showShopBooks(shopId) {
    let htmlobj = $.ajax({
        type: "get",
        url: "./shopBooks",
        data:{shopId: shopId},
        async: true,
        success: function (obj) {
            let body = $.parseJSON(obj);
            let book = body.data.book;
            let book_det = body.data.bookDetail;
            const container = $('.tab__container');
            const grid = container.children('#nav-grid').children('.row');
            const list = container.children('#nav-list').children('.list__view__wrapper');
            list.append(showList(book,book_det));
            grid.append(showGrid(book,book_det));

        }
    });
}

function showTest() {
    showListTest();
    showGridTest();
}

function showListTest() {
    const list = $('.tab__container').children('#nav-list').children('.list__view__wrapper');
    list.append(showList("data","data"));
}

function showGridTest() {
    const list = $('.tab__container').children('#nav-grid').children('.row');
    list.append(showGrid("data","data"));
}

function showGrid(book, book_det) {
    let products_html = "";
    for (let i = 0; i < 10; i++) {
        let bookName = book[i].bookName;
        let price = book[i].price;
        let oriPrice = book[i].oriPrice;
        let img1 = book_det[i].img1;
        let img2 = book_det[i].img2;
        let avgScore = book_det[i].avgScore;
        let one_product_html =
            "<div class=\"col-lg-4 col-md-4 col-sm-6 col-12\">" +
            "<div class=\"product\">" +
            "<div class=\"product__thumb\">" +
            "<a class=\"first__img\" href=\"description.html\"><img src=\"" + img1 + "\" alt=\"product image\"></a>" +
            "<a class=\"second__img animation1\" href=\"description.html\"><img src=\"" + img2 + "\" alt=\"product image\"></a>" +
            "<div class=\"new__box\">" +
            "<span class=\"new-label\">new</span>" +
            "</div>" +
            "<ul class=\"prize position__right__bottom d-flex\">" +
            "<li>￥"+ price +"</li>" +
            "<li class=\"old_prize\">￥"+ oriPrice +"</li>" +
            "</ul>" +
            "<div class=\"action\">" +
            "<div class=\"actions_inner\">" +
            "<ul class=\"add_to_links\">" +
            "<li><a class=\"cart\" href=\"#\"></a></li>" +
            "<li><a class=\"wishlist\" href=\"#\"></a></li>" +
            "<li><a class=\"compare\" href=\"compare.html\"></a></li>" +
            "<li><a data-toggle=\"modal\" title=\"Quick View\" class=\"quickview modal-view detail-link\" href=\"#productmodal\"></a></li>" +
            "</ul>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "<div class=\"product__content\">" +
            "<h4><a href=\"description.html\">"+ bookName +"</a></h4>" +
            "<ul class=\"rating d-flex\">";
        for(let j = 1;j <= 5; j ++) {
            if(j + 0.5 > avgScore) {
                one_product_html = one_product_html + "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>";
            } else {
                one_product_html = one_product_html + "<li><i class=\"fa fa-star-o\"></i></li>";
            }
        }
        one_product_html = one_product_html +
            "</ul>" +
            "</div>" +
            "</div>" +
            "</div>";
        products_html = products_html + one_product_html;
    }
    return products_html;
}

function showList(book, book_det) {
    let products_html = "";
    for (let i = 0; i < 10; i++) {
        let bookName = book[i].bookName;
        let price = book[i].price;
        let oriPrice = book[i].oriPrice;
        let img1 = book_det[i].img1;
        let img2 = book_det[i].img2;
        let avgScore = book_det[i].avgScore;
        let description = book_det[i].description;
        let one_product_html =
            "<div class=\"list__view mt--40\" id=\"book2\">" +
            "<div class=\"thumb\">" +
            "<a class=\"first__img\" href=\"description.html\"><img src=\"" + img1 + "\" alt=\"product images\"></a>" +
            "<a class=\"second__img animation1\" href=\"description.html\"><img src=\"" + img2 + "\" alt=\"product images\"></a>" +
            "</div>" +
            "<div class=\"content\">" +
            "<h2><a href=\"description.html\">"+ bookName +"</a></h2>" +
            "<ul class=\"rating d-flex\">";
        for(let j = 1;j <= 5; j ++) {
            if(j + 0.5 > avgScore) {
                one_product_html = one_product_html + "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>";
            } else {
                one_product_html = one_product_html + "<li><i class=\"fa fa-star-o\"></i></li>";
            }
        }
        one_product_html = one_product_html +
            "</ul>" +
            "<ul class=\"prize__box\">" +
            "<li>￥"+ price +"</li>" +
            "<li class=\"old__prize\">￥"+ oriPrice +"</li>" +
            "</ul>" +
            "<p>"+ description +"</p>" +
            "<ul class=\"cart__action d-flex\">" +
            "<li class=\"cart\"><a href=\"#\">加入购物车</a></li>" +
            "<li class=\"wishlist\"><a href=\"#\"></a></li>" +
            "<li class=\"compare\"><a href=\"#\"></a></li>" +
            "</ul>" +
            "</div>" +
            "</div>";
        products_html = products_html + one_product_html;
    }
    return products_html;
}