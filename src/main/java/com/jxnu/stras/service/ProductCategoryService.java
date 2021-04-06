package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.ProductCategory;
import com.jxnu.stras.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> getAllProductCategoryVO();
}
