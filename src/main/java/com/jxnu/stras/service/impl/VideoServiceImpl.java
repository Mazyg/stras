package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Video;
import com.jxnu.stras.mapper.VideoMapper;
import com.jxnu.stras.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("VideoService")
public class VideoServiceImpl extends ServiceImpl<VideoMapper,Video> implements VideoService {

    @Resource
    VideoMapper videoMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Video getVideoById(Integer vid) {
        return videoMapper.getVideoById(vid);
    }

    @Override
    public boolean deleteVideo(Integer vid) {
        return videoMapper.deleteVideo(vid);
    }

    @Override
    public List<Video> findVideo() {
        String key = "allVideo";
        List<Video>  videoList;
        if(!redisTemplate.hasKey(key)){
            videoList = videoMapper.findVideo();
            redisTemplate.opsForValue().set(key,videoList);
            redisTemplate.expire(key,20, TimeUnit.MINUTES);
            return (List<Video>) redisTemplate.opsForValue().get(key);
        }else{
            return (List<Video>) redisTemplate.opsForValue().get(key);
        }
    }

    @Override
    public boolean updateVideoView(Integer vid) {
        return videoMapper.updateVideoView(vid);
    }
}
