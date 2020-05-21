package com.git.sql.mapper;

import com.git.sql.anno.MySelect;
import com.git.sql.anno.MyTestMapper;

import java.util.Map;

@MyTestMapper
public interface MyMapper {

    @MySelect("select * from crm_gb_goods where id=#{id}")
    Map getGoodsById(Integer id);
}
