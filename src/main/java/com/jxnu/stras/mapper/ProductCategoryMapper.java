package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.ProductCategory;
import com.jxnu.stras.vo.ProductCategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
}
