package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Topic;

public interface TopicService extends IService<Topic> {

    public Topic getTopicbyID(Integer tid);

    public boolean updateTopic(Integer tid);

    public boolean updateTopic2(Integer tid);

    public boolean deleteTopic(Integer tid);
}
