package com.demi.java.ai.langchain4j;


import com.demi.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PromptTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testPrompt() {
        int memoryId = 3;
        String ans2 = separateChatAssistant.chat(memoryId, "我是谁？我在哪？我在干什么？今天几号了");
        System.out.println(ans2);
    }

    @Test
    public void testV() {
        int memoryId = 4;
        String ans2 = separateChatAssistant.chat2(memoryId, "我是谁？我在哪？我在干什么？今天几号了");
        System.out.println(ans2);
    }

    @Test
    public void testV2() {
        // 用户信息
        String username = "Demi";
        int age = 30;

        int memoryId = 5;
        String ans2 = separateChatAssistant.chat3(memoryId, "我是男人", username, age);
        System.out.println(ans2);
    }
}
