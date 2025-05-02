package com.demi.java.ai.langchain4j.assistant;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "calculatorTools"  // 调用自定义工具
)
public interface SeparateChatAssistant {
    /**
     * 注意：增加{{current_date}}参数，可以让大模型回答出当前的日期
     * 注意：增加@MemoryId int memoryId参数，可以让大模型记住之前的对话
     * **/
    // @SystemMessage("你是一个四川人，你会用四川话回答问题。今天是{{current_date}}")
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(@MemoryId int memoryId, @UserMessage String inputMessage);

    /**
     * 多个参数，必须使用 @V 注解指定参数名
     */
    @SystemMessage(fromResource = "system-prompt.txt")
    @UserMessage("作为一个四川好朋友，请用四川话回答问题。{{inputMessage}}")
    String chat2(@MemoryId int memoryId, @V("inputMessage") String inputMessage);

    /**
     * @V() 注解还可以作为变量占位符
     */
    @SystemMessage(fromResource = "system-prompt-2.txt")
    @UserMessage("作为一个四川好朋友，请用四川话回答问题。{{inputMessage}}")
    String chat3(
            @MemoryId int memoryId,
            @V("inputMessage") String inputMessage,
            @V("username") String username,
            @V("age") int age
    );
}
