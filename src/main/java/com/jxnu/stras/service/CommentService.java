package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

    public boolean saveComment(Comment comment);
    public boolean deleteComment(Integer cid);
    public List<Comment> findByWid(Integer wid);
}
