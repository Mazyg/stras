package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Dynamic;
import com.jxnu.stras.mapper.DynamicMapper;
import com.jxnu.stras.service.DynamicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("DynamicService")
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper,Dynamic> implements DynamicService {

    @Resource
    DynamicMapper dynamicMapper;

    @Override
    public boolean saveDynamic(Dynamic dynamic) {
        return dynamicMapper.saveDynamic(dynamic);
    }

    @Override
    public boolean deleteDynamic(Integer wid) {
        return dynamicMapper.deleteDynamic(wid);
    }

    @Override
    public Integer findTidByWid(Integer wid) {
        return dynamicMapper.findTidByWid(wid);
    }

    @Override
    public List<Dynamic> findByTid(Integer tid) {
        return dynamicMapper.findByTid(tid);
    }



}
