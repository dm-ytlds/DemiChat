package com.demi.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * 智慧医疗智能体
 */
//@AiService(
//        wiringMode = AiServiceWiringMode.EXPLICIT,
//        chatModel = "qwenChatModel",
//        chatMemoryProvider = "medicalChatMemoryProvider",
//        tools = "appointmentTools",
//        contentRetriever = "medicalContentRetriever"
//)

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "medicalChatMemoryProvider",
        tools = "appointmentTools",
        contentRetriever = "medicalContentRetriever"
)
public interface MedicalAgent {
    @SystemMessage(fromResource = "medical-agent-prompt.txt")
//    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
