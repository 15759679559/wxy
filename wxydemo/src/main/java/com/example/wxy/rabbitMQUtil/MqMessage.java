package com.example.wxy.rabbitMQUtil;

/**
 * 消息队列消息对象
 * @title MqMessage
 * @author yf
 * @date 2018年2月2日
 * @since v1.0.0
 */
public class MqMessage {
    /**
     * 主机名的环境变量名
     */
    private static final String HOSTNAME_ENV_NAME = "HOSTNAME";

    /**
     * 主机名
     */
    private String hostName;

    /**
     * 转发器名称
     */
    private String exchangeName;

    /**
     * 消息
     */
    private String message;

    private MqMessage() {
        // 禁止实例化，避免生成没有主机名的消息
    }

    public static MqMessage newMessage(String exchangeName, String message) {
        MqMessage msg = new MqMessage();
        msg.setExchangeName(exchangeName);
        msg.setMessage(message);
        // 在消息上附加主机名，便于问题追踪
        String hostName = "unknown";
        try {
            hostName = System.getenv(HOSTNAME_ENV_NAME);
        } catch (Exception e) {
            // nothing to do
        }
        msg.setHostName(hostName);
        return msg;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

