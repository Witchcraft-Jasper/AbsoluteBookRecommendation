let express = require("express");
let path = require("path");
let cors = require('cors');
let request = require('request');
let app = express();
let bodyParser = require('body-parser');//用于req.body获取值的
app.use(bodyParser.json());
// 创建 application/x-www-form-urlencoded 编码解析
app.use(bodyParser.urlencoded({ extended: false }));

// set static
app.use(cors());
app.use(express.static(path.join(__dirname, "static")));

// set router
let indexRouter = require("./router/index");
let loginRouter = require("./router/login");
let signUpRouter = require("./router/signUp");
let descRouter = require("./router/description");
let shopRouter = require("./router/shop-list");


app.use("/index.html", indexRouter);
app.use("/login.html", loginRouter);
app.use("/signUp.html", signUpRouter);
app.use("/description.html", descRouter);
app.use("/shopList.html", shopRouter);

app.all('*', function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    // 2.允许客户端使用哪些请求方法访问我
    res.header('Access-Control-Allow-Methods', 'PUT,GET,POST,DELETE,OPTIONS');
    res.header('Access-Control-Allow-Headers', 'X-Requested-With');
    res.header('Access-Control-Allow-Headers', ['mytoken', 'Content-Type']);
    next();
})

app.get('/hotBooks', (req, res) => {
    request({ url: 'http://39.106.137.16:3180/api/hotBooks'}, function (error, response, body) {
        //把请求成功后的数据发送给客户端
        res.send(body);
    })
});

app.get('/shopBooks', (req, res) => {
    var shopId = req.query.shopId;
    request({ url: 'http://39.106.137.16:3180/api/shop/bookList',data:{shopId:shopId}},
        function (error, response, body) {
            //console.log(response)
            //把请求成功后的数据发送给客户端
            console.log(body);
            res.send(body);
        })
});

app.listen(3000, () => {
    console.log('Server has listen to 3000');
})