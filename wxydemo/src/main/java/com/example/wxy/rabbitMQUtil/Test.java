package com.example.wxy.rabbitMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Test {
    public static void main(String[] args) {
        MessageSubscriber messageSubscriber = new MessageSubscriber();
        try {
            //初始化
            messageSubscriber.init();
            messageSubscriber.subscribe("队列01", new MessageHandler() {
                @Override
                public boolean handleMessage(String message) {
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
