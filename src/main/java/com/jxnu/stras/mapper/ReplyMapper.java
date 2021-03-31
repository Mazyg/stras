package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {
    //保存回复信息
    @Insert("  insert into reply(\n" +
            "          lrPhone,\n" +
            "          lrDate,\n" +
            "          lrContent,\n" +
            "          lrForPhone,\n" +
            "          lrForWords,\n" +
            "          lrForReply,\n" +
            "          lrForArticleId\n" +
            "        )\n" +
            "        values(\n" +
            "          #{lrPhone},\n" +
            "          #{lrDate},\n" +
            "          #{lrContent},\n" +
            "          #{lrForPhone},\n" +
            "          #{lrForWords},\n" +
            "          #{lrForReply},\n" +
            "          #{lrForArticleId}\n" +
            "        )")
    public  boolean saveReply(Reply reply);

    //    删除某一留言下回复信息
    @Delete("DELETE from reply\n" +
            "where lrForWords=#{lrForWords}")
    public boolean delReply(String lrForWords);

    /**
     *   删除某一回复信息
     */
    @Delete("DELETE from reply\n" +
            "where lrId=#{lrId}")
    public boolean delReply2(String lrId);

    /**
     *     根据文章查询所有回复信息
     */
    @Select("select * from reply where lrForArticleId=#{lrForArticleId}")
    public  List<Reply> findByReply(Integer lrForArticleId);
}
