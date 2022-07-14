function showDescription(bookId) {
    $.ajax({
        type: "get",
        url: "./bookDescription",
        data: {bookId: bookId},
        async: true,
        success: function (obj) {
            // console.log(obj);
            let body = $.parseJSON((obj));
            let content = body.data;
            sessionStorage.setItem("bookDescription", content);
            const fotorama = $('#div_img').data('fotorama');
            fotorama.load([
                {img: content.img1, thumb: content.img1},
                {img: content.img2, thumb: content.img2},
                {img: content.img3, thumb: content.img3},
                {img: content.img4, thumb: content.img4},
                {img: content.img5, thumb: content.img5}
            ]);
            $('#book-name').text("作者:" + content.author);
            $('#reserve').text(content.quantity);
            $('#book-id').text(content.bookId);
            $('#desc-1').text(content.description);
            $('#publish-1').text("出版社:" + content.publish);
            $('#year-1').text("出版时间:" + content.year);
            $('#desc-2').text(content.description);
            $('#publish-2').text("出版社:" + content.publish);
            $('#year-2').text("出版时间:" + content.year);
        }
    });
}