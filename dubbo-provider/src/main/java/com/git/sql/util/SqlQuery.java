package com.git.sql.util;

import javax.sql.DataSource;
import java.sql.*;

public class SqlQuery {
    public static ResultSet querySql(String sql)throws ClassNotFoundException, SQLException {
        //1.导入外部的驱动jar包

        //2.加载驱动

        Class.forName("com.mysql.jdbc.Driver");

        DataSource dataSource = MySpringContext.getBean(DataSource.class);

        Connection conn = dataSource.getConnection();
        PreparedStatement prs = (PreparedStatement) conn.prepareStatement(sql);//创建ProparedStatement 对象
        ResultSet res =prs.executeQuery();//执行sql语句并将查询结果返回

        while(res.next()) {//遍历查询结果
            System.out.println(res.getString(1)+"--"+res.getInt(2)+"--"+res.getInt(3));
        }
        //关闭资源
        res.close();
        conn.close();
        prs.close();

        return res;

    }
}
