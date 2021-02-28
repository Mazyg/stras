package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.mapper.InfoMapper;
import com.jxnu.stras.service.InfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("InfoService")
public class InfoServiceImpl extends ServiceImpl<InfoMapper,Info> implements InfoService {

    @Resource
    private InfoMapper infoMapper;

    /**
     * 根据id查找信息
     * @param infoId
     * @return
     */
    @Override
    public Info getInfoById(Integer infoId) {
        return infoMapper.getInfoById(infoId);
    }

    /**
     * 文章审核通过
     * @param infoId
     * @return
     */
    @Override
    public boolean updateInfo(Integer infoId) {
        return infoMapper.updateInfo(infoId);
    }

    /**
     * 文章审核不通过
     * @param infoId
     * @return
     */
    @Override
    public boolean updateInfo2(Integer infoId) {
        return infoMapper.updateInfo2(infoId);
    }

    /**
     * 删除话题
     * @param infoId
     * @return
     */
    @Override
    public boolean deleteInfo(Integer infoId) {
        return infoMapper.deleteInfo(infoId);
    }

    /**
     * 按时间查询最新的5条轮播图
     * @return
     */
    @Override
    public List<Info> mainRotate() {
        return infoMapper.mainRotate();
    }

    /*查询侧边信息*/
    @Override
    public List<Info> findInfoByHot() {
        return infoMapper.findInfoByHot();
    }

    /**
     * 根据文章类别查找
     * @param info_type 类别
     * @param start 开始位置
     * @param length 查询条数
     * @return
     */
    @Override
    public List<Info> findInfoBytype(String info_type, int start, int length) {
        return infoMapper.findInfoBytype(info_type,start,length);
    }
}
