package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.Reply;
import com.jxnu.stras.mapper.ReplyMapper;
import com.jxnu.stras.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("ReplyService")
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    @Resource
    ReplyMapper replyMapper;
    /**
     * 保存回复信息内容
     * @param reply 回复信息
     */
    @Override
    public boolean saveReply(Reply reply) {
        return replyMapper.saveReply(reply);
    }

    @Override
    public boolean delReply(String lr_for_words) {
        return replyMapper.delReply(lr_for_words);
    }

    @Override
    public boolean delReply2(String lr_id) {
        return replyMapper.delReply2(lr_id);
    }

    /**
     * 查询所有的回复信息
     * @return 返回查询到的回复信息并存放到List集合中
     */
    @Override
    public List<Reply> findByReply(Integer lrForArticleId) {
        return replyMapper.findByReply(lrForArticleId);
    }

    @Override
    public List<Reply> findByLw(String lrForWords) {
        return replyMapper.findByLw(lrForWords);
    }


}
