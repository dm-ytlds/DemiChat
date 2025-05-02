package com.demi.java.ai.langchain4j;

import com.demi.java.ai.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoDBCrudTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void testInsert() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录");
        mongoTemplate.insert(chatMessages);
    }

    @Test
    public void testFind() {
        ChatMessages messages = mongoTemplate.findById("681357b3b228ed76a99c89d6", ChatMessages.class);
        System.out.println(messages);
    }
    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id").is("681357b3b228ed76a99c89d7");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
    @Test
    public void testUpdate() {
        Criteria criteria = Criteria.where("_id").is("681357b3b228ed76a99c89d6");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "qwerty");
        // 修改或新增
        mongoTemplate.upsert(query, update, ChatMessages.class);
    }

}
