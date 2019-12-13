package com.git.config.aop;

/**
 * @author authorZhao
 * @date 2019/12/12
 */
public class Person2 {

    private String name;

    private String sex;

    private Integer age;

    //@MyLogAop
    public void sayHello(){
        System.out.println("我系"+this.getName()+"\n"+"年龄"+this.getAge()+"岁");
    }


    public static void main(String[] args) {
        Person2 person = new Person2();
        person.setName("渣渣辉");

        person.sayHello();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
