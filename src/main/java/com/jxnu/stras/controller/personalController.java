package com.jxnu.stras.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Comment;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.service.CommentService;
import com.jxnu.stras.service.InfoService;
import com.jxnu.stras.service.TopicService;
import com.jxnu.stras.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class personalController {
    @Resource
    UserService userService;

    @Resource
    TopicService topicService;

    @Resource
    InfoService infoService;

    @Resource
    CommentService commentService;

    public void nums(HttpServletRequest request,Model model){
        User users = (User) request.getSession().getAttribute("user");
        Integer TopicNum = topicService.topicCountByphone(users.getPhone());
        Integer infoNum = infoService.countByPhone(users.getPhone());
        Integer TopicNums = topicService.topicCountByphoneStatus(users.getPhone());
        Integer infoNums = infoService.countByPhoneStatus(users.getPhone());
        Integer statusNum = TopicNums+infoNums;
        Integer newNum = commentService.countByUser(users.getPhone());
        model.addAttribute("topicNum",TopicNum);
        model.addAttribute("infoNum",infoNum);
        model.addAttribute("statusNum",statusNum);
        model.addAttribute("newNums",newNum);
    }


    /**
     * 跳转用户个人信息界面
     * @return
     */
    @GetMapping("/person/personalInfo")
    public String personalInfo(HttpServletRequest request,Model model){
        nums(request,model);
        return "user/personal";
    }

    /**
     * 用户更换头像
     * @param user
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/person/updatePhoto")
    public String updatePhoto(User user, HttpServletRequest request, Model model){
        User users = (User) request.getSession().getAttribute("user");
        users.setPhoto(user.getPhoto());
        nums(request,model);
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

    /**
     * 用户查看自己所有话题
     * @param model
     * @return
     */
    @GetMapping("/person/personTopic")
    public String allTopicByUser(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model,HttpServletRequest request){
        Page<Topic> topicPage = new Page<>(pn,8);
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        topicQueryWrapper.eq("phone",user.getPhone());
        topicQueryWrapper.eq("del",0);
        topicQueryWrapper.select("tid","date","content","phone","tstatus","ttitle","ttype","tview","tresult","del");
        Page<Topic> topicList = topicService.page(topicPage,topicQueryWrapper);
        model.addAttribute("topics",topicList);
        nums(request,model);
        return "user/personal_topic";
    }

    /**
     * 个人中心跳转话题详情
     * @param tid 话题id
     * @param pn 页数
     * @param model
     * @return
     */
    @GetMapping("/person/personalTopicDal")
    public String topicDetal(@RequestParam("tid")Integer tid,@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model,HttpServletRequest request){
        Topic topic = topicService.getTopicbyID(tid);
        model.addAttribute("topicDetail",topic);
        model.addAttribute("pn",pn);
        nums(request,model);
        return "user/personal_topicDal";
    }

    /**
     * 用户修改话题
     * @param tid 话题ID
     * @param content 话题内容
     * @param Ttitle 话题标题
     * @return
     */
    @ResponseBody
    @PostMapping("/person/updateTopicDal")
    public String topicUpdate(Integer tid,String content,String Ttitle){
        System.out.println("top=="+tid);
        Topic topic = topicService.getTopicbyID(tid);
        topic.setTtitle(Ttitle);
        topic.setContent(content);
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        topic.setDate(time);
        boolean isUp = topicService.updateTopic3(topic);
        if(isUp){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 用户发布话题界面跳转
     * @return
     */
    @GetMapping("/person/putInfo")
    public String putInfoUser(HttpServletRequest request,Model model){
        nums(request,model);
        return "user/personal_putInfo";
    }

    /**
     * 用户消息
     * @param request
     * @param model
     * @param pn
     * @return
     */
    @GetMapping("/person/userNews")
    public String userNews(HttpServletRequest request,Model model,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        User users = (User) request.getSession().getAttribute("user");
        System.out.println("user=="+users);
        Page<Comment> commentPage = new Page<>(pn,5);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        Page<Comment> commentPage1 = commentService.getAllComByrphone(commentPage,wrapper,users.getPhone());
        model.addAttribute("comments",commentPage1);
        nums(request,model);
        return "user/personal_news";
    }

    /**
     * 跳转用户个人文章列表
     * @param pn 跳转页数
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/person/userInfo")
    public String userInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model,HttpServletRequest request){
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        Page<Info> infoPage = new Page<>(pn,8);
        QueryWrapper<Info> wrapper = new QueryWrapper<>();
        wrapper.eq("Uphone",user.getPhone());
        wrapper.eq("info_del",0);
        Page<Info> infoPage1 = infoService.page(infoPage,wrapper);
        model.addAttribute("infoList",infoPage1);
        nums(request,model);
        return "user/personal_info";
    }

    /**
     * 用户查看文章详情
     * @param pn
     * @param infoId
     * @param model
     * @return
     */
    @GetMapping("/person/infoDetail")
    public String infoDetail(@RequestParam(value = "pn" ,defaultValue = "1")Integer pn,Integer infoId,Model model,HttpServletRequest request){
        Info info = infoService.getInfoById(infoId);
        model.addAttribute("infoDetail",info);
        model.addAttribute("pn",pn);
        nums(request,model);
        return "user/personal_InfoDal";
    }
}
