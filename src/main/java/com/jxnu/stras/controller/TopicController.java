package com.jxnu.stras.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.User;
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
public class TopicController {

    @Resource
    TopicService topicService;

    @Resource
    UserService userService;

    /**
     * 分页查询所有话题
     * @return
     */
    @GetMapping("/admin/allTopic")
    public String allTopic(@RequestParam(value = "pn",defaultValue = "1")Integer pn , Model model){
        Page<Topic> topicPage = new Page<>(pn,6);
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("del",0);/*0表示话题存在，未被删除*/
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
        User user = userService.getUserByPhone(topic.getUphone());
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
        User user = userService.getUserByPhone(topic.getUphone());
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
        topic.setUphone(user.getPhone());
        topic.setTstatus("已审核");
        topic.setTresult("已通过");
        System.out.println("topic"+topic);
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
}
