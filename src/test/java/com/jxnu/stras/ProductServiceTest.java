package com.jxnu.stras;

import com.jxnu.stras.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Test
    void test(){
            Map<String,Object> map=new HashMap<>();
            map.put("categorylevelthree_id",655);
            service.listByMap(map).forEach(System.out::println);
    }

    @Test
    void test2(){
        System.out.println(service.getById(733));
    }
}