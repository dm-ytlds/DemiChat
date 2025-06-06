package com.demi.java.ai.langchain4j.config;

import com.demi.java.ai.langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 带有聊天记忆对话的基础上，区分不同的聊天
 */
@Configuration
public class SeparateChatAssistantConfig {
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
}
