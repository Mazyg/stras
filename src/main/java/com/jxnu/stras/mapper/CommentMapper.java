package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxnu.stras.domin.Comment;
import com.jxnu.stras.domin.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Insert("insert into comment(date,phone,rphone,wid,content) " +
            "values('${date}','${phone}','${rphone}','${wid}','${content}')")
    public boolean saveComment(Comment comment);

    @Delete("delete from comment where cid=#{cid}")
    public boolean deleteComment(Integer cid);


    @Select("select * from comment where wid=#{wid}")
    @Result(column = "phone" ,property = "user",
    one = @One(select = "com.jxnu.stras.mapper.UserMapper.getUserByPhone"))
    @Result(column = "rphone" ,property = "ruser",
            one = @One(select = "com.jxnu.stras.mapper.UserMapper.getUserByPhone"))
    public List<Comment> findByWid(Integer wid);

    @Select("select count(*) from comment where rphone = #{rphone}")
    public Integer countByUser(String rphone);

    @Select("select * from comment where rphone = #{rphone} ORDER BY date DESC")
    @Result(column = "phone",property = "user",
    one = @One(select = "com.jxnu.stras.mapper.UserMapper.getUserByPhone"))
    @Result(column = "rphone",property = "ruser",
    one = @One(select = "com.jxnu.stras.mapper.UserMapper.getUserByPhone"))
    @Result(column = "wid",property = "dynamic",
    one = @One(select = "com.jxnu.stras.mapper.DynamicMapper.findDynamicByWid"))
    public Page<Comment> getAllComByrphone(@Param("page") IPage<Comment> page, @Param(Constants.WRAPPER) QueryWrapper<Comment> Wrapper,String rphone);

}
