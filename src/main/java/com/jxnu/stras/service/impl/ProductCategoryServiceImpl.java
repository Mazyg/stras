package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Product;
import com.jxnu.stras.domin.ProductCategory;
import com.jxnu.stras.mapper.ProductCategoryMapper;
import com.jxnu.stras.mapper.ProductMapper;
import com.jxnu.stras.service.ProductCategoryService;
import com.jxnu.stras.vo.ProductCategoryVO;
import com.jxnu.stras.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service("ProductCategoryService")
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {
        //一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type",1);
        //levelOne 所有type=1的商品
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        //把这些商品封装成ProductCategoryVO类型
        //基本的方法
//        List<ProductCategoryVO> levelOneVO=new ArrayList<>();
//        for (ProductCategory productCategory : levelOne) {
//            ProductCategoryVO productCategoryVO=new ProductCategoryVO();
//            BeanUtils.copyProperties(productCategory,productCategoryVO);//把productCategory的id和name复制给productCategoryVO
//            levelOneVO.add(productCategoryVO);
//        }
        //stream流的方法处理
        List<ProductCategoryVO> levelOneVO = levelOne.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
        //图片赋值
        //商品信息赋值
        for (int i = 0; i < levelOneVO.size(); i++) {
            //界面几个固定图片
            levelOneVO.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVO.get(i).setTopImg("/images/top"+i+".png");
            wrapper = new QueryWrapper();
            wrapper.eq("categorylevelone_id",levelOneVO.get(i).getId());//查到一级分类商品的id查商品
            List<Product> productList = productMapper.selectList(wrapper);
//            从数据库取的部分，将查出的商品用留得方式转化为ProductVO类型
            List<ProductVO> productVOList = productList.stream()
                    .map(e -> new ProductVO(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFileName()
                    )).collect(Collectors.toList());
            levelOneVO.get(i).setProductVOList(productVOList);
        }
        for (ProductCategoryVO levelOneProductCategoryVO : levelOneVO) {
            //二级分类
            wrapper = new QueryWrapper();
            wrapper.eq("type",2);
            wrapper.eq("parent_id",levelOneProductCategoryVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
//            把二级类别商品封装成ProductCategoryVO类型
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
            levelOneProductCategoryVO.setChildren(levelTwoVO);
            for (ProductCategoryVO levelTwoProductCategoryVO : levelTwoVO) {
                //三级分类
                wrapper = new QueryWrapper();
                wrapper.eq("type",3);
                wrapper.eq("parent_id",levelTwoProductCategoryVO.getId());
                List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> levelThreeVO = levelThree.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                levelTwoProductCategoryVO.setChildren(levelThreeVO);
            }
        }
        return levelOneVO;
    }

}
