package com.example.demo.config;

import com.example.demo.bean.Car;
import com.example.demo.bean.Pig;
import com.example.demo.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 18070967
 * @create 2021/1/9
 */
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnBean(name = "pig1")
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
public class MyConfig {
    @Bean
    public User user1(){
        User user = new User("zs",12);
        user.setPig(pig1());
        System.out.println(user.toString());
        return user;
    }

    @Bean("pig11")
    public Pig pig1(){
        return new Pig("zs");
    }
}
