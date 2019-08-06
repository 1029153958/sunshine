package com.zgy2.HWork86;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 单例模式
    private static final DbUtil DB_UTILl = new DbUtil();

    private DbUtil(){}

    public static DbUtil getInstance(){
        return DB_UTILl;
    }

    public static void closeResource(Connection conn) {
    }

    // 获取连接对象
    public Connection getConnection() throws IOException {
        // 创建流
        FileInputStream fis = new FileInputStream("src/com/zgy2/HWork86/jdbc.properties");
        // 创建对象
        Properties pro = new Properties();
        // 加载流
        pro.load(fis);
        driver = pro.getProperty("driver");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");
        //加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关流方法
    public static void closeResourse(AutoCloseable autoClo){
        if(autoClo != null){
            try {
                autoClo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}