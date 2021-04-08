package com.jxnu.stras;

import com.jxnu.stras.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;
    @Test
    void test(){
        productMapper.selectList(null).forEach(System.out::println);
    }

}
