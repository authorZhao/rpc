package com.git.sql;

import com.git.config.beanname.MyBeanName;
import com.git.config.condition.MyMapperScan;
import com.git.mq.config.ActivemqConfig;
import com.git.sql.config.MyMapper4;
import com.git.sql.config.SqlConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@SpringBootApplication
//@Import({SqlConfig.class})
@MyMapperScan(basePackages = {"com.git.sql.mapper","com.git.sql.mapper2"},classes = MyMapper4.class)
public class SqlApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SqlApplication.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("启动完毕");

    }

}
