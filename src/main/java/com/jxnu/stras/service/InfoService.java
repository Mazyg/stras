package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoService extends IService<Info> {

    public Info getInfoById(Integer infoId);

    public boolean updateInfo(Integer infoId);
    public boolean updateInfo2(Integer infoId);
    public boolean deleteInfo(Integer infoId);
    public List<Info> mainRotate();
    public List<Info> findInfoByHot();
    public List<Info> findInfoBytype(@Param("info_type") String info_type, @Param("start") int start, @Param("length") int length);
}
