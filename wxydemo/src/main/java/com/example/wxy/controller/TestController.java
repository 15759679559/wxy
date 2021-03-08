package com.example.wxy.controller;
import com.example.wxy.rabbitMQUtil.MessageHandler;
import com.example.wxy.rabbitMQUtil.MessagePublisher;
import com.example.wxy.rabbitMQUtil.MessageSubscriber;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wxy
 * @date 2021年3月8日09:45:09
 */
@RestController
@RequestMapping("/test")
public class TestController {


    //测试备注
    @RequestMapping("messageSubscriber")
    public String messageSubscriber() throws Exception {
        MessageSubscriber messageSubscriber = new MessageSubscriber();
        //初始化
        messageSubscriber.init();
        messageSubscriber.subscribe("队列01", new MessageHandler() {
            @Override
            public boolean handleMessage(String message) {
                return true;
            }
        });
        return "success";
    }

    @RequestMapping("messagePublisher")
    public String test() throws Exception {
        MessagePublisher messagePublisher = new MessagePublisher();
        //初始化
        messagePublisher.init();
        messagePublisher.publish("队列01","内容");
        return "success";
    }
}
