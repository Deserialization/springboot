package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@EnableCaching
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

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
