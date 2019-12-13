package com.git.bean;

import com.git.config.condition.MyLogAop;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author authorZhao
 * @date 2019/12/12
 */
@Service
@Data
public class Person {

    private String name;

    private String sex;

    private Integer age;

    @MyLogAop
    public void sayHello(){
        System.out.println("我系"+this.getName()+"\n"+"年龄"+this.getAge()+"岁");
    }


    public static void main(String[] args) {
        Person person = new Person();
        person.setName("渣渣辉");

        person.sayHello();
    }
}
