package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Topic;
import com.jxnu.stras.domin.Words;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface WordsMapper extends BaseMapper<Words>{

    /**
     *
     * @param words
     * 保存留言信息
     */
    @Insert("insert into words(\n" +
            "          lwPhone,\n" +
            "          lwDate,\n" +
            "          lwContent,\n" +
            "          lwForArticleId\n" +
            "        )\n" +
            "        values(\n" +
            "          #{lwPhone},\n" +
            "          #{lwDate},\n" +
            "          #{lwContent},\n" +
            "          #{lwForArticleId}\n" +
            "        )")
    public Integer saveWords(Words words);

    /**
     * 删除留言信息
     */
    @Delete("DELETE from words\n" +
            "where lwId=#{lwId}")
    public  boolean delWords(String lwId);

    /**
     * 查询所有留言信息
     */
    @Select("select * from words where lwForArticleId=#{lwForArticleId}")
    public List<Words> findWordsByInfoId(Integer lwForArticleId);

}
