package com.cbltps.loveaiagent.advisor.advisorfactory;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;

/**
 * Created with IntelliJ IDEA.
 * Description: 创建上下文查询增强器的工厂
 * User: lichengxiang
 * Date: 2025-05-23
 * Time: 11:32
 */
public class ContextualQueryAugmenterFactory {
    public static ContextualQueryAugmenter createInstance() {
        PromptTemplate emptyContextPromptTemplate = new PromptTemplate("""
                你应该输出下面的内容：
                抱歉，我只能回答恋爱相关的问题，别的没办法帮到您哦，
                有问题可以联系程序员大祥子 https://blog.csdn.net/Cbiltps
                """);
        return ContextualQueryAugmenter.builder()
                /**
                 * 参数为 false 时, 如果检索不到文档, 就会修改prompt,
                 * 如果为 true 时, 如果检索不到文档, 就会直接询问大模型
                 */
                .allowEmptyContext(false)
                .emptyContextPromptTemplate(emptyContextPromptTemplate)
                .build();
    }
}
