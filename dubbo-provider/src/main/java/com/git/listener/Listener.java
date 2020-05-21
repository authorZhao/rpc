package com.git.listener;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.DriverSpy;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.net.URL;
import java.sql.*;

/**
 * @author authorZhao
 * @date 2019/12/19
 */
@Slf4j
@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        log.info("进行数据初始化");

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String sql = "show databases";
        String databases = "SELECT information_schema.SCHEMATA.SCHEMA_NAME FROM information_schema.SCHEMATA where SCHEMA_NAME=\"no_name\"";
        String createDataBase = "create database no_name default character set utf8mb4;\n" +
                "use no_name;";
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sql/no_name.sql");

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(resourceAsStream, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while (true) {
            try {
                if (!((line = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.append(line);
        }
        line = buffer.toString();

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("net.sf.log4jdbc.DriverSpy");
            /*DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/open_md?characterEncoding=UTF8&allowMultiQueries=true");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            conn = dataSource.getConnection();*/
            //conn = DriverManager.getConnection("url","root","root");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/open_md?characterEncoding=UTF8&allowMultiQueries=true", "root", "root");
            //这里的三个参数分别为MySQL资源的地址，用户名，密码

            //执行sql语句

            preparedStatement = conn.prepareStatement(databases);

            //1.检查数据库存在否
            ResultSet resultSet1 = preparedStatement.executeQuery(databases);
            if(resultSet1.next()){
                log.info("当前系统已存在数据库:{}","no_name");
            }else{
                log.warn("当前系统没有数据库，正准备初始化数据");
                boolean execute = preparedStatement.execute(createDataBase);
                if(!execute){
                    log.info("数据库:no_name创建完毕，开始导入sql");
                    boolean execute1 = preparedStatement.execute(line);
                    if(!execute1){
                        log.info("sql导入成功");
                    }else{
                        log.info("sql导入失败");
                    }

                }else{
                    log.error("数据库:no_name创建失败");
                    throw new RuntimeException();
                }


            }


            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()) {//遍历查询结果
                System.out.println(resultSet.getString(1));
                //System.out.println(resultSet.getString(1)+"--"+resultSet.getInt(2)+"--"+resultSet.getInt(3));
            }
            System.out.println(resultSet);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
