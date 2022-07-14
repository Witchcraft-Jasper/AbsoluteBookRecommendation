let express = require("express");
let fs = require("fs");
let router = express.Router();

router.get('/', function(req, res, next) {
    let htmlCont = fs.readFileSync("./static/html/user.html");
    res.write(htmlCont);
    res.end();
});

module.exports = router;