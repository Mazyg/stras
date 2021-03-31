package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.NiceDetail;
import com.jxnu.stras.domin.Reply;
import com.jxnu.stras.domin.Words;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoService extends IService<Info> {

    public Info getInfoById(Integer infoId);

    public boolean updateInfo(Integer infoId);
    public boolean updateInfo2(Integer infoId);
    public boolean deleteInfo(Integer infoId);
    public List<Info> mainRotate();
    public Integer countByPhone(String Uphone);
    public Integer countByPhoneStatus(String Uphone);
    public List<Info> findInfoByHot();
    public Page<Info> chinesePageInfo(Integer pn);
    public Page<Info> hotPageInfo(Integer pn);
    public Page<Info> manPageInfo(Integer pn);
    public Page<Info> allPageE(Integer pn);
    public List<Info> findInfoBytype(@Param("info_type") String info_type, @Param("start") int start, @Param("length") int length);
    /**
     * 删除收藏记录
     *
     * @param id
     * @return
     */
    public Integer deleteNiceDetail(String id);
    /**
     * 插入收藏记录
     *
     * @param
     * @return
     */
    public Integer insertNiceDetail(String uid,Integer infoId);

    /**
     * 根据用户id和文章id信息查询点赞记录
     *
     * @param
     * @return
     */
    public NiceDetail findNiceDetail(String uname, Integer infoId);
}
