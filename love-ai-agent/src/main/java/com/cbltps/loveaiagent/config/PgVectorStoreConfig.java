package com.cbltps.loveaiagent.config;

import com.cbltps.loveaiagent.rag.LoveDocumentLoader;
import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.springframework.ai.vectorstore.pgvector.PgVectorStore.PgDistanceType.COSINE_DISTANCE;
import static org.springframework.ai.vectorstore.pgvector.PgVectorStore.PgIndexType.HNSW;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-19
 * Time: 18:03
 */
public class PgVectorStoreConfig {
    @Resource
    private LoveDocumentLoader loveDocumentLoader;

    @Bean
    public VectorStore pgVectorStore(JdbcTemplate jdbcTemplate, @Qualifier("dashscopeEmbeddingModel") EmbeddingModel embeddingModel) {
        VectorStore vectorStore = PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .dimensions(1536)                    // Optional: defaults to model dimensions or 1536
                .distanceType(COSINE_DISTANCE)       // Optional: defaults to COSINE_DISTANCE
                .indexType(HNSW)                     // Optional: defaults to HNSW
                .initializeSchema(true)              // Optional: defaults to false
                .schemaName("public")                // Optional: defaults to "public"
                .vectorTableName("vector_store")     // Optional: defaults to "vector_store"
                .maxDocumentBatchSize(10000)         // Optional: defaults to 10000
                .build();
        // 加载文档
        List<Document> documents = loveDocumentLoader.loadDocumentsInMd();
        vectorStore.add(documents);
        return vectorStore;
    }
}
