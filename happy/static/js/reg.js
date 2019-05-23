var login_host = "http://127.0.0.1:8010/";

var main_url = "../main/main.html";

$(document).ready(function(){


    $('#reg_btn').click(function () {

        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: login_host + "reg",
            async: false,
            data: $('#reg_form').serializeArray(),
            success: function(result){
                //登录通过
                if(result.code == 200){
                    var id = result.object.friend.id;
                    var name = result.object.friend.name;
                    var head_img = result.object.friend.headImg;
                    var token = result.object.token;

                    //设置cookie
                    $.cookie('token',token,{expires: 1, path: '/'});
                    $.cookie('id',id,{expires: 1, path: '/'});
                    $.cookie('name',name,{expires: 1, path: '/'});
                    $.cookie('head_img',head_img,{expires: 1, path: '/'});
                    window.location.href = main_url;

                }else{
                    $('.err').html(result.msg);
                }
            }
        })
    });

})
