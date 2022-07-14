function login() {
    const username1 = document.getElementById("floatingInput");
    const pass1 = document.getElementById("floatingPassword");
    if (username1.value == "") {
        alert("请输入用户名");
    } else if (pass1.value == "") {
        alert("请输入密码");
    } else {
        htmlobj = $.ajax({
            type: "post",
            url: "../login",
            data: {username: username1.value.toString(), password: pass1.value.toString()},
            dataType: "json",
            async: false,
            success: function (msg) {
                if (msg.data == "3") {
                    alert("登录成功");
                    $.cookie("user", username1.value, {expires: 30});
                    $.cookie("pwd", pass1.value, {expires: 30});
                    $.cookie("user_id",msg.id,{expires:30});
                    window.location.href = "index.html";
                } else {
                    alert(msg.message);
                }
            }
        });
    }
}


function sign() {
    const username = document.getElementById("floatingInput");
    const pass = document.getElementById("floatingPassword");
    if (username.value === "") {
        alert("请输入用户名");
    } else if (pass.value === "") {
        alert("请输入密码");
    } else {
        htmlobj = $.ajax({
            type: "post",
            url: "../sign",
            data: {username: username.value.toString(), password: pass.value.toString()},
            dataType: "json",
            async: false,
            success: function (msg) {
                if (msg.data === "5") {
                    alert("注册成功");
                    window.location.href = "index.html";
                    //window.returnValue=false;
                } else {
                    alert(msg.message);
                }
            }
        });
    }
}

function check() {
    let user = $.cookie("user");
    let pwd = $.cookie("pwd");
    if (user != null && user !== "" && pwd != null && pwd !== "") {
        $.ajax({
            type: "post",
            url: "../login",
            data:{username:user.toString(),password:pwd.toString()},
            async: false,
            success:function (msg){
                if (msg.data === "3") {
                    window.location.href = "index.html";
                } else
                    window.location.href="login.html";
            }
        });
    } else {
        window.location.href="login.html";
    }
}

function exit(){

}