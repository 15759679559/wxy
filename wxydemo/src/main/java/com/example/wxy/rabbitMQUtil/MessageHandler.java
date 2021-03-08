package com.example.wxy.rabbitMQUtil;


/**
 * 消息处理器接口
 * 通过实现该接口来定制自己的消息处理逻辑
 * @title 消息处理器接口
 * @author yf
 * @date 2018年2月2日
 * @since v1.0.0
 */
public interface MessageHandler {
    /**
     * 处理消息
     * @param message 消息
     * @return true-如果消息最终被消费掉,该消息会从队列中移除 false-如果消息没有被消费，该消息会保持在队列中
     */
    public boolean handleMessage(String message);
}
