package com.demi.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@SpringBootTest
public class LLMTest {

    @Test
    public void testLLM() {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();
        String chat = model.chat("你是谁？");
        System.out.println(chat);
    }


    @Autowired
    private OpenAiChatModel model;
    @Test
    public void testSpringBoot() {
        String chat = model.chat("你是谁？");
        System.out.println(chat);
    }

    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testQwenChatModel() {
        String chat = qwenChatModel.chat("你是谁？");
        System.out.println(chat);
    }

    @Test
    public void testWanxChatModel() {
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .modelName("wanx2.1-t2i-plus")
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .build();
        Response<Image> response = wanxImageModel.generate("性感美女");
        System.out.println(response.content().url());
    }
}
