package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.NiceInfo;
import com.jxnu.stras.mapper.NiceInfoMapper;
import com.jxnu.stras.service.NiceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("NiceInfoService")
public class NiceInfoImpl extends ServiceImpl<NiceInfoMapper, NiceInfo> implements NiceInfoService {
    @Resource
    private NiceInfoMapper niceinfoMapper;
    @Override
    public Integer insertNiceDetail(String uPhone, Integer infoId) {
        return niceinfoMapper.insertNiceDetail(uPhone,infoId);
    }

    @Override
    public Integer deleteNiceDetail(Integer id) {
        return niceinfoMapper.deleteNiceDetail(id);
    }

    @Override
    public NiceInfo findNiceDetail(String uPhone, Integer infoId) {
        return niceinfoMapper.findNiceDetail(uPhone,infoId);
    }
}
