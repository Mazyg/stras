package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Words;
import com.jxnu.stras.mapper.WordsMapper;
import com.jxnu.stras.service.WordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("WordsService")
public class WordsServiceImpl extends ServiceImpl<WordsMapper, Words> implements WordsService {
    @Resource
    WordsMapper wordsMapper;
    /**
     * 保存留言信息功能
     * @param words 保存的信息
     */
    @Override
    public Integer saveWords(Words words) {
        return wordsMapper.saveWords(words);
    }

    @Override
    public boolean delWords(String lwId){return wordsMapper.delWords(lwId);}

    /**
     * 查询所有留言信息
     * @return 返回查询查询到的留言信息并存放到List集合中
     * @param lwForArticleId
     */
    @Override
    public List<Words> findWordsByInfoId(Integer lwForArticleId) {
            return wordsMapper.findWordsByInfoId(lwForArticleId);

    }

}
