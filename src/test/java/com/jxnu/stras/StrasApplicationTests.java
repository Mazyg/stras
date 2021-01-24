package com.jxnu.stras;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.mapper.UserMapper;
import com.jxnu.stras.service.TopicService;
import com.jxnu.stras.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
class StrasApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private TopicService topicService;

    @Resource
    private UserMapper userMapper;

    @Test
    void topicNum(){

        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("del",0);
        int num = topicService.count(wrapper);
        System.out.println("df"+num);
    }

    @Test
    void contextLoads() {
        User user = userService.getUserByPhone("18379514803");
        System.out.println("user"+user);
    }

    @Test
    void findAll(){
        List<User> userList = userService.list();
        System.out.println("users"+userList);
    }

    @Test
    void topicDetail(){
        Topic topic = topicService.getTopicbyID(1);
        System.out.println("topic"+topic);
        User user = userService.getUserByPhone(topic.getUphone());
        System.out.println("user"+user);
    }

}
