package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Cvideo;

import java.util.List;

public interface CvideoService extends IService<Cvideo> {
    public Page<Cvideo> findByCid(Integer cid,Integer pn);
}
