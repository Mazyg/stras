package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Comment;
import com.jxnu.stras.domin.Topic;
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

    @Override
    public Integer countByUser(String rphone) {
        return commentMapper.countByUser(rphone);
    }

    @Override
    public Page<Comment> getAllComByrphone(IPage<Comment> page, QueryWrapper<Comment> Wrapper,String rphone) {
        return commentMapper.getAllComByrphone(page,Wrapper,rphone);
    }


}
