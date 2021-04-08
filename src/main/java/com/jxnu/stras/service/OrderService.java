package com.jxnu.stras.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Orders;
import com.jxnu.stras.domin.User;

import java.util.List;

public interface OrderService extends IService<Orders> {
    public boolean save(Orders orders, User user, String address, String remark);
//    public List<OrderVO> findAllOrederVOByUserId(Integer id);
}
