//实现分页效果
function jumpTo(maxPage,numberPerPage,total,name){
    var page = $("#jumpTo").val();
    var start=(page-1)*numberPerPage;
    var rest=total-start;
    if (page==''){
        alert("请输入要跳转的页数")
    }
    else if(page > maxPage || page < 1){
        alert("对不起，无法到达该页")
    }else{
        if(rest>=numberPerPage){
            window.location.href=name+".do?page="+page+"&numberPerPage="+numberPerPage+"&start="+start+"&length="+numberPerPage;
        }
        else {
            window.location.href=name+".do?page="+page+"&numberPerPage="+numberPerPage+"&start="+start+"&length="+rest;
        }
    }
}

function change(total,name){
    var numberPerPage=$("#numberPerPage").val();
    if(numberPerPage==''){
        alert("请设置每页需要显示的条数")
    }
    else if(numberPerPage > total || numberPerPage < 1){
        alert("对不起，无法设置");
    }else{
        window.location.href=name+".do?start=0&page=1"+"&length="+numberPerPage+"&numberPerPage="+numberPerPage;
    }
}