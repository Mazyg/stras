package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Cart;
import com.jxnu.stras.domin.Product;
import com.jxnu.stras.enums.ResultEnum;
import com.jxnu.stras.exception.MallException;
import com.jxnu.stras.mapper.CartMapper;
import com.jxnu.stras.mapper.ProductMapper;
import com.jxnu.stras.service.CartService;
import com.jxnu.stras.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service("CartService")
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean save(Cart entity) {
        //扣库存
        Product product = productMapper.selectById(entity.getProductId());
        Integer stock = product.getStock() - entity.getQuantity();
        if(stock < 0){
            log.error("【添加购物车】库存不足！stock={}",stock);
            throw new MallException(ResultEnum.STOCK_ERROR);
        }
        product.setStock(stock);
        productMapper.updateById(product);
        if(cartMapper.insert(entity) == 1){
            return true;
        }
        return false;
    }

    @Override
    //根据用户的手机号查到用户购物车里的所有商品信息
    public List<CartVO> findAllCartVOByUserId(String phone) {
        List<CartVO> cartVOList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_phone",phone);
        //根据用户手机号查出用户购物车商品
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();
            Product product = productMapper.selectById(cart.getProductId());
            //caetVo属性是根据product和cart实体类映射过来的
            BeanUtils.copyProperties(product,cartVO);
            BeanUtils.copyProperties(cart,cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }
    
}
