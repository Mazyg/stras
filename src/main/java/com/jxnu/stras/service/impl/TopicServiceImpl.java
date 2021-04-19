package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.mapper.TopicMapper;
import com.jxnu.stras.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("TopicService")
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Resource
    TopicMapper topicMapper;

    @Autowired
    RedisTemplate redisTemplate;



    /**
     * 通过ID查找话题
     * @param tid
     * @return
     */
    @Override
    public Topic getTopicbyID(Integer tid) {

            Topic topic = topicMapper.getTopicByID(tid);
            return topic;
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

    @Override
    public List<Topic> findTopicByHot() {
        return topicMapper.findTopicByHot();
    }

    /**
     * 用户个人界面界面修改话题
     * @param topic
     * @return
     */
    @Override
    public boolean updateTopic3(Topic topic) {
        return topicMapper.updateTopic3(topic);
    }

    /**
     * 更新话题浏览量，2小时内，用户无论访问多少次，算一次访问量
     * @param tid 话题ID
     * @param request 获取用户信息
     * @return true Or false
     */
    @Override
    public boolean updateView(Integer tid, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        String key;
        if(user !=null){
             key = tid+user.getPhone();
        }else{
            key = tid+"*";
        }

        if(!redisTemplate.hasKey(key)){
            ValueOperations<String,String> operations = redisTemplate.opsForValue();
            operations.set(key,"1");
            redisTemplate.expire(key,2,TimeUnit.HOURS);
        return topicMapper.updateView(tid);
        }else{
            return false;
        }
    }

    @Override
    public Integer topicCountByphone(String phone) {
        return topicMapper.topicCountByphone(phone);
    }

    @Override
    public Integer topicCountByphoneStatus(String phone) {
        return topicMapper.topicCountByphoneStatus(phone);
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

    @Override
    public Page<Topic> getAllTopicByView(IPage<Topic> page, QueryWrapper<Topic> topicWrapper) {
        return topicMapper.getAllTopicByView(page,topicWrapper);
    }

    @Override
    public Page<Topic> getAllTopic(IPage<Topic> page, QueryWrapper<Topic> topicWrapper) {
        return topicMapper.getAllTopic(page,topicWrapper);
    }

}
