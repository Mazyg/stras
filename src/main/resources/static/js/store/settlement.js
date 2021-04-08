$(function(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseInt($(".qprice").eq(i).html());
        // alert(val);
        totalCost += val;
    }
    $("#totalprice").html(totalCost+"积分");
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
function addQuantity(obj){
    // alert("进入addQuantity.......");
    //所有加号保存在数组里【0,1,2,3，.....】,取出当前加号
    var index = $(".car_btn_2").index(obj);
    //根据index找到所点击加号对应的数量，价格
    var stock = parseInt($(".productStock").eq(index).val());
    var price = parseInt($(".productPrice").eq(index).val());
    var inputObj = $(".car_ipt").eq(index);
    var quantity = inputObj.val();//数量
    var id = $(".id").eq(index).val();
    ++quantity;
    if(quantity > stock){
        quantity = stock;
        alert("库存不足！");
        return false;
    }
    var cost = quantity*price;
    //更新数据库
    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        dataType:"text",
        success:function (data) {
            if(data == "success"){
                //更新Settlement1.html的数据
                $(".qprice").eq(index).html(cost);//更新后的积分写回
                inputObj.val(quantity);//更新后的数量写回
                // if(quantity < stock){
                    var totalCost = parseInt($("#totalprice").html());
                    totalCost += price;
                    $("#totalprice").html(totalCost+'积分');//更新后的总积分
                // }
                //更新searchBar的数据
                // $(".quantity").eq(index).text(quantity);
                // $(".cost").eq(index).text(cost);
                //
                // var array = $(".cost");
                // var totalCost = 0;
                // for(var i = 0;i < array.length;i++){
                //     var val = parseInt($(".cost").eq(i).html());
                //     totalCost += val;
                // }
                // $("#totalCost").html(totalCost+'积分');
            }
        }
    });
}

//商品数量--
function subQuantity(obj){
    var index = $(".car_btn_1").index(obj);
    var price = parseInt($(".productPrice").eq(index).val());
    var inputObj = $(".car_ipt").eq(index);
    var quantity = inputObj.val();
    var id = $(".id").eq(index).val();
    --quantity;
    if(quantity == 0){
        quantity = 1;
        return false;
    }
    var cost = quantity*price;
    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        dataType:"text",
        success:function(data){
            if(data == "success"){
                $(".qprice").eq(index).html(cost);
                inputObj.val(quantity);
                if(quantity!=1){
                    var totalCost = parseInt($("#totalprice").html());
                    totalCost -= price;
                    $("#totalprice").html(totalCost+'积分');
                }
                // $(".quantity").eq(index).text(quantity);
                // $(".cost").eq(index).text(cost);
                //
                // var array = $(".cost");
                // var totalCost = 0;
                // for(var i = 0;i < array.length;i++){
                //     var val = parseInt($(".cost").eq(i).html());
                //     totalCost += val;
                // }
                // $("#totalCost").html("￥"+totalCost);
            }
        }
    });
}

//移出购物车
function removeCart(obj){
    var index = $(".delete").index(obj);
    var id = $(".id").eq(index).val();
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/deleteById/"+id;
    }
}