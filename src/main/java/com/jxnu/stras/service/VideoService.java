package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Video;
import org.apache.ibatis.annotations.Select;

public interface VideoService extends IService<Video> {

    public Video getVideoById(Integer vid);
    public boolean deleteVideo(Integer vid);
}
