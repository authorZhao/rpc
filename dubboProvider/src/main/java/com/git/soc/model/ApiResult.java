package com.git.soc.model;

public class ApiResult {

    private Integer code;
    private String msg;
    private Object object;

    public ApiResult(Integer code,String msg,Object obj){
        super();
        this.object=obj;
    }

    public ApiResult(Integer code,String msg){
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
}
