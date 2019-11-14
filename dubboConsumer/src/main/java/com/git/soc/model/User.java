package com.git.soc.model;


import java.io.Serializable;
import java.util.List;

public class User implements Serializable {


    private static final long serialVersionUID = 3570930855800498567L;
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户角色集合
     */
    private List<Role> roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
