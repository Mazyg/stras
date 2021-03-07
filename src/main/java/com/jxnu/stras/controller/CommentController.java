package com.jxnu.stras.controller;

import com.jxnu.stras.domin.Comment;
import com.jxnu.stras.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {

    @Resource
    CommentService commentService;

    @RequestMapping("/user/saveComment")
    @ResponseBody
    public Comment saveComment(@RequestBody Comment comment){
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(now);
        comment.setDate(time);
        boolean result = commentService.saveComment(comment);
        if (result){
            return comment;
        }else {
            return null;
        }
    }

    @RequestMapping("/user/deleteComment")
    @ResponseBody
    public  String deleteComment(Integer cid){
        boolean result = commentService.deleteComment(cid);
        if (result){
            return "success";
        }
        return "false";
    }
}
