package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.mapper.InfoMapper;
import com.jxnu.stras.service.InfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

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
}
