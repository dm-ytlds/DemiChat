package com.demi.java.ai.langchain4j;

import com.demi.java.ai.langchain4j.assistant.MessageChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserPromptTest {
    @Autowired
    private MessageChatAssistant messageChatAssistant;
    @Test
    public void testPrompt() {
        String ans1 = messageChatAssistant.chat("我是demi，我五一在成都学习 LangChain4j。");
        System.out.println(ans1);
        String ans2 = messageChatAssistant.chat("学习 LangChain4j 有些枯燥呀。");
        System.out.println(ans2);
        String ans3 = messageChatAssistant.chat("我在干嘛。");
        System.out.println(ans3);
    }
}
