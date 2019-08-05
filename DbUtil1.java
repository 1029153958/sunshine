package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtil1 {
//    jdbc:mysql://locahost:3306/user82(连接的数据库名)

    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
//        加载驱动程序
//        将Driver类加载到内存
        Class.forName("com.mysql.jdbc.Driver");
//        获取数据库连接
//        返回连接对象
//        url:数据库连接地址
//        连接数据库的密码user 密码password
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
//        操作数据库，进行业务操作
//        System.out.println(conn);//不为null就可以正常连接
//        Statement stmt = conn.createStatement();
//        sql语句必须通过conn发送到mysql
        Statement statement = conn.createStatement();
//        ResultSet resultSet = stmt.executeQuery();//
        String sql = "select * from student";
//        执行查询后返回结果集对象，用完关闭
        ResultSet rs = statement.executeQuery(sql);
//        System.out.println(rs);
//        遍历结果集
        while(rs.next()){
            String sno = rs.getString(1);
            String sname = rs.getString(2);
            String ssex = rs.getString(3);
            String sbirthday = rs.getString(4);
            String clz = rs.getString(5);
            System.out.println(sno+"\t"+sname+"\t"+ssex+"\t"+sbirthday+"\t"+clz);
        }


//        用完关闭 后打开先关闭
        rs.close();
        statement.close();
        conn.close();

    }
}
