package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    @Select("select * from video where vid=#{vid}")
    public Video getVideoById(Integer vid);

    @Update("update video set Vstatus=1 where vid=#{vid}")
    public boolean deleteVideo(Integer vid);

    /*查询最新的四条视频信息*/
    @Select("SELECT vid,vtitle,vintroduce, date_format(vdate ,'%Y-%m-%d' ) vdate,vphoto,video,vnice FROM `video`\n" +
            "where Vstatus = 0\n" +
            "ORDER  BY Vdate desc\n" +
            "limit 0 ,4")
    public List<Video>  findVideo();

    @Update("update video set Vview= Vview+1 where vid = #{vid}")
    public boolean updateVideoView(Integer vid);
}
