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
let bookListRouter = require("./router/bookList");
let welcomeRouter = require("./router/welcome");
let userRouter = require("./router/user");
let shopCartRouter = require("./router/shopCart");


app.use("/index.html", indexRouter);
app.use("/login.html", loginRouter);
app.use("/signUp.html", signUpRouter);
app.use("/description.html", descRouter);
app.use("/bookList.html", bookListRouter);
app.use("/welcome.html",welcomeRouter);
app.use("/user.html",userRouter);
app.use("/shopCart.html",shopCartRouter);

app.all('*', function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    // 2.允许客户端使用哪些请求方法访问我
    res.header('Access-Control-Allow-Methods', 'PUT,GET,POST,DELETE,OPTIONS');
    res.header('Access-Control-Allow-Headers', 'X-Requested-With');
    res.header('Access-Control-Allow-Headers', ['mytoken', 'Content-Type']);
    next();
})

app.get('/hotBooks', (req, res) => {
    request({ url: 'http://39.106.137.16:3180/api/hotBooks' },
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            // console.log(body);
            res.send(body);
        })
});

app.get('/highScoresBooks', (req, res) => {
    request({ url: 'http://39.106.137.16:3180/api/highScoreBooks' },
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            // console.log(body);
            res.send(body);
        })
});

app.get('/bookSearch', (req, res) => {
    let info = req.query.info;
    request({ url: 'http://39.106.137.16:3180/api/book/search/'+ info ,qs:{info: info}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            console.log(body);
            res.send(body);
        })
});

app.get('/bookDescription', (req, res) => {
    let bookId = Number(req.query.bookId);
    request({ url: 'http://39.106.137.16:3180/api/book/details', qs:{bookId:bookId}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            // console.log(body);
            res.send(body);
        })
});

app.post('/comment', (req, res) => {
    let bookId = Number(req.query.bookId);
    let userId = Number(req.query.userId);
    let comment = req.query.comment;
    let scores = req.query.scores;
    request({ url: 'http://39.106.137.16:3180/api/user/insertComment',
            json:{bookId:bookId,userId:userId,comment:comment,scores:scores}},
        function (error, response, body) {
            //console.log(body);
            res.send(body);
        })
});

app.get('/shopBooks', (req, res) => {
    let shopId = Number(req.query.shopId);
    request({ url: 'http://39.106.137.16:3180/api/shop/bookList', qs:{shopId:shopId}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            // console.log(body);
            res.send(body);
        })
});

app.get('/getShopCart', (req, res) => {
    let userId = Number(req.query.userId);
    request({ url: 'http://39.106.137.16:3180/api/user/shoppingCar', qs:{userId:userId}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            // console.log(body);
            res.send(body);
        })
});

app.get('/editShopCart', (req, res) => {
    let userId = Number(req.query.userId);
    let bookId = Number(req.query.bookId);
    let num = Number(req.query.num);
    let operation = Number(req.query.operation);
    request({ url: 'http://39.106.137.16:3180/api/user/updateShoppingCar',
            qs:{userId: userId,bookId: bookId,num: num,operation: operation}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            console.log(body);
            res.send(body);
        })
});

app.get('/booksCategory', (req, res) => {
    let categoryId = Number(req.query.categoryId);
    request({ url: 'http://39.106.137.16:3180/api/category/'+categoryId, qs:{categoryId:categoryId}},
        function (error, response, body) {
            // console.log(response.toString())
            //把请求成功后的数据发送给客户端
            // console.log(body);
            res.send(body);
        })
});

app.post('/login', (req, res) => {
    //console.log(req,res)
    request({ url: 'http://39.106.137.16:3180/api/user/login',json:{userName:req.body.username,password:req.body.password}},
        function (error, response, body) {
            console.log(body);
            res.send(body);
        })
});

app.post('/sign',(req,res)=>{
    //console.log(req,res)
    request({url: 'http://39.106.137.16:3180/api/user/register',json:{userName: req.body.username,password: req.body.password}},
        function (error,response,body){
            res.send(body);
        })
});

app.post('/details', (req, res) => {
    //console.log(req,res)
    request({ url: 'http://39.106.137.16:3180/api/user/details',json:{userId:req.body.user_id}},
        function (error, response, body) {
            console.log(body);
            res.send(body);
        })
});

app.post('/edit', (req, res) => {
    //console.log(req,res)
    request({ url: 'http://39.106.137.16:3180/api/user/update/details',json:{gender:req.body.gender,age:req.body.age,address:req.body.address,phone:req.body.phone}},
        function (error, response, body) {
            console.log(body);
            res.send(body);
        })
});

app.listen(3000, () => {
    console.log('Server has listen to 3000');
})

app.post('/changed', (req, res) => {
    //console.log(req,res)
    request({ url: 'http://39.106.137.16:3180/api/user/update/password',json:{old_pwd:req.body.old_pwd,new_pwd: req.body.new_pwd}},
        function (error, response, body) {
            console.log(body);
            res.send(body);
        })
});