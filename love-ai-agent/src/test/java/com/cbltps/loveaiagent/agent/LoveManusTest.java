package com.cbltps.loveaiagent.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-06-08
 * Time: 02:07
 */
@SpringBootTest
class LoveManusTest {

    @Resource
    private LoveManus loveManus;

    @Test
    public void run() {
        String userPrompt = """
                我的另一半居住在西宁市城西区, 请帮我找到 5 公里内合适的约会地点, 
                并结合一些网络图片, 制定一份详细的约会计划, 
                并以 PDF 格式输出""";
        String answer = loveManus.run(userPrompt);
        Assertions.assertNotNull(answer);
    }
}
