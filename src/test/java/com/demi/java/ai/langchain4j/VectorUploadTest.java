package com.demi.java.ai.langchain4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class VectorUploadTest {

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingStore embeddingStore;

    @Test
    public void testVectorUpload() {
        Document document1 = FileSystemDocumentLoader.loadDocument("knowledge/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("knowledge/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("knowledge/口腔医学.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);

        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);
    }
}
