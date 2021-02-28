var search;
function searchSub(numberPerPage){
    search= $("#search").val();
    if (search=='') {
        alert("搜索内容不能为空");
    }
    else {
        window.location.href="../info/findAll.do?start=0&page=1"+"&length="+numberPerPage+"&numberPerPage="+numberPerPage+"&search="+search;
    }
}

//搜索结果实现分页效果
function jumpTo2(maxPage,numberPerPage,total,search){
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
            window.location.href="../info/findAll.do?page="+page+"&numberPerPage="+numberPerPage+"&start="+start+"&length="+numberPerPage+"&search="+search;
        }
        else {
            window.location.href="../info/findAll.do?page="+page+"&numberPerPage="+numberPerPage+"&start="+start+"&length="+rest+"&search="+search;
        }
    }
}

function change2(total,search){
    var numberPerPage=$("#numberPerPage").val();
    if(numberPerPage==''){
        alert("请设置每页需要显示的条数")
    }
    else if(numberPerPage > total || numberPerPage < 1){
        alert("对不起，无法设置");
    }else{
        window.location.href="../info/findAll.do?start=0&page=1"+"&length="+numberPerPage+"&numberPerPage="+numberPerPage+"&search="+search;
    }

}







