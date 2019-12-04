package com.git.sql;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;

public class Sql {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from crm_gb_goods";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/utopa_dev_crm?characterEncoding=UTF8&allowMultiQueries=true");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            conn = dataSource.getConnection();
            //conn = DriverManager.getConnection("url","root","root");

            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {//遍历查询结果
                System.out.println(resultSet.getString(1)+"--"+resultSet.getInt(2)+"--"+resultSet.getInt(3));
            }
            System.out.println(resultSet);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void sql() throws ClassNotFoundException, SQLException {
        //1.导入外部的驱动jar包

        //2.加载驱动

        Class.forName("com.mysql.jdbc.Driver");

        //3.建立连接
        String user = "root";
        String password = "123456";
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/linan", user, password);
        //这里的三个参数分别为MySQL资源的地址，用户名，密码

        //执行sql语句
        String sql = "select * from linan";//这里写入的是sql语句   查询linan表中所有数据
        PreparedStatement prs = (PreparedStatement) conn.prepareStatement(sql);//创建ProparedStatement 对象
        ResultSet res =prs.executeQuery();//执行sql语句并将查询结果返回


        while(res.next()) {//遍历查询结果
            System.out.println(res.getString(1)+"--"+res.getInt(2)+"--"+res.getInt(3));
        }
        //关闭资源
        res.close();
        conn.close();
        prs.close();


    }



}
