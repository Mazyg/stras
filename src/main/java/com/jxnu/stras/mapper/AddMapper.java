package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Add;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddMapper extends BaseMapper<Add> {

    @Select("SELECT * FROM `add`")
    public List<Add> findAllAdd();
}
