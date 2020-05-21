package com.git.sql;

import com.git.config.beanname.MyBeanName;
import com.git.config.condition.MyMapperScan;
import com.git.listener.CheckSqlListener;
import com.git.listener.Listener;
import com.git.mq.config.ActivemqConfig;
import com.git.sql.config.MyMapper4;
import com.git.sql.config.SqlConfig;
import com.git.sql.mapper.MyMapper;
import com.git.sql.mapper2.MyMapper2;
import com.git.sql.service.UserService;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@SpringBootApplication
@Import({CheckSqlListener.class, UserService.class})
@MyMapperScan(basePackages = {"com.git.sql.mapper","com.git.sql.mapper2"},
        classes = {MyMapper4.class})
@ServletComponentScan(basePackageClasses = Listener.class)
public class SqlApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SqlApplication.class, args);
        //Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
        //((MyMapper)run.getBean("myMapper")).getGoodsById(2);
        //System.out.println("启动完毕");

    }

}
