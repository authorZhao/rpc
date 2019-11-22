package com.git.model;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = -2328451803818677889L;

    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String desc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
