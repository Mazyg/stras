package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.OrderDetail;
import com.jxnu.stras.mapper.OrderDetailMapper;
import com.jxnu.stras.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service("OrderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
