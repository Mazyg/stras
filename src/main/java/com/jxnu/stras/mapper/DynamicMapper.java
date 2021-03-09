package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.Dynamic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface DynamicMapper extends BaseMapper<Dynamic> {
    @Select("select * from dynamic where tid = #{tid}")
    public List<Dynamic> findByTid(Integer tid);

    @Insert("insert into dynamic(date,content,phone,tid) values('${date}','${content}','${phone}','${tid}')")
    public boolean saveDynamic(Dynamic dynamic);

    @Delete("delete from dynamic where wid=#{wid} ")
    public boolean deleteDynamic(Integer wid);

    @Select("select tid from dynamic where wid = #{wid}")
    Integer findTidByWid(Integer wid);

    @Select("select * from dynamic where wid = #{wid}")
    public Dynamic findDynamicByWid(Integer wid);



}
