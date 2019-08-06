package prestate;

import com.sun.prism.PresentableState;
import org.junit.Test;
import teacherconn.Hero;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class JdbcUtil {

    public static void main(String[] args) {

        try {
           // findByHeroNameLike("小");
           // findOrderByUserName();
           // findByHeroNameLike("杨");
          //  findOrderByUserName();
           findByUserNameLikeOrderLimit("杨",2, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SRC -> ClassPath

    public static Connection getConnection() throws IOException {
        //  操作Properties文件的工具类
        Properties properties = new Properties();
        // 获取项目文件路径：ClassPath（编译后路径）
        String filePath = JdbcUtil.class.getResource("/").getFile().toString() + "jdbc.properties";
        // 输入流
        InputStream is = new FileInputStream(filePath);
        // 加载输入流
        properties.load(is);
        try {
            // 获取driver信息，返回Object对象，需要toString转化为String
            Class.forName(properties.get("driver").toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.get("url").toString(), properties.get("username").toString(), properties.get("passwd").toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }




    public static void insertHero(Hero hero) throws IOException {
        Connection connection = getConnection();
        // sql语句中未知的值，使用？代替，？占位符,在后面为其设置值
        String sql = "insert into hero values(null,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 为占位符依次设置值，索引从1开始
            preparedStatement.setObject(1,hero.getSno());
            preparedStatement.setObject(2,hero.getSname());
            preparedStatement.setObject(3,hero.getSsex());
            preparedStatement.setObject(4,hero.getSage());
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println(affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }
    }



    public static List<Hero> findByHeroNameLike(String userName) throws IOException {
        Connection connection=getConnection();
        List<Hero> heroList=new ArrayList();
        String sql="select * from hero where sname like ?";
        PreparedStatement preparedStatement=null;
        ResultSet rs1=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userName+"%");


            rs1 = preparedStatement.executeQuery();
            while(rs1.next()) {
                Hero hero=new Hero();
                hero.setId(rs1.getInt("id"));
                hero.setSno(rs1.getString("sno"));
                hero.setSname(rs1.getString("sname"));
                hero.setSsex(rs1.getString("ssex"));
                hero.setSage(rs1.getInt("sage"));
                heroList.add(hero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }
       /* heroList.forEach(System.out::println);*/
        return heroList;

    }



    /*    public List<Hero> findAllHero(Hero hero) {
               Connection connection = JdbcUtil.getInstance().getConnection();
               Statement statement = null;
               ResultSet rs = null;
               List<Hero> heroList = new ArrayList<>(16);
               try {
                   statement = connection.createStatement();*/

    public static List<Hero> findOrderByUserName() throws IOException { //根据年龄排序
        Connection connection = getConnection();
        String sql="select *from Hero order by sage desc";
        PreparedStatement preparedStatement=null;
        ResultSet rs2=null;
        List heroList2=new ArrayList();
        try {
            preparedStatement=connection.prepareStatement(sql);
            rs2 = preparedStatement.executeQuery();


            while(rs2.next()) {
                Hero hero=new Hero();
                hero.setId(rs2.getInt("id"));
                hero.setSno(rs2.getString("sno"));
                hero.setSname(rs2.getString("sname"));
                hero.setSsex(rs2.getString("ssex"));
                hero.setSage(rs2.getInt("sage"));
                heroList2.add(hero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }
        return heroList2;
    }




    /**
     * userName:用户名
     * currPage:当前页
     * pageSize:每页显示的数量
     * @param userName
     * @param currPage
     * @param pageSize
     * 某个字段模糊查找并排序（升序），然后分页获取第二页数据的操作
     */
    public static List<Hero> findByUserNameLikeOrderLimit(String userName, int currPage, int pageSize) throws IOException {
        List<Hero> heroList3=new ArrayList<Hero>();
        Connection connection=getConnection();
/*
        findByHeroNameLike(userName);//先模糊查找
        findOrderByUserName();//根据年龄排序*/

        String sql="select *from hero where sname like ? order by sage desc limit ?,?";
        PreparedStatement preparedStatement=null;
        ResultSet rs3=null;

        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userName+"%");
            preparedStatement.setInt(2,(currPage-1)*pageSize);
            preparedStatement.setInt(3,pageSize);
            rs3=preparedStatement.executeQuery();
            while(rs3.next()){
                Hero hero=new Hero();
                hero.setId(rs3.getInt("id"));
                hero.setSno(rs3.getString("sno"));
                hero.setSname(rs3.getString("sname"));
                hero.setSsex(rs3.getString("ssex"));
                hero.setSage(rs3.getInt("sage"));
                heroList3.add(hero);
            }
            heroList3.forEach(System.out::println);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }

         return heroList3;
    }




    public static void closeResource(AutoCloseable autoCloseable) {
        if (null != autoCloseable) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }











   /* *//**
     * 通用的增删改方法
     *//*
    public int update(String sql, Object... objs) throws IOException {
        Connection connection = getConnection();
        // sql语句中未知的值，使用？代替，？占位符,在后面为其设置值
        PreparedStatement preparedStatement = null;

        // String sql = "insert into hero values(null,?,?,?,?)";
        // "delete form hero where id=?"
        try {
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i <= objs.length; i ++ ) {
                preparedStatement.setObject(i, objs[i-1]);
            }

            int a =  preparedStatement.executeUpdate();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
*/


}
