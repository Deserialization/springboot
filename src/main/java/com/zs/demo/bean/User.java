package com.zs.demo.bean;

/**
 * @author 18070967
 * @create 2021/1/9
 */
public class User {
    public String name;
    private Integer age;

    private Pig pig;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Pig getPig() {
        return pig;
    }

    public void setPig(Pig pig) {
        this.pig = pig;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pig=" + pig +
                '}';
    }
}
