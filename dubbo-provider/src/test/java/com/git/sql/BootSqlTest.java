package com.git.sql;

import com.git.sql.mapper.MyMapper;
import com.git.sql.service.UserService;
import com.git.sql.util.ProxyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;

import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest(classes = SqlApplication.class)
public class BootSqlTest {

    @Autowired
    private MyMapper myMapper;

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        String proxyName = "MyMapperImpl";


        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, myMapper.getClass().getInterfaces());
        String path = "D:\\idea\\workspace2\\test\\rpc\\dubbo-provider\\src\\test\\java\\com\\git\\sql\\";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + proxyName + ".class");
            fos.write(classFile);//保存到磁盘
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myMapper.getGoodsById(8);
        }

    }

    @Test
    public void test2() {

        userService.getObject();
    }
}
