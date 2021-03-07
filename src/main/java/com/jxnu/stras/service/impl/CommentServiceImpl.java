package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Comment;
import com.jxnu.stras.mapper.CommentMapper;
import com.jxnu.stras.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("CommentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    CommentMapper commentMapper;

    @Override
    public boolean saveComment(Comment comment) {
        return commentMapper.saveComment(comment);
    }

    @Override
    public boolean deleteComment(Integer cid) {
        return commentMapper.deleteComment(cid);
    }

    @Override
    public List<Comment> findByWid(Integer wid) {
        return commentMapper.findByWid(wid);
    }
}
