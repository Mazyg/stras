package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.mapper.TopicMapper;
import com.jxnu.stras.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("TopicService")
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Resource
    TopicMapper topicMapper;

    /**
     * 通过ID查找话题
     * @param tid
     * @return
     */
    @Override
    public Topic getTopicbyID(Integer tid) {
        return topicMapper.getTopicByID(tid);
    }

    /**
     * 话题审核通过
     * @param tid 话题ID
     * @return
     */
    @Override
    public boolean updateTopic(Integer tid) {
        return topicMapper.updateTopic(tid);
    }

    /**
     * 话题审核不通过
     * @param tid
     * @return
     */
    @Override
    public boolean updateTopic2(Integer tid) {
        return topicMapper.updateTopic2(tid);
    }

    /**
     * 删除话题（逻辑删除）
     * @param tid
     * @return
     */
    @Override
    public boolean deleteTopic(Integer tid) {
        return topicMapper.deleteTopic(tid);
    }

}
