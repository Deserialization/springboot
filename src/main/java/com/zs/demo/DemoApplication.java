package com.zs.demo;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/***
 *  * 自动配置
 *  *  1、RabbitAutoConfiguration
 *  *  2、有自动配置了连接工厂ConnectionFactory；
 *  *  3、RabbitProperties 封装了 RabbitMQ的配置
 *  *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
 *  *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件;
 *  *  	AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *  *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
 */

@EnableRabbit
@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

    private static void m2(String[] args) {
        ConfigurableApplicationContext cx = SpringApplication.run(DemoApplication.class, args);
        String[] names = cx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        //m1();

        boolean user1 = cx.containsBean("user1");

        System.out.println(user1);
        boolean pig1 = cx.containsBean("pig1");
        System.out.println(pig1);

        boolean pig11 = cx.containsBean("pig11");
        System.out.println(pig11);

        boolean user01 = cx.containsBean("user01");
        System.out.println(user01);
        boolean user02 = cx.containsBean("user02");
        System.out.println(user02);

        int i = cx.getBeanDefinitionCount();
        System.out.println(i);
    }

    private static void m1() {
    /*Pig p = cx.getBean("pig1",Pig.class);
    Pig p2 = cx.getBean("pig1",Pig.class);
    System.out.println(p == p2);

    MyConfig m  = cx.getBean(MyConfig.class);
    System.out.println(m);

    User ca = m.user1();
    User ca1 = m.user1();
    System.out.println(ca == ca1);

    User u1 = cx.getBean("user1",User.class);
    Pig p1 = cx.getBean("pig1",Pig.class);
    System.out.println(u1.getPig() == p1);*/
    }

}
