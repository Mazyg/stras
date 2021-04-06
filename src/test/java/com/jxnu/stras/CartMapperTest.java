package com.jxnu.stras;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxnu.stras.domin.Cart;
import com.jxnu.stras.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartMapperTest {
    @Autowired
    CartMapper cartMapper;
    @Test
    public void testCart(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_phone","13207088373");
        //根据用户手机号查出用户购物车商品
        List<Cart> cartList = cartMapper.selectList(wrapper);
        System.out.println(cartList);

    }

}
