package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Insert("insert into comment(date,phone,rphone,wid,content) " +
            "values('${date}','${phone}','${rphone}','${wid}','${content}')")
    public boolean saveComment(Comment comment);

    @Delete("delete from comment where cid=#{cid}")
    public boolean deleteComment(Integer cid);

    @Select("select * from comment where wid=#{wid}")
    public List<Comment> findByWid(Integer wid);
}
