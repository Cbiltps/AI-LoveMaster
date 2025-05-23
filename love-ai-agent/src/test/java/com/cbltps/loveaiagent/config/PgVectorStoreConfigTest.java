package com.cbltps.loveaiagent.config;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-19
 * Time: 18:13
 */
@SpringBootTest
class PgVectorStoreConfigTest {
    @Resource
    private PgVectorStore pgVectorStore;

    @Test
    void pgVectorStore() {
        List<Document> documents = List.of(
                new Document("做鱼香肉丝首先要肉丝腌后炒, 然后配木耳笋丝，最后调鱼香汁翻炒勾芡.", Map.of("meta1", "meta1")),
                new Document("鱼香肉丝起源源于四川民间, 用烧鱼配料炒肉丝得名."),
                new Document("我爱吃鱼", Map.of("meta2", "meta2")));
        // 添加文档
        pgVectorStore.add(documents);
        // 相似度查询
        List<Document> results = pgVectorStore.similaritySearch(SearchRequest.builder().query("怎么做鱼香肉丝?").topK(3).build());
        Assertions.assertNotNull(results);
    }
}