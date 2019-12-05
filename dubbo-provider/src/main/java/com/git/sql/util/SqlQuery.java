package com.git.sql.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.git.sql.config.MyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

public class SqlQuery {

    private static final Logger logger = LoggerFactory.getLogger(SqlQuery.class);

    public static ResultSet querySql(String sql)throws ClassNotFoundException, SQLException {
        //1.导入外部的驱动jar包

        //2.加载驱动

        //Class.forName("com.mysql.cj.jdbc.Driver");

        DataSource dataSource = MySpringContext.getBean(DataSource.class);
        Connection conn = dataSource.getConnection();
        logger.info("正在执行的sql：{}",sql);
        PreparedStatement prs = (PreparedStatement) conn.prepareStatement(sql);//创建ProparedStatement 对象
        ResultSet res =prs.executeQuery();//执行sql语句并将查询结果返回
        logger.info("返回的结果：{}",res);
        while(res.next()) {//遍历查询结果
            System.out.println(res.getString(1)+"--"+res.getInt(2)+"--"+res.getInt(3));
        }
        //关闭资源
        return res;

    }
}
