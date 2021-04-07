package com.jxnu.stras;

import com.jxnu.stras.domin.Words;
import com.jxnu.stras.service.WordsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InfoMapperTest{
    @Autowired
    WordsService wordsService;
    @Test
    public void testInfo(){
        System.out.println(111);
        List<Words> wordList = wordsService.findWordsByInfoId(32);;
            for (Words words : wordList) {
                System.out.println(words);
        }
    }
    @Test
    public void saveWord(){
        Words words = new Words();
        words.setLwPhone("23434");
        words.setLwContent("jdskdjfkjd");
        words.setLwForArticleId("102");
        System.out.println(words);
        Integer flag = wordsService.saveWords(words);
        System.out.println(flag);

    }
}
