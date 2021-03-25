package com.jxnu.stras.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.*;
import com.jxnu.stras.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CourseController {

    @Resource
    CourseService courseService;

    @Resource
    CvideoService cvideoService;

    @Resource
    SchoolService schoolService;

    @Resource
    AddService addService;

    @Resource
    VideoService videoService;

    /**
     * 跳转课程信息
     * @return 页面
     */
    @GetMapping("/admin/putCourse")
    public String putCourse(){
        return "admin/put_course";
    }

    /**
     * 视频发布页面
     * @return
     */
    @GetMapping("/admin/putCvideo")
    public String putCvideo(){
        return "admin/put_cVideo";
    }

    /**
     * 保存课程信息
     * @param course 实体
     * @return boolean
     */
    @ResponseBody
    @PostMapping("/admin/saveCourse")
    public String saveCouse(Course course){
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        course.setDate(time);
        boolean isSave = courseService.save(course);
        if(isSave==true){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 发布视频
     * @param cvideo
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/saveCvideo")
    public String saveCvideo(Cvideo cvideo){
        boolean is = cvideoService.save(cvideo);
        if(is==true){
            return "success";
        }else{
            return "false";
        }
    }

    @GetMapping("/user/searchCourse")
    public String searchCourse(@RequestParam("key") String key,Model model){
        System.out.println("k=="+key);
        List<Course> courses = courseService.searchCourse(key);
        model.addAttribute("courseList",courses);
        School school = schoolService.findSchool();
        List<Add> adds = addService.findAllAdd();
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        model.addAttribute("school",school);
        model.addAttribute("addList",adds);
        return "user/search";
    }


    /**
     * 跳转课程思政页面
     * @return
     */
    @GetMapping("/user/showCourse")
    public String showCourse(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        School school = schoolService.findSchool();
        List<Add> adds = addService.findAllAdd();
        Page<Course> courseList = courseService.coursePage(pn);
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        model.addAttribute("school",school);
        model.addAttribute("addList",adds);
        model.addAttribute("courses",courseList);

        return "user/course";
    }

    /**
     * 跳转课程详情
     * @param pn 页码
     * @param model 容器
     * @param id 课程ID
     * @return 课程信息，视频
     */
    @GetMapping("/user/courseDeal")
    public String courseDeal(@RequestParam(value = "pns",defaultValue = "1")Integer pns,@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model,Integer id){
        Course course = courseService.findByID(id);
        Page<Cvideo> cvideoPage = cvideoService.findByCid(id,pns);
        model.addAttribute("courseDea",course);
        model.addAttribute("cvideoList",cvideoPage);
        model.addAttribute("pn",pn);
        return "user/courseDal";
    }
}
