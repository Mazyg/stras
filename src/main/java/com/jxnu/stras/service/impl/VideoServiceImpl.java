package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Video;
import com.jxnu.stras.mapper.VideoMapper;
import com.jxnu.stras.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("VideoService")
public class VideoServiceImpl extends ServiceImpl<VideoMapper,Video> implements VideoService {

    @Resource
    VideoMapper videoMapper;
    @Override
    public Video getVideoById(Integer vid) {
        return videoMapper.getVideoById(vid);
    }

    @Override
    public boolean deleteVideo(Integer vid) {
        return videoMapper.deleteVideo(vid);
    }
}
