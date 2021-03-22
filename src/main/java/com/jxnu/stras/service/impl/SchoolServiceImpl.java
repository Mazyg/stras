package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.School;
import com.jxnu.stras.mapper.SchoolMapper;
import com.jxnu.stras.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("SchoolService")
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Resource
    SchoolMapper schoolMapper;

    @Override
    public School findSchool() {
        return schoolMapper.findSchool();
    }
}
