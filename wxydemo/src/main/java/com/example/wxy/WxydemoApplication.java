package com.example.wxy;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.example.wxy.entity.User;
import com.example.wxy.entity.Wxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
@EnableDistributedTransaction //开启注册分布式事务中心
@EnableAsync //开启多线程
public class WxydemoApplication {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        System.out.println(context.getId());
        System.out.println(context.getBean(User.class));
//        System.out.println(context.getBean(Wxy.class));

    }

}
