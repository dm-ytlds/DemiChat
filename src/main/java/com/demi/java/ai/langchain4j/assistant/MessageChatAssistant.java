package com.demi.java.ai.langchain4j.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

//@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel")
// 当配置文件中只有一个大模型配置，可以不指定大模型的名字
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory"
)
public interface MessageChatAssistant {
    /**
     * 添加用户提示词
     * {{it}}：这个占位符表示用户输入的消息。默认是 it，也可以在输入的时候使用 @V("名字") 来自定义
     **/
    /*
    @UserMessage("你是一个四川好朋友，你会用四川话回答问题。{{it}}")
    String chat(String inputMessage);
    */
    @UserMessage("你是一个四川好朋友，你会用四川话回答问题。{{inputMessage}}")
    String chat(@V("inputMessage") String inputMessage);
}
