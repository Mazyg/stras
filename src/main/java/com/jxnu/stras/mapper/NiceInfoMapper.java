package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.NiceInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NiceInfoMapper extends BaseMapper<NiceInfo> {
    /**
     * 插入点赞记录
     */
    @Insert("insert into nicedetail(uPhone,contentId,createTime)\n" +
            "VALUES(#{uPhone},#{contentId},NOW())")
    Integer insertNiceDetail(@Param("uPhone") String uPhone, @Param("contentId") Integer infoId);

    /**
     * 删除点赞记录
     *
     * @param id
     * @return
     */
    @Delete("DELETE from nicedetail\n" +
            "where id=#{id}")
    Integer deleteNiceDetail(Integer id);
    /**
     * 根据用户id和文章id信息查询点赞记录
     *
     * @param
     * @param contentId
     * @return
     */
    @Select("select *\n" +
            "from nicedetail\n" +
            "where uPhone=#{uPhone}" +
            " and contentId=#{contentId}")
    NiceInfo findNiceDetail(@Param("uPhone") String uPhone, @Param("contentId") Integer contentId);
}
