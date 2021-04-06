package com.jxnu.stras.controller;


import com.jxnu.stras.domin.User;
import com.jxnu.stras.service.CartService;
import com.jxnu.stras.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Slf4j
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;



}

