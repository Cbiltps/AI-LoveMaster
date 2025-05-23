package com.cbltps.loveaiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.rag.Query;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-22
 * Time: 15:19
 */
@SpringBootTest
class MyMultiQueryExpanderTest {
    @Resource
    private MyMultiQueryExpander myMultiQueryExpander;

    @Test
    void expand() {
        List<Query> queries = myMultiQueryExpander.expand("啥是秦始皇啊啊啊啊啊啊？！请回答我哈哈哈哈");
        Assertions.assertNotNull(queries);
    }
}