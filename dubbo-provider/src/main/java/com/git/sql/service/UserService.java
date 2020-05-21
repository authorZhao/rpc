package com.git.sql.service;

import com.git.sql.mapper2.ma.MyMapper3;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author authorZhao
 * @date 2020年05月20日
 */
@Service
public class UserService {

    @Autowired
    private MyMapper3 myMapper3;

    public Object getObject(){
        Map goodsById = myMapper3.getGoodsById(8);
        return goodsById;
    }
}
