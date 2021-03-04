package com.jxnu.stras.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.domin.Video;
import com.jxnu.stras.service.InfoService;
import com.jxnu.stras.service.TopicService;
import com.jxnu.stras.service.UserService;
import com.jxnu.stras.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Resource
    UserService userService;

    @Resource
    TopicService topicService;
    @Resource
    InfoService infoService;
    @Resource
    VideoService videoService;

    @GetMapping("/login.html")
    public String loginPage(){
        return "login";
    }

    //重定向防止重复提交
    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password,
                        Model model, HttpServletRequest request){
        User user = userService.getUserByPhone(phone);
        if(user != null && password.equals(user.getPassword())){
            HttpSession session = request.getSession(true);
            session.setAttribute("user",user);
            return "redirect:admin/index.html";
        }else {
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }
    }

    /**
     * 跳转管理员首页
     * @return
     */
    @GetMapping("/admin/index.html")
    public String mainPage(Model model){
        Integer userNum = userService.count();
        QueryWrapper<Topic> wrapperT = new QueryWrapper<>();
        wrapperT.eq("del",0);
        Integer topicNum = topicService.count(wrapperT);
        QueryWrapper<Info> wrapperI = new QueryWrapper<>();
        wrapperI.eq("info_del",0);
        Integer infoNum = infoService.count(wrapperI);
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("Vstatus",0);
        Integer videoNum = videoService.count(videoQueryWrapper);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("Ustatus","受限");
        Integer userNot = userService.count(userQueryWrapper);
        wrapperI.eq("info_status","未审核");
        Integer infoNot = infoService.count(wrapperI);
        wrapperT.eq("Tstatus","未审核");
        Integer topicNot = topicService.count(wrapperT);
        model.addAttribute("userNum",userNum);
        model.addAttribute("topicNum",topicNum);
        model.addAttribute("infoNum",infoNum);
        model.addAttribute("videoNum",videoNum);
        model.addAttribute("userNot",userNot);
        model.addAttribute("infoNot",infoNot);
        model.addAttribute("topicNot",topicNot);
        return "admin/index";
    }

    /**
     * 跳转注册页面
     * @return
     */
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    /**
     * 判断是否已注册
     * @param phone
     * @return
     */
    @ResponseBody
    @PostMapping("/phoneTest")
    public String phoneTest(String phone){
        User user = userService.getUserByPhone(phone);
        if(user != null){
            return "false";
        }
        return "success";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/registerUser")
    public String registerUser(User user){
        System.out.println("user="+user);
        boolean isReg = userService.save(user);
        if(isReg==true){
            return "success";
        }
        return "false";
    }
}
