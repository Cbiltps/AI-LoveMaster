package com.cbltps.loveaiagent.agent.model;

/**
 * Created with IntelliJ IDEA.
 * Description: 代理执行状态的枚举类
 * User: lichengxiang
 * Date: 2025-06-07
 * Time: 16:58
 */
public enum AgentState {
    /**
     * 空闲状态
     */
    IDLE,

    /**
     * 运行中状态
     */
    RUNNING,

    /**
     * 已完成状态
     */
    FINISHED,

    /**
     * 错误状态
     */
    ERROR
}
