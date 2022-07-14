function edit(){
        const gender = document.getElementById("gender");
        const age = document.getElementById("age");
        const address = document.getElementById("address");
        const phone = document.getElementById("phone");
        $.ajax({
                type:"post",
                url:"../edit",
                data:{gender:gender.value,age:age.value,address:address.value,phone:phone.value},
                dataType:"json",
                async:false,
                success:function (msg){
                        window.location.reload();
                }
        });
}