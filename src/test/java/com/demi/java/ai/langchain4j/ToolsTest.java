package com.demi.java.ai.langchain4j;


import com.demi.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ToolsTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testPrompt() {
        int memoryId = 1;
        String ans = separateChatAssistant.chat(memoryId, "234567846764574567的平方根是多少");
        System.out.println(ans);
    }
}
