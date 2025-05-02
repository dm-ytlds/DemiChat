package com.demi.java.ai.langchain4j;

import com.demi.java.ai.langchain4j.assistant.Assistant;
import com.demi.java.ai.langchain4j.assistant.MessageChatAssistant;
import com.demi.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private QwenChatModel qwenChatModel;
    MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
    @Test
    public void test() {
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        String answer1 = assistant.chat("我是厍阿清");
        System.out.println(answer1);
        String answer2 = assistant.chat("我是谁？");
        System.out.println(answer2);
    }

    @Autowired
    private MessageChatAssistant assistant;

    @Test
    public void test2() {
        String chat1 = assistant.chat("我是鑫宝，我五一回老家广安去了。");
        System.out.println(chat1);
        String chat2 = assistant.chat("我是谁？我在哪？");
        System.out.println(chat2);
    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void test3() {
        int memoryId = 1;
        String ans1 = separateChatAssistant.chat(memoryId, "我是demi，我五一在成都学习 LangChain4j。");
        System.out.println(ans1);
        String ans2 = separateChatAssistant.chat(memoryId, "我是谁？我在哪？我在干什么？");
        System.out.println(ans2);
        memoryId = 2;
        String ans3 = separateChatAssistant.chat(memoryId, "我是谁？我在哪？我在干什么？");
        System.out.println(ans3);
        /*int memoryId = 1;

        // Process the first streaming request
        separateChatAssistant.chatStream(memoryId, "我是demi，我五一在成都学习 LangChain4j。", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.print(token);
            }

            public void onComplete(AiMessage response) {
                System.out.println();
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });

        // Process the second streaming request
        separateChatAssistant.chatStream(memoryId, "我是谁？我在哪？我在干什么？", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.print(token);
            }

            public void onComplete(AiMessage response) {
                System.out.println();
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });

        memoryId = 2;
        // Process the third streaming request
        separateChatAssistant.chatStream(memoryId, "我是谁？我在哪？我在干什么？", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.print(token);
            }

            public void onComplete(AiMessage response) {
                System.out.println();
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });*/
    }
}
