package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InfoMapper extends BaseMapper<Info> {

    /**
     * 根据id查找信息
     * @param infoId
     * @return
     */
    @Select("select * from info where info_id=#{infoId}")
    public Info getInfoById(Integer infoId);

    /**
     * 信息审核通过
     * @param infoId
     * @return
     */
    @Update("update info set info_status='已通过' where info_id=#{infoId}")
    public boolean updateInfo(Integer infoId);

    /**
     * 信息审核未通过
     * @param infoId
     * @return
     */
    @Update("update info set info_status='未通过' where info_id=#{infoId}")
    public boolean updateInfo2(Integer infoId);

    /**
     * 删除信息
     * @param infoId
     * @return
     */
    @Update("update info set info_del=1 where info_id=#{infoId}")
    public boolean deleteInfo(Integer infoId);


}
