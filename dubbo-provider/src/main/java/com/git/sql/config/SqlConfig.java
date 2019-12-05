package com.git.sql.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.git.sql.anno.MyMapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MyMapperScan({"com.git.sql.config.MapperDefinitionRegistry",
        "com.git.sql.config.MyBatisBeanPostProcessor"
,"com.git.sql.util.MySpringContext"})
@MyMapperScan
public class SqlConfig {

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/utopa_dev_crm?characterEncoding=UTF8&allowMultiQueries=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

}
