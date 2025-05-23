package com.cbltps.loveaiagent.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 查询扩展器
 * User: lichengxiang
 * Date: 2025-05-22
 * Time: 10:39
 */
@Component
public class MyMultiQueryExpander {
    private final ChatClient.Builder chatClientBuilder;

    public MyMultiQueryExpander(@Qualifier("dashscopeChatModel") ChatModel chatModel) {
        this.chatClientBuilder = ChatClient.builder(chatModel);
    }

    public List<Query> expand(String query) {
        MultiQueryExpander queryExpander = MultiQueryExpander.builder()
                .chatClientBuilder(chatClientBuilder)
                .numberOfQueries(3)
                .build();
        List<Query> queries = queryExpander.expand(new Query("谁是秦始皇啊？"));
        return queries;
    }
}
