package com.jxnu.stras.controller;

import com.jxnu.stras.domin.Dynamic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.service.DynamicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DynamicController {

    @Resource
    DynamicService dynamicService;


    @RequestMapping("/user/saveDynamic")
    @ResponseBody
    public Dynamic saveDynamic(Dynamic dynamic, HttpServletRequest request){
        System.out.println("收到的dynamic="+dynamic);
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        dynamic.setDate(time);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        dynamic.setPhone(user.getPhone());
        boolean result = dynamicService.saveDynamic(dynamic);
        if (result){
            return dynamic;
        }else {
            return null;
        }
    }


    @RequestMapping("/user/deleteDynamic")
    @ResponseBody
    public  String deleteDynamic(Integer wid){
        boolean result = dynamicService.deleteDynamic(wid);
        if (result){
            return "success";
        }
        return "false";
    }
}
