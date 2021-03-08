package com.example.wxy.rabbitMQUtil;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息订阅者
 *
 * @title 消息订阅者
 * @author yf
 * @date 2018年2月2日
 * @since v1.0.0
 */
@Component
public class MessageSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);

    /**
     * 服务端配置
     */
    @Resource
    private MqConfig config;  //调用ymlvalue不能使用注解形式，要用new的

    private ConnectionFactory factory;

    private Connection connection;
    @PostConstruct
    public void init() throws IOException, TimeoutException {
        // 1.设置MQ相关的信息
        factory = new ConnectionFactory();
        MqConfig config = new MqConfig();
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
     * 订阅消息
     * @param
     * @param queueName 队列名称
     * @param handler 消息处理器
     * @throws IOException
     */
    public void subscribe(String queueName, final MessageHandler handler) throws IOException {
        // 暂时先使用一个connection多个channel的方式，后续根据量进行优化
        Channel channel = connection.createChannel();
        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                try {
                    String msg = new String(body, "UTF-8");
                    LOGGER.info("MessageSubscriber接收消息: message={}", msg);
                    MqMessage msgObj = JSON.parseObject(msg, MqMessage.class);
                    boolean handleResult = handler.handleMessage(msgObj.getMessage());
                    if (handleResult) {
                        getChannel().basicAck(envelope.getDeliveryTag(), false);
                        LOGGER.info("MessageSubscriber消费消息成功: message={}", msg);
                    } else {
                        LOGGER.info("MessageSubscriber消费消息失败: message={}", msg);
                    }
                } catch (Exception e) {
                    LOGGER.error("MessageSubscriber处理发生异常.", e);
                }
            }
        };
        // 消费消息，不使用消息自动确认机制
        channel.basicConsume(queueName, false, consumer);
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
