package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Cvideo;
import com.jxnu.stras.mapper.CvideoMapper;
import com.jxnu.stras.service.CvideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("CvideoService")
public class CvideoServiceImpl extends ServiceImpl<CvideoMapper, Cvideo> implements CvideoService {
    @Resource
    CvideoMapper cvideoMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Page<Cvideo> findByCid(Integer cid ,Integer pn) {
        String key = "Cvideo"+cid+pn;
        Page<Cvideo> cvideoList;
        if(!redisTemplate.hasKey(key)){
            Page<Cvideo> cvideoPage = new Page<>(pn,1);
            QueryWrapper<Cvideo> cvideoQueryWrapper = new QueryWrapper<>();
            cvideoQueryWrapper.eq("cid",cid);
            cvideoList = page(cvideoPage,cvideoQueryWrapper);
            redisTemplate.opsForValue().set(key,cvideoList);
            redisTemplate.expire(key,30, TimeUnit.MINUTES);
            return (Page<Cvideo>) redisTemplate.opsForValue().get(key);
        }else{
            return (Page<Cvideo>) redisTemplate.opsForValue().get(key);
        }
    }
}
