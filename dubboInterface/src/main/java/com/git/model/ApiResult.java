package com.git.model;

import java.io.Serializable;
import java.util.Map;

public class ApiResult implements Serializable {

    private static final long serialVersionUID = -2328451803818677569L;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回实体
     */
    private Object object;

    /**
     * 用来接收参数
     */
    private Map<String,Object> map;

    public ApiResult(){
    }


    public ApiResult(Integer code, String msg, Object obj){
        super();
        this.object=obj;
    }

    public ApiResult(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", object=" + object +
                ", map=" + map +
                '}';
    }
}
