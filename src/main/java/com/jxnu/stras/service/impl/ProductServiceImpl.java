package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Product;
import com.jxnu.stras.mapper.ProductMapper;
import com.jxnu.stras.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service("ProductService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;
//    @Autowired
//    private ProductCategoryMapper productCategoryMapper;
//
    @Override
    public List<Product> findByCategoryId(String type,Integer categoryId) {
        Map<String,Object> map = new HashMap<>();
        map.put("categorylevel"+type+"_id",categoryId);
        return productMapper.selectByMap(map);
    }
//
//    @Override
//    public TableDataVO<TableProductVO> findAllTableData(Integer page,Integer limit) {
//        IPage<Product> productIPage = new Page<>(page,limit);
//        IPage<Product> result = productMapper.selectPage(productIPage,null);
//        List<Product> productList = result.getRecords();
//        List<TableProductVO> tableProductVOList = new ArrayList<>();
//        QueryWrapper wrapper;
//        ProductCategory productCategory;
//        for (Product product : productList) {
//            TableProductVO tableProductVO = new TableProductVO();
//            BeanUtils.copyProperties(product,tableProductVO);
//            wrapper = new QueryWrapper();
//            wrapper.eq("id",product.getCategoryleveloneId());
//            productCategory = productCategoryMapper.selectOne(wrapper);
//            if(productCategory != null){
//                tableProductVO.setLevelOne(productCategory.getName());
//            }
//
//
//            wrapper = new QueryWrapper();
//            wrapper.eq("id",product.getCategoryleveltwoId());
//            productCategory = productCategoryMapper.selectOne(wrapper);
//            if(productCategory != null){
//                tableProductVO.setLevelTwo(productCategory.getName());
//            }
//
//
//            wrapper = new QueryWrapper();
//            wrapper.eq("id",product.getCategorylevelthreeId());
//            productCategory = productCategoryMapper.selectOne(wrapper);
//            if(productCategory != null){
//                tableProductVO.setLevelThree(productCategory.getName());
//            }
//            tableProductVOList.add(tableProductVO);
//        }
//        TableDataVO tableDataVO = new TableDataVO();
//        tableDataVO.setCode(0);
//        tableDataVO.setMsg("");
//        tableDataVO.setData(tableProductVOList);
//        tableDataVO.setCount(result.getTotal());
//        return tableDataVO;
//    }
}
