package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Info;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 刷新文章点赞数
     * @param
     * @return
     */
    @Update("update info set title=#{title},info_type=#{infoType},introduce=#{introduce} ,content=#{content},date=#{date}, photo=#{photo},nice=#{nice},view=#{view},info_status=#{infoStatus},Uphone=#{Uphone},info_del=#{infoDel},Utype=#{Utype} where info_id=#{infoId}")
    public boolean updateInfo3(Info info);

    /**
     * 按时间查询最新的5条轮播图
     * @return
     */
    @Select("SELECT * FROM `info`\n" +
            "where info_type = '首页轮播' and info_del = 0 and info_status = '已通过'\n" +
            "ORDER  BY date desc\n" +
            "limit 0 ,5")
    public List<Info> mainRotate();

    @Select("select info_id,title,date_format(date ,'%Y-%m-%d' ) date\n" +
            "from info\n" +
            "where info_del = 0 and info_type='热点时事'\n" +
            "ORDER BY date DESC\n" +
            "limit 0,6\n")
    public List<Info> findInfoByHot();

    @Select("select count(*) from info where Uphone = #{Uphone}")
    public Integer countByPhone(String Uphone);

    @Select("select count(*) from info where Uphone = #{Uphone} and info_status = '未审核'")
    public Integer countByPhoneStatus(String Uphone);

    @Update("update info set view = view+1 where info_id = #{infoId}")
    public boolean updateInfoView(Integer infoId);


    /**
     * 通过文章类别搜索文章
     * @param info_type 类别
     * @param start 第几页
     * @param length 长度
     * @return
     */
    @Select("SELECT  *\n" +
            "FROM `info`\n" +
            "where info_type like #{info_type} and info_del = 0\n"+
            "order by date desc\n"+"limit #{start},#{length}")
    public List<Info> findInfoBytype(@Param("info_type") String info_type, @Param("start") int start, @Param("length") int length);

}
