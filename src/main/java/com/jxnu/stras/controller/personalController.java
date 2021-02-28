package com.jxnu.stras.controller;

import com.jxnu.stras.domin.User;
import com.jxnu.stras.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class personalController {
    @Resource
    UserService userService;

    /**
     * 跳转用户个人信息界面
     * @return
     */
    @GetMapping("/user/personalInfo")
    public String personalInfo(){
        return "user/personal";
    }

    /**
     * 用户更换头像
     * @param user
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/user/updatePhoto")
    public String updatePhoto(User user, HttpServletRequest request, Model model){
        User users = (User) request.getSession().getAttribute("user");
        users.setPhoto(user.getPhoto());
        boolean isPhoto = userService.updatePhoto(users);
        if(isPhoto==true){
            User user1 = userService.getUserByPhone(users.getPhone());
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user1);
        }else{
            model.addAttribute("msg","出错了！");
        }
        return "user/personal";
    }

}
