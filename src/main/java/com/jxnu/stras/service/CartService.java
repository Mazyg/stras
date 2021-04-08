package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Cart;
import com.jxnu.stras.vo.CartVO;

import java.util.List;

public interface CartService extends IService<Cart> {
    //根据用户的phone查看用户购物车内的所有商品
    public List<CartVO> findAllCartVOByUserId(String phone);
}
