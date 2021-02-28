package com.jxnu.stras.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Resource
    UserService userService;

    /**
     * 分页查询所有用户
     * @param pn 当前页数
     * @param model
     * @return
     */
    @GetMapping("/admin/userInfo")
    public String userInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn , Model model){
        Page<User> userPage = new Page<>(pn,6);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("Utype","user");
        Page<User> userList = userService.page(userPage,wrapper);
        long current = userList.getCurrent();
        long pages = userList.getPages();
        long total = userList.getTotal();
        List<User>  records = userList.getRecords();
        model.addAttribute("userList",userList);
        return "admin/user_info";
    }


    /**
     * 分页查询管理员信息
     * @param pn 页码
     * @param model
     * @return
     */
    @GetMapping("/admin/adminInfo")
    public String adminInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn , Model model){
        Page<User> adminPage = new Page<>(pn,6);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("Utype","admin");
        Page<User> adminList = userService.page(adminPage,wrapper);
        long current = adminList.getCurrent();
        long pages = adminList.getPages();
        long total = adminList.getTotal();
        List<User>  records = adminList.getRecords();
        model.addAttribute("adminList",adminList);
        return "admin/admin_info";
    }

    /**
     * 管理员个信息页面跳转
     * @return
     */
    @GetMapping("/admin/personal")
    public String personalInfo(){
        return "admin/personal";
    }

    /**
     * 修改用户信息
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/updateUser")
    public String updateUser(User user, HttpServletRequest request){
        boolean isUser = userService.updateUser(user);
        if(isUser==true){
            User userUpadte = userService.getUserByPhone(user.getPhone());
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userUpadte);
           return "success";
        }else{
            return "false";
        }
    }

    /**
     * 更换头像
     * @param user
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/admin/updatePhoto")
    public String updatePhoto(User user,HttpServletRequest request,Model model){
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
        return "admin/personal";
    }


    /**
     * 封号
     * @param phone 用户账号
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/restrictUser")
    public String restrictUser(String phone){
        User user = userService.getUserByPhone(phone);
        boolean isRestrict = userService.restrictUser(phone);
        if("正常".equals(user.getUstatus()) && isRestrict == true){
            return "success";
        }else {
            return "false";
        }
    }

    /**
     * 解除限制
     * @param phone
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/disenthral")
    public String disenthral(String phone){
        User user = userService.getUserByPhone(phone);
        boolean isRestrict = userService.disenthral(phone);
        if("受限".equals(user.getUstatus()) && isRestrict == true){
            return "success";
        }else {
            return "false";
        }
    }

    /* @GetMapping("/admin/restrictUser/{phone}")
    public String restrictUser(@PathVariable("phone") String phone, @RequestParam(value = "pn",defaultValue = "1")Integer pn, RedirectAttributes ra){
        boolean isRestrict = userService.restrictUser(phone);
        ra.addAttribute("pn",pn);
        return "redirect:/admin/userInfo";
    }*/
}
