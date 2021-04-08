package com.jxnu.stras.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxnu.stras.domin.Cart;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.service.CartService;
import com.jxnu.stras.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ){
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price*quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserPhone(user.getPhone());
        try {
            if(cartService.save(cart)){
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e) {
            return "redirect:/store/main";
        }
        return null;
    }

    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("store/settlement1");
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getPhone()));
        return modelAndView;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        cartService.removeById(id);
        return "redirect:/cart/findAllCart";
    }
//订单信息加载
    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("store/settlement2");
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getPhone()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_phone",user.getPhone());
        modelAndView.addObject("addressList",userAddressService.list(wrapper));
        return modelAndView;
    }
//Ajax更新购物车数据库的数据
    @PostMapping("/update/{id}/{quantity}/{cost}")
    @ResponseBody
    public String updateCart(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") Float cost
    ){
        //根据id找到购物车对应的数据，并更新数量和总价
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if(cartService.updateById(cart)){
            return "success";
        }else{
            return "fail";
        }
    }
}

