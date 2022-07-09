function login() {
    const username1 = document.getElementById("floatingInput");
    const pass1 = document.getElementById("floatingPassword");
    console.log("2");
    htmlobj = $.ajax({
        type: "post",
        url: "http://localhost:3000/login.html/login",
        data: {username: username1.value, password: pass1.value},
        async: false,
        success: function (msg) {
            if (msg == "1") {
                alert("请输入用户名");
            } else if (msg == "2") {
                alert("请输入密码");
            } else if (msg == "3") {
                alert("登录成功");
                const t1 = document.getElementById("loginText")
                const t2 = document.getElementById("signText")
                t1.innerText = username1.value.toString();
                t2.style.display = "none";
                window.location.href = "index.html";

            } else {
                alert("请输入正确的用户名和密码！");
            }

        }
    });
}


function sign() {
    const username = document.getElementById("floatingInput");
    const pass = document.getElementById("floatingPassword");
    if (username.value == "") {
        alert("请输入用户名");
    } else if (pass.value == "") {
        alert("请输入密码");
    } else {
        window.alert("注册成功")
        window.location.href = "index.html";
    }
}

