var host = "http://192.168.50.232:8005";

var evaluate_host = "http://192.168.50.232:8008";

var friend_host = "http://192.168.50.232:8002";

//查看更多的企业类别
var more_type = 1;
//查找条件
var search_input = "";
//查看企业
var view_company_id = 0;

$(document).ready(function() {

    var user_id = $.cookie('id');
    var name = $.cookie('name');
    var head_img = $.cookie('head_img');
    var token = $.cookie('token');

    if(token != null && token != ''){
        $('#login_btn').html('退出');
    }

    //初始化显示排行榜
    $('#top_show').show();
    $('#search_show').hide();

    //登录、登出
    $('#login_btn').click(function () {
        if(token != null){
            //删除cookie
            $.cookie('token','',{expires: -1, path: '/'});
            $.cookie('id','',{expires: -1, path: '/'});
            $.cookie('name','',{expires: -1, path: '/'});
            $.cookie('head_img','',{expires: -1, path: '/'});
        }
        window.location.href = "login.html";
    });

    $('#friend_name').html(name);

    //加载965企业
    var company965 = loadCompany(1);
    for(var i = 0;i<company965.length;i++){
        $('#965companys').append('<h4><a href="javascript:viewCompany(' + company965[i].id + ')">'+company965[i].name+'</a></h4>');
        if(company965[i].tags == ''){
            $('#965companys').append('<p>暂无标签</p>');
        }else{
            $('#965companys').append('<p>'+company965[i].tags+'</p>');
        }
    }

    //加载996企业
    var company996 = loadCompany(2);
    for(var i = 0;i<company996.length;i++){
        $('#996companys').append('<h4><a href="javascript:viewCompany(' + company965[i].id + ')">'+company996[i].name+'</a></h4>');
        if(company996[i].tags == ''){
            $('#996companys').append('<p>暂无标签</p>');
        }else{
            $('#996companys').append('<p>'+company996[i].tags+'</p>');
        }

    }


    //添加企业
    $('#add_company_btn').click(function () {
        if(token == null || token == ""){
            alert('请先登录!');
            return;
        }
        $('#company_user').val(user_id);

       $.ajax({
           type: 'POST',
           dataType: 'json',
           url: host+ '/company/add',
           data: $('#company_form').serializeArray(),
           success:function (result) {
               if(result.code == 200){
                   alert('提交成功!')
                   $('#add_company').modal('hide');
               }else {
                   alert(result.msg);
               }
           }
       });
    });


    //查看更多
    $('#more_965_btn').click(function () {
        more_type = 1;
        loadCompanyPage({'type':more_type,'pageNo':1,'pageSize':10});

    });

    $('#more_996_btn').click(function () {
        more_type = 2;
        loadCompanyPage({'type':more_type,'pageNo':1,'pageSize':10});

    });


    //查找公司
    $('#search_btn').click(function () {
        search_input = $('#search_input').val();
        loadCompanyPage({'name':search_input,'type':more_type,'pageNo':1,'pageSize':10});
    });


    //添加评论
    $('#add_evaluate_btn').click(function () {
        if(token == null || token == ""){
            alert('请先登录!');
            return;
        }
        $('#evaluate_user').val(user_id);
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: evaluate_host+ '/evaluate/add',
            data: $('#evaluate_form').serializeArray(),
            success:function (result) {
                if(result.code == 200){
                    alert('评论成功!')
                    loadEvaluate($('#evaluate_company').val());
                    $('#evaluate_content').val('');
                }else {
                    alert(result.msg);
                }
            }
        });

    });

})

//首页加载企业top3列表,返回企业列表
function loadCompany(type){
    var companys;

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: host+ '/company/find',
        data: {'type':type},
        async: false,
        success:function (result) {
            if(result.code == 200){
                companys = result.object.list;
            }
        }

    });
    return companys;
}

function loadCompanyPage(data){

    $('#top_show').hide();
    $('#search_show').show();
    var company_pages;

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: host+ '/company/find',
        data: data,
        async: false,
        success:function (result) {
            if(result.code == 200){
                company_pages = result.object;
            }
        }

    });
    showList(company_pages);
}

function showList(company_pages){
    //先清空原有展示内容
    $('#company_list').html('');
    $('#company_page_ul').html('');

    var companys = company_pages.list;
    for(var i = 0;i<companys.length;i++){
        $('#company_list').append('<div class="col-lg-6">' +
            '<h4><a href="javascript:viewCompany(' + companys[i].id + ')">' + companys[i].name + '</a></h4>' +
            '<p>' + companys[i].tags + '</p>' +
            '</div>');
    }
    //处理分页信息
    doPage('company_page_ul',company_pages,'changeCompanyPage');


    //添加返回top页按钮
    $('#company_page_ul').append('<li><a href="javascript:backTop()">返回</a></li>');
}

//企业列表跳转页面
function changeCompanyPage(pageNo){
    loadCompanyPage({'name':search_input,'type':more_type,'pageNo':pageNo,'pageSize':10});
}

function backTop() {
    $('#top_show').show();
    $('#search_show').hide();
}

//企业详情
function viewCompany(id){

    view_company_id = id;

    $('#view_company').modal('show');

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: host + '/company/' + id,
        success:function (result) {
            if(result.code == 200){
                //展示企业内容
                var company = result.object;
                $('#company_view_name').html(company.name);
                if(company.tags == ''){
                    $('#company_view_tags').html('暂无标签');
                }else{
                    $('#company_view_tags').html(company.tags);
                }

                $('#company_view_type').html(company.type);
                if(company.href == ''){
                    $('#company_view_href').html('暂无网址');
                }else{
                    $('#company_view_href').html(company.href);
                }

                //展示评价
                loadEvaluate(company.id,{'pageNo':1});

                //初始化添加评论form
                $('#evaluate_company').val(company.id);
            }
        }
    })
}

function loadEvaluate(id,pageData){
    //先清空
    $('#evaluate_list').html('');

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: evaluate_host + '/evaluate/findByCompany/' + id,
        data: pageData,
        success: function (result) {
            if(result.code == 200){
                var pages = result.object;
                var evaluates = result.object.list;
                if(evaluates.length == 0){
                    $('#evaluate_list').html('暂无评价');
                }

                for(var i = 0;i<evaluates.length;i++){
                    $('#evaluate_list').append('<div>' +
                        '<h5>#'+ ((i+1)+5*(pages.pageNum-1)) +'    评论用户:' + formmatUser(evaluates[i].createUser) + '</h5>' +
                        '<p class="evaluate_content">' + evaluates[i].content + '</p>' +
                        '<p class="evaluate_time">' + evaluates[i].dateStr+ '</p>' +
                        '</div>');
                }
                //处理分页
                doPage('evaluate_page_ul',pages,'changeEvaluatePage');

            }
        }
    });
}

function changeEvaluatePage(page){
    loadEvaluate(view_company_id,{'pageNo':page});
}

function formmatUser(id){
    var name = '';

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: friend_host + '/friend/' + id,
        async: false,
        success: function (result) {
            if(result.code == 200){
                name = result.object.name;
            }
        }
    });

    return name;
}