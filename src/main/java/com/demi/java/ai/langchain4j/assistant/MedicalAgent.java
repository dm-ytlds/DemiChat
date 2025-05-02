package com.demi.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * 智慧医疗智能体
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "medicalChatMemoryProvider"
)
public interface MedicalAgent {
    @SystemMessage(fromResource = "medical-agent-prompt.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
