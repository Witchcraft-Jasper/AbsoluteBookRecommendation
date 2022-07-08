let express = require("express");
let path = require("path");
let cors = require('cors')
let app = express();

// set static
app.use(cors());
app.use(express.static(path.join(__dirname, "static")));

// set router
let indexRouter = require("./router/index");
let loginRouter = require("./router/login");
let signUpRouter = require("./router/signUp");
let descRouter = require("./router/description");


app.use("/index.html", indexRouter);
app.use("/login.html", loginRouter);
app.use("/signUp.html", signUpRouter);
app.use("/description.html", descRouter);

app.listen(3000, () => {
    console.log('Server has listen to 3000');
})