function editShopCart(userId, bookId, num, operation){
    $.ajax({
        type: "get",
        url: "/editShopCart",
        data: {userId: userId,bookId: bookId,num: num,operation: operation},
        async: true,
        success: function (obj) {
            let o = $.parseJSON(obj);
            console.log(o.message);
            alert(o.message);
        }
    });
}