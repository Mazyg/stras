package com.jxnu.stras.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Comment;
import com.jxnu.stras.domin.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService extends IService<Comment> {

    public boolean saveComment(Comment comment);
    public boolean deleteComment(Integer cid);
    public List<Comment> findByWid(Integer wid);
    public Integer countByUser(String rphone);
    public Page<Comment> getAllComByrphone(@Param("page") IPage<Comment> page, @Param(Constants.WRAPPER) QueryWrapper<Comment> Wrapper,String rphone);

}
