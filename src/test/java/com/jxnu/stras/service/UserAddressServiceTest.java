package com.jxnu.stras.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UserAddressServiceTest {
    @Autowired
    private UserAddressService userAddressService;
    @Test
    void test(){
        Map<String,Object> map=new HashMap<>();
        map.put("user_phone","13207088373");
        userAddressService.listByMap(map).forEach(System.out::println);
    }

}