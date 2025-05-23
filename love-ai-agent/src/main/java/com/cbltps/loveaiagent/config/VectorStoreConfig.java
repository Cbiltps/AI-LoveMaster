package com.cbltps.loveaiagent.config;

import com.cbltps.loveaiagent.rag.LoveDocumentLoader;
import com.cbltps.loveaiagent.rag.MyKeywordEnricher;
import com.cbltps.loveaiagent.rag.MyTokenTextSplitter;
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

    @Resource
    private MyTokenTextSplitter myTokenTextSplitter;

    @Resource
    private MyKeywordEnricher myKeywordEnricher;

    @Bean
    public VectorStore loveVectorStore(@Qualifier("dashscopeEmbeddingModel") EmbeddingModel embeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        // 加载文档
        List<Document> documents = loveDocumentLoader.loadDocumentsInMd();
        // 分割文档
//        List<Document> splitDocuments = myTokenTextSplitter.splitCustomized(documents);
        // 自动补充关键词元信息
        List<Document> enrichedDocuments = myKeywordEnricher.enrichDocuments(documents);
        simpleVectorStore.add(enrichedDocuments);
        return simpleVectorStore;
    }
}
