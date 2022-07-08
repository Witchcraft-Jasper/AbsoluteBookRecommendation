let express = require("express");
let fs = require("fs");
let querystring = require("querystring")
let router = express.Router();

let username = ["wit", "user"];
let password = ["123", "456"];

router.get('/', function (req, res, next) {
    let htmlCont = fs.readFileSync("./static/html/login.html");
    res.write(htmlCont);
    res.end();
});

router.post('/login', function (req, res) {
    let body = ""
    req.on('data', function (str) {
        body += str;
    });
    req.on('end', function () {
        body = querystring.parse(body);
        console.log(body);
        if (body.username == '') {
            res.write("1");
            res.end();
        } else if (body.password == '') {
            res.write("2");
            res.end();
        } else {
            let symbol = 0;
            Array.from(username).forEach(function (value, index) {
                if (value == body.username && password[index] == body.password) {
                    res.write("3");
                    res.end();
                    symbol = 1;
                }
            });
            if (symbol == 0) {
                res.write("4");
                res.end();
            }
        }
    });

    // Array.from(username).forEach(function (value, index){
    //
    // })
});
module.exports = router;