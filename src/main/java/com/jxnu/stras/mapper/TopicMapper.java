package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Topic;
import org.apache.ibatis.annotations.*;

import java.sql.Wrapper;
import java.util.List;

@Mapper

public interface TopicMapper extends BaseMapper<Topic> {

    /**
     * 通过id查找话题
     * @param tid
     * @return
     */
    @Select("select tid,Ttitle,content,date,phone,Tstatus,Tresult,Ttype,Tview,del from topic where tid=#{tid}")
    public Topic getTopicByID(Integer tid);

    /**
     * 审核话题，通过
     * @param tid
     * @return
     */
    @Update("update topic set Tstatus= '已审核',Tresult= '已通过' where tid=#{tid} ")
    public boolean updateTopic(Integer tid);

    /**
     * 话题审核不通过
     * @param tid
     * @return
     */
    @Update("update topic set Tstatus= '已审核',Tresult= '未通过' where tid=#{tid} ")
    public boolean updateTopic2(Integer tid);

    /**
     * 删除话题（逻辑删除）
     * @param tid
     * @return
     */
    @Update("update topic set del = 1 where tid=#{tid}")
    public boolean deleteTopic(Integer tid);

    /**
     * 自定义SQL使用page分页（所有话题按时间）
     * @param page
     * @param topicWrapper
     * @return
     */
    @Select("select * from topic\n" +
            "where Tresult = '已通过' and del = 0\n" +
            "ORDER BY date DESC")
    @Result(column = "phone" ,property = "user",
            many = @Many(select = "com.jxnu.stras.mapper.UserMapper.getUserByPhone"))
    public Page<Topic> getAllTopic(@Param("page") IPage<Topic> page, @Param(Constants.WRAPPER) QueryWrapper<Topic> topicWrapper);



    @Select("select * from topic\n" +
            "where Tresult = '已通过' and del = 0\n" +
            "ORDER BY Tview DESC")
    @Result(column = "phone" ,property = "user",
            many = @Many(select = "com.jxnu.stras.mapper.UserMapper.getUserByPhone"))
    public Page<Topic> getAllTopicByView(@Param("page") IPage<Topic> page, @Param(Constants.WRAPPER) QueryWrapper<Topic> topicWrapper);


    /**
     * 选取浏览量最高的3条话题
     * @return
     */
    @Select("select * from topic\n" +
            "where Tresult = '已通过' and del = 0\n" +
            "ORDER BY Tview DESC\n" +
            "LIMIT 0 , 3")
    public List<Topic> findTopicByHot();

}
