function showBooks(kind) {
    htmlobj = $.ajax({
        type: "get",
        url: "../js/data.json",
        data: {type: kind},
        async: true,
        success: function (data) {
            const container = $('.tab__container');
            const grid = container.children('#nav-grid').children('.row');
            const list = container.children('#nav-list').children('.list__view__wrapper');
            list.append(showGrid(data));
            grid.append()
        }
    });
}

function showTest() {
    showListTest();
    showGridTest();
}

function showListTest() {
    const list = $('.tab__container').children('#nav-list').children('.list__view__wrapper');
    list.html(showList("data"));
}

function showGridTest() {
    const list = $('.tab__container').children('#nav-grid').children('.row');
    list.html(showGrid("data"));
}

function showGrid(data) {
    let products_html = "";
    for (let i = 0; i < 10; i++) {
        let one_product_html =
            "<div class=\"col-lg-4 col-md-4 col-sm-6 col-12\">" +
            "<div class=\"product\">" +
            "<div class=\"product__thumb\">" +
            "<a class=\"first__img\" href=\"description.html\"><img src=\"../images/product/7.jpg\" alt=\"product image\"></a>" +
            "<a class=\"second__img animation1\" href=\"description.html\"><img src=\"../images/product/2.jpg\" alt=\"product image\"></a>" +
            "<div class=\"new__box\">" +
            "<span class=\"new-label\">new</span>" +
            "</div>" +
            "<ul class=\"prize position__right__bottom d-flex\">" +
            "<li>￥40.00</li>" +
            "<li class=\"old_prize\">￥55.00</li>" +
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
            "<h4><a href=\"description.html\">Strive Shoulder Pack</a></h4>" +
            "<ul class=\"rating d-flex\">" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li><i class=\"fa fa-star-o\"></i></li>" +
            "<li><i class=\"fa fa-star-o\"></i></li>" +
            "</ul>" +
            "</div>" +
            "</div>" +
            "</div>";
        products_html = products_html + one_product_html;
    }
    return products_html;
}

function showList(data) {
    let products_html = "";
    for (let i = 0; i < 4; i++) {
        let one_product_html =
            "<div class=\"list__view mt--40\" id=\"book2\">" +
            "<div class=\"thumb\">" +
            "<a class=\"first__img\" href=\"description.html\"><img src=\"../images/product/2.jpg\" alt=\"product images\"></a>" +
            "<a class=\"second__img animation1\" href=\"description.html\"><img src=\"../images/product/4.jpg\" alt=\"product images\"></a>" +
            "</div>" +
            "<div class=\"content\">" +
            "<h2><a href=\"description.html\">Voyage Yoga Bag</a></h2>" +
            "<ul class=\"rating d-flex\">" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>" +
            "<li><i class=\"fa fa-star-o\"></i></li>" +
            "<li><i class=\"fa fa-star-o\"></i></li>" +
            "</ul>" +
            "<ul class=\"prize__box\">" +
            "<li>￥111.00</li>" +
            "<li class=\"old__prize\">￥220.00</li>" +
            "</ul>" +
            "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla augue nec est tristique auctor. Donec non est at libero vulputate rutrum. Morbi ornare lectus quis justo gravida semper. Nulla tellus mi, vulputate adipiscing cursus eu, suscipit id nulla.</p>" +
            "<ul class=\"cart__action d-flex\">" +
            "<li class=\"cart\"><a href=\"#\">Add to cart</a></li>" +
            "<li class=\"wishlist\"><a href=\"#\"></a></li>" +
            "<li class=\"compare\"><a href=\"#\"></a></li>" +
            "</ul>" +
            "</div>" +
            "</div>";
        products_html = products_html + one_product_html;
    }
    return products_html;
}