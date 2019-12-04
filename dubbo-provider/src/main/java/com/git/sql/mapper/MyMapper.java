package com.git.sql.mapper;

import com.git.sql.anno.MySelect;

import java.util.Map;

public interface MyMapper {

    @MySelect("select * from crm_gb_goods where id=#{id}")
    Map getGoodsById(Integer id);
}
