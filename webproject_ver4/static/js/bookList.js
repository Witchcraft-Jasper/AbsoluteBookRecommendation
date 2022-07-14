function showBookList() {
    let type = sessionStorage.getItem("jumpStatus");
    alert(type);
    if(type==0)
        getSearchBooks();
    else if(type==1)
        getShopBooks();
    else
        getShopBooks();
}

function getSearchBooks(){
    let info = sessionStorage.getItem("info");
    if(info==null || info === ""){
        alert("有只小鹿飞走咯");
    }
        $.ajax({
            type: "get",
            url: "/bookSearch",
            data:{info: info},
            async: true,
            success: function (obj) {
                let o = $.parseJSON(obj);
                let data = o.data;
                sessionStorage.setItem("searchBooks",data);
                console.log(o.message);
                console.log(data);
                if(data == null) {
                    return
                }
                const container = $('.tab__container');
                const grid = container.children('#nav-grid').children('.row');
                const list = container.children('#nav-list').children('.list__view__wrapper');
                list.append(showList(data));
                grid.append(showGrid(data));
            }
        });
}

function getShopBooks() {
    let shopId = sessionStorage.getItem("shopId");
    if(shopId==null){
        alert("火烧屁屁咯");
    }
    $.ajax({
        type: "get",
        url: "/shopBooks",
        data:{shopId: shopId},
        async: true,
        success: function (obj) {
            let o = $.parseJSON(obj);
            let data = o.data;
            sessionStorage.setItem("shopBooks",data);
            // console.log(o.message);
            // console.log(data);
            if(data == null) {
                return
            }
            const container = $('.tab__container');
            const grid = container.children('#nav-grid').children('.row');
            const list = container.children('#nav-list').children('.list__view__wrapper');
            list.append(showList(data));
            grid.append(showGrid(data));
        }
    });
}

function showGrid(data) {
    let products_html = "";
    let length;
    if(data.length > 10)
        length = 10;
    else
        length = Object.keys(data).length;
    console.log(data);
    for (let i = 0; i < length; i++) {
        let bookName = data["obj"+(i+1)].book.name;
        let price = data["obj"+(i+1)].book.price;
        let oriPrice = data["obj"+(i+1)].book.oriPrice;
        let img1 = data["obj"+(i+1)].bookDetail.img1;
        let img2 = data["obj"+(i+1)].bookDetail.img2;
        let avgScore = data["obj"+(i+1)].bookDetail.avgScore;
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
                one_product_html = one_product_html + "<li><i class=\"fa fa-star-o\"></i></li>";
            } else {
                one_product_html = one_product_html + "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>";
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

function showList(data) {
    let products_html = "";
    let length;
    if(data.length > 10)
        length = 10;
    else
        length = Object.keys(data).length;
    console.log(data);
    for (let i = 0; i < length; i++) {
        console.log(["obj"+(i+1)]);
        let bookName = data["obj"+(i+1)].book.name;
        let price = data["obj"+(i+1)].book.price;
        let oriPrice = data["obj"+(i+1)].book.oriPrice;
        let img1 = data["obj"+(i+1)].bookDetail.img1;
        let img2 = data["obj"+(i+1)].bookDetail.img2;
        let avgScore = data["obj"+(i+1)].bookDetail.avgScore;
        let description = data["obj"+(i+1)].bookDetail.description;
        let one_product_html =
            "<div class=\"list__view mt--40\">" +
            "<div class=\"thumb\">" +
            "<a class=\"first__img\" href=\"description.html\"><img src=\"" + img1 + "\" alt=\"product images\"></a>" +
            "<a class=\"second__img animation1\" href=\"description.html\"><img src=\"" + img2 + "\" alt=\"product images\"></a>" +
            "</div>" +
            "<div class=\"content\">" +
            "<h2><a href=\"description.html\">"+ bookName +"</a></h2>" +
            "<ul class=\"rating d-flex\">";
        for(let j = 1;j <= 5; j ++) {
            if(j + 0.5 > avgScore) {
                one_product_html = one_product_html + "<li><i class=\"fa fa-star-o\"></i></li>";
            } else {
                one_product_html = one_product_html + "<li class=\"on\"><i class=\"fa fa-star-o\"></i></li>";
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

