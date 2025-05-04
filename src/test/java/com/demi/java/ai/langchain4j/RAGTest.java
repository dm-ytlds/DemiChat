package com.demi.java.ai.langchain4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

@SpringBootTest
public class RAGTest {

    @Test
    public void testDocumentLoader() {
        /*Document loader = FileSystemDocumentLoader.loadDocument("src/main/resources/medical-agent-prompt.txt", new TextDocumentParser());
        System.out.println(loader.text());*/
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources", pathMatcher, new TextDocumentParser());
        documents.forEach(System.out::println);
    }

    @Test
    public void testDocumentParser() {
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/四川省巴中市2025年上半年引进高层次人才和急需紧缺专业人才企业岗位一览表.pdf", new ApachePdfBoxDocumentParser());
        System.out.println(document);
    }

    @Test
    public void testRAG() {
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/四川省巴中市2025年上半年引进高层次人才和急需紧缺专业人才企业岗位一览表.pdf", new ApachePdfBoxDocumentParser());
        InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();

        /*
        * ingest 方法包括：
        * （1）文档分割；
        * （2）文本向量化；
        * （3）向向量数据库中插入数据；
        * */
        EmbeddingStoreIngestor.ingest(document, inMemoryEmbeddingStore);
        System.out.println(inMemoryEmbeddingStore);
    }

    /**
     * 测试文档分割器
     */
    @Test
    public void testDocumentSplitter() {
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/medical-agent-prompt.txt");
        InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();

        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(300, 30, new HuggingFaceTokenizer());

        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(inMemoryEmbeddingStore)
                .documentSplitter(documentByParagraphSplitter)
                .build()
                .ingest(document);
        System.out.println(inMemoryEmbeddingStore);
    }
}
