let express = require("express");
let path = require("path");
let cors = require('cors');
let request = require('request');
let app = express();
let bodyParser = require('body-parser');//用于req.body获取值的

// set static
app.use(cors());
app.use(express.static(path.join(__dirname, "static")));
app.use(bodyParser.json());
// 创建 application/x-www-form-urlencoded 编码解析
app.use(bodyParser.urlencoded({extended: true}));

// set router
let indexRouter = require("./router/index");
let loginRouter = require("./router/login");
let signUpRouter = require("./router/signUp");
let descRouter = require("./router/description");
let shopRouter = require("./router/shop-list");
const querystring = require("querystring");


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
    request({url: 'http://39.106.137.16:3180/api/hotBooks'}, function (error, response, body) {
        //把请求成功后的数据发送给客户端
        res.send(body);
    })
});

app.get('/shopBooks', (req, res) => {
    const shopId = req.query.shopId;
    request({url: 'http://39.106.137.16:3180/api/shop/bookList', qs: {shopId: shopId}},
        function (error, response, body) {
            //console.log(response)
            //把请求成功后的数据发送给客户端
            console.log(body);
            res.send(body);
        })
});

// app.post('/login', (req, res) => {
//     console.log(req.body)
//     request({
//             method: "post",
//             heads: {'content-type': 'application/json'},
//             url: 'http://39.106.137.16:3180/api/user/login',
//             json: {userName: req.query.userName, password: req.query.password}
//         },
//         function (error, response, body) {
//             res.send(body);
//         })
// });

app.post('/UserLogin', function (req, res) {
    let username = req.body.userName;
    let pswod = req.body.password;
    console.log("2"+username)
    console.log("2"+pswod)
    // let send_obj =
    // send_obj = JSON.stringify(send_obj)
    request({
        type: 'post',
        json: true,
        headers: {
            "content-type": "application/json",
        },
        url: 'http://39.106.137.16:3180/api/user/login',
        body: {userName: username, password: pswod }
            // + "?userName=" + username + "&password=" + pswod,
    }, function (error, response, body) {
        res.send(body);
    });
});

app.get('/shopCart', (req, res) => {
    let shopId = Number(req.query.userId);
    request({url: 'http://39.106.137.16:3180/api/shop/bookList', qs: {userId: shopId}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            console.log(body);
            res.send(body);
        })
});

app.listen(3000, () => {
    console.log('Server has listen to 3000');
})