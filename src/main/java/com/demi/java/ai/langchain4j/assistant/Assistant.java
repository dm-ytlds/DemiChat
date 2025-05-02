package com.demi.java.ai.langchain4j.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel")
// 当配置文件中只有一个大模型配置，可以不指定大模型的名字
//@AiService
public interface Assistant {
    String chat(String inputMessage);
}
