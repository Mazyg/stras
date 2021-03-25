package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Course;

import java.util.List;

public interface CourseService extends IService<Course> {

    public Page<Course> coursePage(Integer pn);

    public Course findByID(Integer id);

    public List<Course> searchCourse(String key);
}
