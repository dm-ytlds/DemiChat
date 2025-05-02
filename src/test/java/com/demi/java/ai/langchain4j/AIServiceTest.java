package com.demi.java.ai.langchain4j;

import com.demi.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    /**
     * 采用创建 Assistant 的方式
     */
    @Test
    public void testQwenChatModel() {
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String chat = assistant.chat("你是谁？");
        System.out.println(chat);
    }

    /**
     * 采用注入 Assistant 的方式
     * 前提：在 Assistant 接口上添加 @AiService 注解
     */
    @Autowired
    private Assistant assistant;
    @Test
    public void testAssistant() {
        String chat = assistant.chat("你好呀");
        System.out.println(chat);
    }
}
