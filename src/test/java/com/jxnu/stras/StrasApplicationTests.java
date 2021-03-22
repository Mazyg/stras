package com.jxnu.stras;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Course;
import com.jxnu.stras.mapper.CourseMapper;
import com.jxnu.stras.utils.SerializeUtil;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.mapper.TopicMapper;
import com.jxnu.stras.mapper.UserMapper;
import com.jxnu.stras.service.InfoService;
import com.jxnu.stras.service.TopicService;
import com.jxnu.stras.service.UserService;
import net.sf.jsqlparser.statement.select.Top;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class StrasApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private TopicService topicService;

    @Resource
    private InfoService infoService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    CourseMapper courseMapper;

    @Autowired
    RedisTemplate redisTemplate;


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
        User user = userService.getUserByPhone(topic.getPhone());
        System.out.println("user"+user);
    }

    @Test
    void findInfo(){
        List<Info> model = infoService.findInfoBytype("新时代楷模",0,3);
        System.out.println("bfj==="+model);
    }

    @Test
    void findTopic(){
        Page<Topic> topicPage = new Page<>(0,6);
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        Page<Topic> topic = topicMapper.getAllTopic(topicPage,wrapper);
        System.out.println("tr=="+topic);
    }

    @Test
    void findComm(){
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.set("test","retg");
        String tr =operations.get("test");
        System.out.println("drth=="+tr);
    }

    @Test
    void redis(){
       /* String key = "20";

        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.set(key,"1");
        redisTemplate.expire(key,2,TimeUnit.HOURS);*/

        List<Info> model = infoService.findInfoBytype("新时代楷模",0,3);
        redisTemplate.opsForValue().set("modela",model);
        redisTemplate.expire("modela",10,TimeUnit.MINUTES);
        System.out.println(redisTemplate.opsForValue().get("modela"));


        /*Topic topic = topicMapper.getTopicByID(20);
            redisTemplate.opsForValue().set(key, topic);
            redisTemplate.expire(key,12, TimeUnit.HOURS);
        System.out.println(redisTemplate.opsForValue().get(key));*/
    }


    @Test
    public void search(){
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.like("course","经济").or().like("Tname","毛");
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);
        System.out.println("vfgb="+courseList);
    }

    @Test
    public void vourse(){
        Course course = courseMapper.findByID(2);
        System.out.println("f=="+course);
    }
}
