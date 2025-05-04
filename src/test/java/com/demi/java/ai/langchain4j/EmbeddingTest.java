package com.demi.java.ai.langchain4j;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmbeddingTest {

    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void test() {
        String text = "Hello, world!";
        Response<Embedding> embedded = embeddingModel.embed(text);
        System.out.println(embedded.content().vector().length);
        System.out.println(embedded.toString());
    }

    @Autowired
    private EmbeddingStore embeddingStore;
    @Test
    public void testStore() {
        TextSegment textSegment = TextSegment.from("Hello, world!");
        Embedding embedded = embeddingModel.embed(textSegment).content();
        embeddingStore.add(embedded, textSegment);
    }

    @Test
    public void testSearch() {
        TextSegment textSegment = TextSegment.from("Hello");
        Embedding embedded = embeddingModel.embed(textSegment).content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(embedded)
                .maxResults(1)
                .minScore(0.6)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);
        System.out.println(embeddingMatch.score());
        System.out.println(embeddingMatch.embedded().text());
    }
}
