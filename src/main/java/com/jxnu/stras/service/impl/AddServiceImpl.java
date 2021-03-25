package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Add;
import com.jxnu.stras.mapper.AddMapper;
import com.jxnu.stras.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("AddService")
public class AddServiceImpl extends ServiceImpl<AddMapper, Add> implements AddService {
    @Resource
    AddMapper addMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Add> findAllAdd() {
        String key = "add";
        List<Add> addList;
        if(!redisTemplate.hasKey(key)){
            addList = addMapper.findAllAdd();
            redisTemplate.opsForValue().set(key,addList);
            redisTemplate.expire(key,20, TimeUnit.MINUTES);
            return (List<Add>) redisTemplate.opsForValue().get(key);
        }else{
            return (List<Add>) redisTemplate.opsForValue().get(key);
        }
    }
}
