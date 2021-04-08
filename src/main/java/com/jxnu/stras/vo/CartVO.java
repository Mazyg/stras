package com.jxnu.stras.vo;

import lombok.Data;

@Data
//用户查看自己购物车的所有商品页面的信息封装
public class CartVO {
    private Integer id;
    private Integer quantity;
    private Float cost;
    private Integer productId;
    private String name;
    private Float price;
    private String fileName;
    private Integer stock;
}
