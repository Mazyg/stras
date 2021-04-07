package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.NiceInfo;

public interface NiceInfoService extends IService<NiceInfo> {
    /**
     * 插入点赞记录
     *
     * @param
     * @return
     */
    public Integer insertNiceDetail(String uPhone,Integer infoId);

    /**
     * 删除点赞记录
     *
     * @param id
     * @return
     */
    public Integer deleteNiceDetail(Integer id);


    /**
     * 根据用户id和文章id信息查询点赞记录
     *
     * @param
     * @return
     */
    public NiceInfo findNiceDetail(String uPhone, Integer infoId);

}
