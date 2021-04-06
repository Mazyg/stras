package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Product;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface ProductService extends IService<Product> {
    //商品列表展示，根据商品类别和所属类别的id查询改类别下的所有商品
    public List<Product> findByCategoryId(String type, Integer categoryId);

    /**
     * 后台管理系统返回商品数据
     */
//    public TableDataVO<TableProductVO> findAllTableData(Integer page, Integer limit);
}
