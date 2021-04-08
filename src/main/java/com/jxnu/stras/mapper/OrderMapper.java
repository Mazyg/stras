package com.jxnu.stras.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
