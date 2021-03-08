package com.example.wxy;

import com.example.wxy.entity.Wxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        beanFactory.createBean(Wxy.class);
        AbstractBeanDefinition abd = (AbstractBeanDefinition)beanFactory.getBeanDefinition( "User");
//        abd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
//        System.out.println(abd.getAutowireMode());
        abd.setBeanClass(Wxy.class);
        System.out.println("11");

    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Name:");
//        String name = sc.next();
//        System.out.println(name);
//    }

}
