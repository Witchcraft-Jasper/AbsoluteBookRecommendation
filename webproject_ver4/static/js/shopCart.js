function showShopCart(userId) {
    let htmlobj = $.ajax({
        type: "get",
        url: "/shopCart",
        data: {userId: userId},
        async: true,
        success: function (obj) {
            let o = $.parseJSON(obj);
            let data = o.data;
            console.log(o.message);
            if (data == null) {
                return
            }
            const container = $('.public-shop-cart').children('.content');
            container.append(addShopCartBody(data));
            container.append(addShopCartFooter(data));
        }
    });
}

function showShopCartTest() {
    let container = $('.public-shop-cart').children('.content');
    container.append(addShopCartBody("data"));
    container.append(addShopCartFooter("data"));
}

function addShopCartBody(data) {
    let goods_html = "";
    for (let i = 0; i < 5; i++) {
        let one_good_html =
            "<div class=\"cart-detail\">" +
            "    <input type=\"checkbox\">" +
            "    <div class=\"cart-title\">" +
            "        <img src=\" ../images/details/0.jpg\" alt=\"\">" +
            "        <div>" +
            "            <a href=\"\"><i>HOT</i> 计算机网络:自顶向下方法</a>" +
            "            <span><i class=\"iconfont\"></i> </span>" +
            "        </div>" +
            "        <span>(美)库罗斯,(美)罗斯　著,陈鸣　译</span>" +
            "    </div>" +
            "    <div class=\"cart-price\">￥4999.00</div>" +
            "    <div class=\"cart-number\">" +
            "        <button class=\"sub\">-</button> <input type=\"text\" value=\"1\"><button class=\"plus\">+</button>" +
            "    </div>" +
            "    <div class=\"cart-subtotal\">￥4999.00</div>" +
            "    <div class=\"cart-oprate\"><a href=\"\">删除</a></div>" +
            "</div>";
        goods_html = goods_html + one_good_html;
    }
    return goods_html;
}

function addShopCartFooter(data) {
    //todo xunhuan jisuan data lide dongxi
    let footer =
        "<div class=\"cart-btn\"><span>总价:</span><span> ¥14797.00 </span>" +
        "    <button class=\"btn\">去结算</button>" +
        "</div>";
    return footer;
}