package com.cbltps.loveaiagent.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-08
 * Time: 10:09
 */
@Slf4j
@Component
public class LoveDocumentLoader {

    private final ResourcePatternResolver resourcePatternResolver;

    public LoveDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    /**
     * 加载所有Md文档
     * @return
     */
    public List<Document> loadDocumentsInMd() {
        List<Document> allDocuments = new ArrayList<>();
        // 加载多篇Md文档
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath:document/*.md");
            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                // 提取文档倒数第 3 和第 2 个字作为标签
                String status = fileName.substring(fileName.length() - 6, fileName.length() - 4);
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .withAdditionalMetadata("fileName", fileName)
                        .withAdditionalMetadata("status", status)
                        .build();
                MarkdownDocumentReader markdownDocumentReader = new MarkdownDocumentReader(resource, config);
                allDocuments.addAll(markdownDocumentReader.get());
            }
        } catch (IOException e) {
            log.error("Failed to load documents from markdown files", e);
        }
        return allDocuments;
    }
}
