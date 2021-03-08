package com.example.wxy.rabbitMQUtil;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发布者
 * @title MessagePublisher
 * @author yf
 * @date 2018年2月2日
 * @since v1.0.0
 */
@Component
public class MessagePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);

    /**
     * 服务端配置
     */
    @Resource
    private MqConfig config;

    private ConnectionFactory factory;

    private Connection connection;

    /**
     * 初始化方法
     *
     * @throws IOException
     * @throws TimeoutException
     */
    @PostConstruct
    public void init() throws IOException, TimeoutException {
        // 1.设置MQ相关的信息
        factory = new ConnectionFactory();
        factory.setHost(config.getHost());
        factory.setPort(config.getPort());
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        factory.setVirtualHost(config.getVirtualHost());
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(60000L);
        // 2.创建一个新的连接
        connection = factory.newConnection();
    }

    /**
     * 向消息队列中发布一条消息
     *
     * @param exchangeName
     *            转发器名称
     * @param message
     *            消息
     * @return
     */
    public boolean publish(String exchangeName, String message) {  //exchangeName是交换器，message是对应的消息
        try {
            //创建一个通道
            Channel channel = connection.createChannel();
            MqMessage msgObj = MqMessage.newMessage(exchangeName, message);
            String msg = JSON.toJSONString(msgObj);
            channel.basicPublish(msgObj.getExchangeName(), "", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("UTF-8"));
            channel.close();
            LOGGER.info("MessagePublisher消息推送成功: message={}", msg);
            return true;
        } catch (Exception e) {
            LOGGER.error("MessagePublisher消息推送异常, message={}", message, e);
        }
        return false;
    }

    /**
     * 设置MQ连接相关的信息
     *
     * @param config
     */
    public void setConfig(MqConfig config) {
        this.config = config;
    }
}

