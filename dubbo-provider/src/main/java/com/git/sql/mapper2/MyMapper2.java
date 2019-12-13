package com.git.sql.mapper2;

import com.git.sql.anno.MySelect;

import java.util.Map;

public interface MyMapper2 {

    @MySelect("select * from crm_gb_goods where id=#{id}")
    Map getGoodsById(Integer id);
}
