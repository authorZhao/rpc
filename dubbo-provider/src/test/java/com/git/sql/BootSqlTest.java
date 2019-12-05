package com.git.sql;

import com.git.sql.mapper.MyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SqlApplication.class)
public class BootSqlTest {

    @Autowired
    private MyMapper myMapper;

    @Test
    public void test1(){
        myMapper.getGoodsById(8);
    }

}
