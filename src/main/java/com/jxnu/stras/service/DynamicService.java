package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Dynamic;

import java.util.List;

public interface DynamicService extends IService<Dynamic> {

    public boolean saveDynamic(Dynamic dynamic);
    public boolean deleteDynamic(Integer wid);
    Integer findTidByWid(Integer wid);
    public List<Dynamic> findByTid(Integer tid);

}
