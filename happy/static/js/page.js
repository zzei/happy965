/**
 * 处理显示分页标签
 * @param id    div id
 * @param pages 包含有参数
 * @param changeFunctionName 切换页面的方法名
 * 默认显示5个页码
 * 当前页pageNum
 * 总页数pages
 * 是否第一页isFirstPage
 * 是否最后页isLastPage
 */
function doPage(id,pages,changeFunctionName){

    //先清空显示
    $('#'+id).html('');

    //不是首页 显示上一页按钮
    if(!pages.isFirstPage){
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '(1)" aria-label="Previous">'+
            '<span aria-hidden="true">首页</span>'+
            '</a>'+
            '</li>');
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '('+(pages.pageNum-1)+')" aria-label="Previous">'+
            '<span aria-hidden="true">&laquo;</span>'+
            '</a>'+
            '</li>');
    }

    //当只有数据只有一页时
    if(pages.pages == 1){
        $('#'+id).append('<li><a class="page_num" href="javascript:' + changeFunctionName + '(1)">1</a></li>');
    }else{
        //5页一组 第几组
        var page_group = parseInt(pages.pageNum/pages.pages) + 1;
        //显示页码 最多5
        for(var i = 1 + 5*(page_group - 1);i<=pages.pages && i<(page_group*5+1);i++){
            //当前页变色显示
            if(i == pages.pageNum){
                $('#'+id).append('<li><a class="page_num" href="javascript:' + changeFunctionName + '(' + i + ')">' + i + '</a></li>');
            }else{
                $('#'+id).append('<li><a href="javascript:' + changeFunctionName + '(' + i + ')">' + i + '</a></li>');
            }

        }
    }

    //不是最后页 显示下一页按钮
    if(!pages.isLastPage){
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '('+(pages.pageNum+1)+')" aria-label="Next">'+
            '<span aria-hidden="true">&raquo;</span>'+
            '</a>'+
            '</li>');
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '('+pages.pages+')" aria-label="Next">'+
            '<span aria-hidden="true">尾页</span>'+
            '</a>'+
            '</li>');
    }
}

/**
 * 处理显示分页标签
 * @param id    div id
 * @param pages 包含有参数
 * @param changeFunctionName 切换页面的方法名
 * @param pageSize 自定义最多显示多少页码
 * 当前页pageNum
 * 总页数pages
 * 是否第一页isFirstPage
 * 是否最后页isLastPage
 */
function doPage(id,pages,changeFunctionName,pageSize){

    //先清空显示
    $('#'+id).html('');

    //不是首页 显示上一页按钮
    if(!pages.isFirstPage){
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '(1)" aria-label="Previous">'+
            '<span aria-hidden="true">首页</span>'+
            '</a>'+
            '</li>');
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '('+(pages.pageNum-1)+')" aria-label="Previous">'+
            '<span aria-hidden="true">&laquo;</span>'+
            '</a>'+
            '</li>');
    }

    //当只有数据只有一页时
    if(pages.pages == 1){
        $('#'+id).append('<li><a class="page_num" href="javascript:' + changeFunctionName + '(1)">1</a></li>');
    }else{
        //5页一组 第几组
        var page_group = parseInt(pages.pageNum/pages.pages) + 1;
        //显示页码 最多5
        for(var i = 1 + pageSize*(page_group - 1);i<=pages.pages && i<(page_group*pageSize+1);i++){
            //当前页变色显示
            if(i == pages.pageNum){
                $('#'+id).append('<li><a class="page_num" href="javascript:' + changeFunctionName + '(' + i + ')">' + i + '</a></li>');
            }else{
                $('#'+id).append('<li><a href="javascript:' + changeFunctionName + '(' + i + ')">' + i + '</a></li>');
            }

        }
    }

    //不是最后页 显示下一页按钮
    if(!pages.isLastPage){
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '('+(pages.pageNum+1)+')" aria-label="Next">'+
            '<span aria-hidden="true">&raquo;</span>'+
            '</a>'+
            '</li>');
        $('#'+id).append('' +
            '<li>'+
            '<a href="javascript:' + changeFunctionName + '('+pages.pages+')" aria-label="Next">'+
            '<span aria-hidden="true">尾页</span>'+
            '</a>'+
            '</li>');
    }
}