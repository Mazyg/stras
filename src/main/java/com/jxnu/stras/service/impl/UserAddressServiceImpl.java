package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.UserAddress;
import com.jxnu.stras.mapper.UserAddressMapper;
import com.jxnu.stras.service.UserAddressService;
import org.springframework.stereotype.Service;

@Service("UserAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
