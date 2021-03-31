package com.jxnu.stras.controller;

import com.jxnu.stras.domin.*;
import com.jxnu.stras.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class WordsReplyController {
    @Resource
    InfoService infoService;

    @Resource
    VideoService videoService;

    @Resource
    WordsService wordsService;

    @Resource
    UserService userService;

    @Resource
    ReplyService replyService;

    /**
     * 跳转文章详情
     */
    //声明用于存放留言回复信息的List集合
    private List<Words> lw_list;
    private List<Reply> lr_list;
    private User user;
    @GetMapping("/user/infoDal")
    public String articleDeal(Model model, @RequestParam("infoId")Integer infoId, HttpServletRequest request){
        Info info = infoService.getInfoById(infoId);
        User user = (User) request.getSession().getAttribute("user");
        String uphone;
        if(user==null){uphone=null;}
        else{uphone=user.getPhone();}
        model.addAttribute("uphone",uphone);
        log.info("用户："+uphone);
//        -----------------------------点赞----------------------------------------
        //获取文章点赞数
//        int niceNum=info.getNice();
//        log.info("点赞量"+niceNum);
//        model.addAttribute("niceNum",niceNum);
//        NiceDetail niceDetail=infoService.findNiceDetail(uname,infoId);
//        request.getSession().setAttribute("color","like");
//        if (niceDetail!=null){
//            request.getSession().getAttribute("color");
//            request.getSession().setAttribute("color","like");
//        }else{
//            request.getSession().setAttribute("color","unlike");
//        }

//       ------------------------------------- 留言-------------------------------------
        //封装留言信息(根据文章id查留言)
        lw_list = wordsService.findWordsByInfoId(infoId);
        lr_list = replyService.findByReply(infoId);
        //根据用户手机号找到用户名
        for (Words words: lw_list) {
            User u=new User();
            u=userService.getUserByPhone(words.getLwPhone());
            words.setLwName(u.getUname());
        }
        //根据用户手机号找到用户名
        for (Reply reply: lr_list) {
            User u1=new User();
            u1=userService.getUserByPhone(reply.getLrPhone());
            reply.setLrName(u1.getUname());
            User u2=new User();
            u2=userService.getUserByPhone(reply.getLrForPhone());
            reply.setLrForName(u2.getUname());

        }
//        log.info("获取留言"+lw_list);
        //封装回复信息（根据文章id查询所有回复）
//        log.info("获取回复"+lr_list);
        model.addAttribute("lw_list",lw_list);
        model.addAttribute("lr_list",lr_list);
//        -----------------------------------侧边栏-----------------------------
        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videoList",videoList);
        List<Info> hot = infoService.findInfoByHot();
        model.addAttribute("hot",hot);


        model.addAttribute("info",info);
        model.addAttribute("user",user);

        return "user/infoDal";
    }
    /**
     * 保存留言信息
     */
    @PostMapping("/user/saveWords")
    public String saveWords(Words words,Model model){
        if(words != null){
            log.info("留言："+words);
            wordsService.saveWords(words);
            String infoId=words.getLwForArticleId();
            model.addAttribute("infoId",infoId);
            return "redirect:infoDal?infoId="+infoId;
        }else{
            return null;
        }

    }

    /**
     * 保存回复信息
     */
    @ResponseBody
    @GetMapping("/user/saveReply")
    public String saveReply(Reply reply,Model model){
            log.info("1111111111111111111111111");
            log.info("reply:"+reply);
            boolean result=replyService.saveReply(reply);
            System.out.println("回复结果："+result);
//            String infoId=reply.getLrForArticleId();
//            model.addAttribute("infoId",infoId);
            if(result==true){
                return "success";
            }
        else{
            return "false";
        }

    }
    /**
     * 删除留言信息
     */
    @ResponseBody
    @PostMapping("/user/delWords")
    public String delWords(String lwId){
        boolean r1=replyService.delReply(lwId);
        boolean r2=wordsService.delWords(lwId);
        log.info("r1="+r1);
        log.info("r2="+r2);
        if(r2==true && r1==true){
            return "success";
        }else{
            return "false";
        }
    }


    /**
     * 删除回复信息
     */
    @ResponseBody
    @PostMapping("/user/delReply")
    public String delReply(String lrId){
//        log.info("lrId"+lrId);
        boolean r=replyService.delReply2(lrId);
//        log.info("删除回复结果"+r);
        if(r==true){
            return "success";
        }else{
            return "false";
        }
    }

}
