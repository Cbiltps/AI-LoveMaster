package com.cbltps.loveaiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-08
 * Time: 10:54
 */
@SpringBootTest
class LoveDocumentLoaderTest {

    @Resource
    private LoveDocumentLoader loveDocumentLoader;

    @Test
    void loadDocumentsInMd() {
        loveDocumentLoader.loadDocumentsInMd();
    }
}