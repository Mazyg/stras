package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Info;

public interface InfoService extends IService<Info> {

    public Info getInfoById(Integer infoId);

    public boolean updateInfo(Integer infoId);
    public boolean updateInfo2(Integer infoId);
    public boolean deleteInfo(Integer infoId);
}
