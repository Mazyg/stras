package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Words;

import java.util.List;

public interface WordsService extends IService<Words>{
    public Integer saveWords(Words words);
    public boolean delWords(String lw_id);
    public List<Words> findWordsByInfoId(Integer lwForArticleId);
}
