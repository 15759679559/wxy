package com.example.wxy.rabbitMQUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 消息队列配置
 * @title MqConfig
 * @author yf
 * @date 2018年2月2日
 * @since v1.0.0
 */
@Component
public class MqConfig {
    /**
     * 服务器域名或IP
     */
    @Value("${spring.rabbitmq.host}")
    private String host;

    /**
     * 端口，默认5672
     */
    @Value("${spring.rabbitmq.port}")
    private int port;

    /**
     * 用户名，默认guest
     */
    @Value("${spring.rabbitmq.username}")
    private String username;

    /**
     * 用户密码，默认guest
     */
    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * 虚拟目录，默认/
     */
    private String virtualHost = "/";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

}

