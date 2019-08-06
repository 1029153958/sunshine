package com.zgy2.HWork86;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TranslationDemo {
    public static void main(String[] args) throws IOException {

        // 通过工具类获取连接对象
        Connection conn = DbUtil.getInstance().getConnection();

        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        String sql = "insert into student (sno,sname) values(?,?)";
        try {
            // 改为false方便后面手动提交
            conn.setAutoCommit(false);
            ps1 = conn.prepareStatement(sql);
            ps1.setString(1, "112");
            ps1.setString(2, "臧三");
            System.out.println("成功插入一个学生");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ps2 = conn.prepareStatement(sql);
            ps2.setString(1, "113");
            ps2.setString(2, "李三");
            System.out.println("成功插入一个学生");

            // 提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 执行失败，进行回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
