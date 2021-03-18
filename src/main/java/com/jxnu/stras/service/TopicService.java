package com.jxnu.stras.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Topic;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TopicService extends IService<Topic> {

    public Topic getTopicbyID(Integer tid);

    public boolean updateTopic(Integer tid);

    public boolean updateTopic2(Integer tid);
    public List<Topic> findTopicByHot();
    public boolean updateTopic3(Topic topic);
    public boolean updateView(Integer tid, HttpServletRequest request);
    public Integer topicCountByphone(String phone);
    public Integer topicCountByphoneStatus(String phone);


    public boolean deleteTopic(Integer tid);
    public Page<Topic> getAllTopicByView(@Param("page") IPage<Topic> page, @Param(Constants.WRAPPER) QueryWrapper<Topic> topicWrapper);
    public Page<Topic> getAllTopic(@Param("page") IPage<Topic> page, @Param(Constants.WRAPPER) QueryWrapper<Topic> topicWrapper);

}
