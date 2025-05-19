package com.cbltps.loveaiagent.config;

import com.cbltps.loveaiagent.rag.LoveDocumentLoader;
import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-08
 * Time: 15:03
 */
@Configuration
public class VectorStoreConfig {
    @Resource
    private LoveDocumentLoader loveDocumentLoader;

    @Bean
    public VectorStore vectorStore(@Qualifier("dashscopeEmbeddingModel") EmbeddingModel embeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        List<Document> documents = loveDocumentLoader.loadDocumentsInMd();
        simpleVectorStore.add(documents);
        return simpleVectorStore;
    }
}
