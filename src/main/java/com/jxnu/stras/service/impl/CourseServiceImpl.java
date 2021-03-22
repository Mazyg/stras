package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Course;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.mapper.CourseMapper;
import com.jxnu.stras.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("CourseService")
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    CourseMapper courseMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Page<Course> coursePage(Integer pn) {
        String key = "coursePage"+pn;
        Page<Course> courseList;
        if(!redisTemplate.hasKey(key)){
            Page<Course> coursePage = new Page<>(pn,7);
            QueryWrapper<Course> wrapper = new QueryWrapper<>();
            courseList = page(coursePage,wrapper);
            redisTemplate.opsForValue().set(key,courseList);
            redisTemplate.expire(key,30, TimeUnit.MINUTES);
            return (Page<Course>) redisTemplate.opsForValue().get(key);
        }else{
            return  (Page<Course>) redisTemplate.opsForValue().get(key);
        }
    }

    @Override
    public Course findByID(Integer id) {
        String key = "course"+id;
        Course course;
        if(!redisTemplate.hasKey(key)){
            course = courseMapper.findByID(id);

            redisTemplate.opsForValue().set(key,course);
            redisTemplate.expire(key,20,TimeUnit.MINUTES);
           return (Course) redisTemplate.opsForValue().get(key);
        }else {
            return (Course) redisTemplate.opsForValue().get(key);
        }
    }

    @Override
    public List<Course> searchCourse(String key) {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.like("course",key).or().like("Tname",key);
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);
        return courseList;
    }
}
