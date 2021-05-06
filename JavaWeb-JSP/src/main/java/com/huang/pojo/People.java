package com.huang.pojo;

/**
 * @author huanghudong
 * @ClassName People.java
 * @Description TODO
 * @createTime 2021年05月06日 23:54:00
 */
public class People {

    private String name;
    private String addr;
    private String age;

    public People() {
    }

    public People(String name, String addr, String age) {
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
