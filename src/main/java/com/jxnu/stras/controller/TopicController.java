package com.jxnu.stras.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
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
import java.util.List;

@Controller
public class TopicController {

    @Resource
    TopicService topicService;

    @Resource
    UserService userService;

    @Resource
    InfoService infoService;

    /**
     * 用户界面展示所有的话题
     * @param pn 页数
     * @param is 0 按时间排序，1按热度排序
     * @param model
     * @return
     */
    @GetMapping("/user/topic")
    public String userAllTopic(@RequestParam(value = "pn",defaultValue = "1")Integer pn ,@RequestParam(value = "is",defaultValue = "0")Integer is, Model model){
        Page<Topic> topicPage = new Page<>(pn,6);
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("del",0);
        if(is == 1){
            Page<Topic> topicPage1 = topicService.getAllTopicByView(topicPage,wrapper);
            model.addAttribute("topicList",topicPage1);
            model.addAttribute("is",1);
        }else{
            Page<Topic> topics = topicService.getAllTopic(topicPage,wrapper);
            model.addAttribute("topicList",topics);
            model.addAttribute("is",0);
        }
        List<Topic> hotTopic = topicService.findTopicByHot();
        model.addAttribute("hotTopics",hotTopic);
        return "user/topic";
    }

    /**
     * 分页查询所有话题
     * @return
     */
    @GetMapping("/admin/allTopic")
    public String allTopic(@RequestParam(value = "pn",defaultValue = "1")Integer pn , Model model){
        Page<Topic> topicPage = new Page<>(pn,6);
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("del",0);/*0表示话题存在，未被删除*/
        wrapper.select("tid","date","content","phone","tstatus","ttitle","ttype","tview","tresult","del");
        Page<Topic> topicList = topicService.page(topicPage,wrapper);
        System.out.println(topicList);
        long current = topicList.getCurrent();
        long pages = topicList.getPages();
        long total = topicList.getTotal();
        model.addAttribute("topics",topicList);
        return "admin/all_topic";
    }

    /**
     * 分页查询所有未审核话题
     * @return
     */
    @GetMapping("/admin/notTopic")
    public String notTopic(@RequestParam(value = "pn",defaultValue = "1")Integer pn , Model model){
        Page<Topic> topicPage = new Page<>(pn,6);
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("del",0);/*0表示话题存在，未被删除*/
        wrapper.eq("Tstatus","未审核");
        wrapper.select("tid","date","content","phone","tstatus","ttitle","ttype","tview","tresult","del");
        Page<Topic> topicList = topicService.page(topicPage,wrapper);
        System.out.println(topicList);
        long current = topicList.getCurrent();
        long pages = topicList.getPages();
        long total = topicList.getTotal();
        model.addAttribute("topics",topicList);
        return "admin/not_topic";
    }

    /**
     * 话题详情
     * @param tid 话题ID
     *  pn 话题所在页数
     * @param model
     * @return
     */
    @GetMapping("/admin/topicDetail")
    public String topicDetail(@RequestParam("id")Integer tid,@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        Topic topic = topicService.getTopicbyID(tid);
        User user = userService.getUserByPhone(topic.getPhone());
        model.addAttribute("topicDetail",topic);
        model.addAttribute("topicUser",user);
        model.addAttribute("pns",pn);
        return "admin/topic_detail";
    }

    /**
     * 未审核界面跳转话题详情
     * @param tid 话题ID
     * @param pn 未审核话题所在页数
     * @param model
     * @return 话题详情页2
     */
    @GetMapping("/admin/topicDetail2")
    public String topicDetail2(@RequestParam("id")Integer tid,@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        Topic topic = topicService.getTopicbyID(tid);
        User user = userService.getUserByPhone(topic.getPhone());
        model.addAttribute("topicDetail",topic);
        model.addAttribute("topicUser",user);
        model.addAttribute("pns",pn);
        return "admin/topic_detail2";
    }

    /**
     * 添加话题
     * @param topic
     * @param request 获取session值
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/addTopic")
    public String addTopic(Topic topic, HttpServletRequest request){
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        topic.setDate(time);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        topic.setPhone(user.getPhone());
        if("admin".equals(user.getUtype())){
            topic.setTstatus("已审核");
            topic.setTresult("已通过");
        }else {
            topic.setTstatus("未审核");
            topic.setTresult("未通过");
        }
        boolean isAdd = topicService.save(topic);
        if(isAdd==true){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 通过审核
     * @param tid
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/updateTopic")
    public String  updateTopic(Integer tid){
        Topic topic = topicService.getTopicbyID(tid);
        boolean isUpdate = topicService.updateTopic(topic.getTid());
        if("已审核".equals(topic.getTstatus()) || isUpdate==false){
            return "false";
        }else{
            return "success";
        }

    }

    /**
     * 审核不通过
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/updateTopic2")
    public String updateTopic2(Integer tid){
        Topic topic = topicService.getTopicbyID(tid);
        boolean isUpdate = topicService.updateTopic2(topic.getTid());
        if("已审核".equals(topic.getTstatus()) || isUpdate==false){
            return "false";
        }else{
            return "success";
        }
    }

    /**
     * 删除话题
     * @param tid
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/deleteTopic")
    public String deleteTopic(Integer tid){
        boolean idDel = topicService.deleteTopic(tid);
        Topic topic = topicService.getTopicbyID(tid);
        if(idDel==true && topic.getDel()==1){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 用户跳转话题详情
     * @param tid 话题ID
     * @param model
     * @return
     */
    @GetMapping("/user/topicDal")
    public String topicDal(@RequestParam("tid")Integer tid,Model model){
        Topic topic = topicService.getTopicbyID(tid);
        User user = userService.getUserByPhone(topic.getPhone());
        List<Info>  infoList = infoService.findInfoBytype(topic.getTtype(),0,5);
        model.addAttribute("topicDal",topic);
        model.addAttribute("topicUser",user);
        model.addAttribute("infoType",infoList);
        return "user/topDal";
    }
}
