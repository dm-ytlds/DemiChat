package com.demi.java.ai.langchain4j.config;

import com.demi.java.ai.langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 带有聊天记忆对话的基础上，区分不同的聊天
 */
@Configuration
public class MedicalAgentConfig {
    /**
     * 配置存储介质
     */
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;
    @Bean
    public ChatMemoryProvider medicalChatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }

    /*@Bean
    public ContentRetriever medicalContentRetriever() {
        Document document1 = FileSystemDocumentLoader.loadDocument("knowledge/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("knowledge/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("knowledge/口腔医学.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);

        // 使用内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // 使用默认的文本分割器
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        // 从向量存储中检索和查询内容相关信息
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }*/

    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    private EmbeddingStore embeddingStore;

    @Bean
    public ContentRetriever medicalContentRetriever() {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(2)
                .minScore(0.6)
                .build();
    }
}
