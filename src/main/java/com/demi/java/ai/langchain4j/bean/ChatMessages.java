package com.demi.java.ai.langchain4j.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_messages")
public class ChatMessages {
    /**
     * 映射到 MongoDB 文档的 _id 字段
     */
    @Id
    private ObjectId messageId;

    /**
     * 对话隔离 ID
     */
    private String memoryId;
    /**
     * 存储当前聊天记录列表的 json 字符串
     */
    private String content;
}
