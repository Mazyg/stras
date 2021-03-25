package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Add;

import java.util.List;

public interface AddService extends IService<Add> {

    public List<Add> findAllAdd();
}
