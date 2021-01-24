package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {

    /**
     * 通过id查找话题
     * @param tid
     * @return
     */
    @Select("select tid,Ttitle,content,date,Uphone,Tstatus,Tresult,Ttype,Tview,del from topic where tid=#{tid}")
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
}
