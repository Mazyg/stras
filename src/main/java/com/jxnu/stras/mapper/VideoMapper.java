package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    @Select("select * from video where vid=#{vid}")
    public Video getVideoById(Integer vid);

    @Update("update video set Vstatus=1 where vid=#{vid}")
    public boolean deleteVideo(Integer vid);
}
