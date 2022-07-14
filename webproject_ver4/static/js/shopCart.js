function showShopCart(userId) {
    $.ajax({
        type: "get",
        url: "/getShopCart",
        data: {userId: userId},
        async: true,
        success: function (obj) {
            let o = $.parseJSON(obj);
            console.log(obj);
            let data = o.data;
            sessionStorage.setItem("shopCart",data);
            console.log(o.message);
            if (data == null) {
                return
            }
            const container = $('.public-shop-cart').children('.content');
            container.append(addShopCartHtml(data));
            container.append(addShopCartFooter(data));
        }
    });
}

function addShopCartHtml(data) {
    let goods_html = "";
    for (let i = 0; i < data.length; i++) {
        let bookName = data["obj"+i].book.bookName;
        let author = data["obj"+i].bookDetail.author;
        let price = data["obj"+i].book.price;
        let num = data["obj"+i].num;
        let one_good_html =
            "<div class=\"cart-detail\" id=\""+ i +"cart\">" +
            "    <input type=\"checkbox\">" +
            "    <div class=\"cart-title\">" +
            "        <img src=\" ../images/details/0.jpg\" alt=\"\">" +
            "        <div>" +
            "            <a href=\"\"><i>HOT</i>"+ bookName +"</a>" +
            "            <span><i class=\"iconfont\"></i> </span>" +
            "        </div>" +
            "        <span>"+ author +"</span>" +
            "    </div>" +
            "    <div class=\"cart-price\">￥"+ price +"</div>" +
            "    <div class=\"cart-number\">" +
            "        <input id=\"qty\" class=\"input-text qty\" name=\"qty\" min=\"0\" value="+ num +" title=\"Qty\" type=\"number\">" +
            "    </div>" +
            "    <div class=\"cart-subtotal\">￥ "+ num*price +"</div>" +
            "    <div class=\"cart-oprate\">" +
            "        <a onclick='onClickSave(this)'>保存</a>" +
            "    </div>" +
            "</div>";
        goods_html = goods_html + one_good_html;
    }

    return goods_html;
}

function onClickSave(thisObj){
    let cart = $(thisObj).parent().parent();
    let count = parseInt(cart.attr(id));

    console.log(cart.attr("id"));
    console.log(count);

    let userId = sessionStorage.getItem("userId");
    let data = sessionStorage.getItem("shopCart");
    if(data==null || userId==null) {
        alert("请求失败");
        return;
    }
    let bookId = data["obj"+count].book.bookId;
    let num = data["obj"+count].num;

    editShopCart(userId, bookId, num, 0);
    location.reload();
}

function addShopCartFooter(data) {
    let footer =
        "<div class=\"cart-btn\"><span>总价:</span><span> ¥14797.00 </span>" +
        "    <button class=\"btn\">去结算</button>" +
        "</div>";
    return footer;
}