package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolMapper extends BaseMapper<School> {

    @Select("select * from school \n" +
            "limit 0,1")
    public School findSchool();
}
