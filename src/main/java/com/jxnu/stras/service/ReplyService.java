package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.Reply;
import com.jxnu.stras.domin.Topic;

import java.util.List;

public interface ReplyService extends IService<Reply> {
    public boolean saveReply(Reply reply);
    public boolean delReply(String lr_for_words);
    public boolean delReply2(String lr_id);
    public List<Reply> findByReply(Integer lrForArticleId);

}
