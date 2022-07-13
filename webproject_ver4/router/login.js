let express = require("express");
let fs = require("fs");
let querystring = require("querystring")
let router = express.Router();
let request = require('request');

let username = ["wit", "user"];
let password = ["123", "456"];

router.get('/', function (req, res, next) {
    let htmlCont = fs.readFileSync("./static/html/login.html");
    res.write(htmlCont);
    res.end();
});


module.exports = router;