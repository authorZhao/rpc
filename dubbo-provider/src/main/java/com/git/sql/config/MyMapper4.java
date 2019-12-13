package com.git.sql.config;

import com.git.sql.anno.MySelect;

import java.util.Map;

public interface MyMapper4 {

    @MySelect("select * from crm_gb_goods where id=#{id}")
    Map getGoodsById(Integer id);
}
