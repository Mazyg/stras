package com.jxnu.stras.service.impl;

import com.jxnu.stras.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryService service;

    @Test
    void getAllProductCategoryVO() {
        service.getAllProductCategoryVO();
    }
}