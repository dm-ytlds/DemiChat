package com.demi.java.ai.langchain4j.bean;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 封装一个对话 data
 */
@Data
public class ChatForm {
    private Long memoryId;
    private String message;
}
