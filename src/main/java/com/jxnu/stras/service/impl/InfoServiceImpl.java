package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Info;
import com.jxnu.stras.domin.NiceDetail;
import com.jxnu.stras.domin.Reply;
import com.jxnu.stras.domin.Words;
import com.jxnu.stras.mapper.InfoMapper;
import com.jxnu.stras.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("InfoService")
public class InfoServiceImpl extends ServiceImpl<InfoMapper,Info> implements InfoService {

    @Resource
    private InfoMapper infoMapper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据id查找信息
     * @param infoId
     * @return
     */
    @Override
    public Info getInfoById(Integer infoId) {
        return infoMapper.getInfoById(infoId);
    }

    /**
     * 文章审核通过
     * @param infoId
     * @return
     */
    @Override
    public boolean updateInfo(Integer infoId) {
        return infoMapper.updateInfo(infoId);
    }

    /**
     * 文章审核不通过
     * @param infoId
     * @return
     */
    @Override
    public boolean updateInfo2(Integer infoId) {
        return infoMapper.updateInfo2(infoId);
    }

    /**
     * 删除话题
     * @param infoId
     * @return
     */
    @Override
    public boolean deleteInfo(Integer infoId) {
        return infoMapper.deleteInfo(infoId);
    }

    /**
     * 按时间查询最新的5条轮播图
     * @return 轮播信息
     */
    @Override
    public List<Info> mainRotate() {
        String key= "首页轮播";
        if(!redisTemplate.hasKey(key)){
            List<Info> infoList = infoMapper.mainRotate();
            redisTemplate.opsForValue().set(key,infoList);
            redisTemplate.expire(key,10, TimeUnit.MINUTES);
            return (List<Info>) redisTemplate.opsForValue().get(key);
        }else{
            return (List<Info>) redisTemplate.opsForValue().get(key);
        }
    }

    @Override
    public Integer countByPhone(String Uphone) {
        return infoMapper.countByPhone(Uphone);
    }

    @Override
    public Integer countByPhoneStatus(String Uphone) {
        return infoMapper.countByPhoneStatus(Uphone);
    }


    /**
     * 侧边信息栏查询
     * @return 信息列表
     */
    @Override
    public List<Info> findInfoByHot() {
        String key = "侧边";
        if(!redisTemplate.hasKey(key)){
            List<Info> infoList = infoMapper.findInfoByHot();
            redisTemplate.opsForValue().set(key,infoList);
            redisTemplate.expire(key,30,TimeUnit.MINUTES);
            return (List<Info>) redisTemplate.opsForValue().get(key);
        }else {
            return (List<Info>) redisTemplate.opsForValue().get(key);
        }
    }

    /**
     * 爱我中华页面分页
     * @param pn 页数
     * @return 页面数据
     */
    @Override
    public Page<Info> chinesePageInfo(Integer pn) {
        String key = "chineseView"+pn;
        Page<Info> infoList;
        if(!redisTemplate.hasKey(key)){
            Page<Info> infoPage = new Page<>(pn,3);
            QueryWrapper<Info> wrapper = new QueryWrapper<>();
            wrapper.eq("info_del",0);
            wrapper.like("info_type","最美");
            infoList = page(infoPage,wrapper);
            redisTemplate.opsForValue().set(key,infoList);
            redisTemplate.expire(key,30, TimeUnit.MINUTES);
            return (Page<Info>) redisTemplate.opsForValue().get(key);
        }else{
           return  (Page<Info>) redisTemplate.opsForValue().get(key);
        }
    }

    /**
     * 热点时事页面分页查询
     * @param pn 页数
     * @return 信息列表
     */
    @Override
    public Page<Info> hotPageInfo(Integer pn) {
       String key = "hotPageInfo"+pn;
       Page<Info> hotList ;
       if(!redisTemplate.hasKey(key)){
           Page<Info> infoPage = new Page<>(pn,4);
           QueryWrapper<Info> wrapper = new QueryWrapper<>();
           wrapper.eq("info_del",0);
           wrapper.eq("info_type","热点时事");
           hotList = page(infoPage,wrapper);
           redisTemplate.opsForValue().set(key,hotList);
           redisTemplate.expire(key,30,TimeUnit.MINUTES);
           return (Page<Info>) redisTemplate.opsForValue().get(key);
       }else {
           return (Page<Info>) redisTemplate.opsForValue().get(key);
       }
    }

    /**
     * 榜样力量分页
     * @param pn 页数
     * @return 信息列表
     */
    @Override
    public Page<Info> manPageInfo(Integer pn) {
        String key = "manPageInfo"+pn;
        Page<Info> infoList;
        if(!redisTemplate.hasKey(key)){
            Page<Info> infoPage = new Page<>(pn,4);
            QueryWrapper<Info> wrapper = new QueryWrapper<>();
            wrapper.eq("info_del",0);
            wrapper.eq("info_type","榜样力量");
             infoList = page(infoPage,wrapper);
             redisTemplate.opsForValue().set(key,infoList);
             redisTemplate.expire(key,30,TimeUnit.MINUTES);
             return (Page<Info>) redisTemplate.opsForValue().get(key);
        }else{
            return (Page<Info>) redisTemplate.opsForValue().get(key);
        }

    }

    /**
     * 全球战役分页
     * @param pn 页数
     * @return 信息2列表
     */
    @Override
    public Page<Info> allPageE(Integer pn) {
       String key = "allPageE"+pn;
       Page<Info> infoList;
       if (!redisTemplate.hasKey(key)){
           Page<Info> infoPage = new Page<>(pn,4);
           QueryWrapper<Info> wrapper = new QueryWrapper<>();
           wrapper.eq("info_del",0);
           wrapper.eq("info_type","全球战疫");
           infoList = page(infoPage,wrapper);
           redisTemplate.opsForValue().set(key,infoList);
           redisTemplate.expire(key,30,TimeUnit.MINUTES);
           return (Page<Info>) redisTemplate.opsForValue().get(key);
       }else {
           return (Page<Info>) redisTemplate.opsForValue().get(key);
       }
    }

    /**
     * 根据文章类别查找
     * @param info_type 类别
     * @param start 开始位置
     * @param length 查询条数
     * @return 文章列表
     */
    @Override
    public List<Info> findInfoBytype(String info_type, int start, int length) {
        String key = info_type + start + length + "";
        if (!redisTemplate.hasKey(key)) {
            List<Info> infoLists = infoMapper.findInfoBytype(info_type, start, length);
            redisTemplate.opsForValue().set(key, infoLists);
            redisTemplate.expire(key, 30, TimeUnit.MINUTES);
            return (List<Info>) redisTemplate.opsForValue().get(key);
        } else {
            return (List<Info>) redisTemplate.opsForValue().get(key);
        }

    }



    @Override
    public Integer insertNiceDetail(String uname,Integer infoId) {
        return infoMapper.insertNiceDetail(uname,infoId);
    }

    @Override
    public Integer deleteNiceDetail(String id) {
        return infoMapper.deleteNiceDetail(id);
    }

    @Override
    public NiceDetail findNiceDetail(String uname,Integer infoId) {
        return  infoMapper.findNiceDetail(uname,infoId);
    }




}
