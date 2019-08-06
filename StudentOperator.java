package day01;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentOperator implements StudentOperatorable{
    @Override
    public List<Student> findAll() {

        //建立单例连接
        Connection connection = JdbcUtil.getInstance().getConnection();

        Statement statement = null;
        ResultSet set = null;
        Student s = null;

        //新建List对象做返回值
        List<Student> students = new ArrayList<>();

        try {
            statement = connection.createStatement();

            String sql = "select * from student";

            //executeQuery执行查询语句，返回ResultSet类型的变量
            set = statement.executeQuery(sql);

            while (set.next()) {
                String sno = set.getString(1);
                String sname = set.getString(2);
                String ssex = set.getString(3);
                Date sbirthday = set.getDate(4);
                String sclass = set.getString(5);

                s = new Student();
                s.setSno(sno);
                s.setSname(sname);
                s.setSsex(ssex);
                s.setSbirthday(sbirthday);
                s.setSclass(sclass);

                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.getInstance().closeResourse(set);
            JdbcUtil.getInstance().closeResourse(statement);
        }
        return students;
    }

    @Override
    public int updateStudent(Student student) {
        Connection connection = JdbcUtil.getInstance().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();

            //修改语句
            String sql = "update student set sname = '"+student.getSname()+"',ssex = '"+student.getSsex()+"',sbirthday = '"+student.getSbirthday()+"',class = '"+student.getSclass()+"' where sno = '"+student.getSno()+"' ";

            //executeUpdate返回int类型变量
            int affectedRows = statement.executeUpdate(sql);

            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }
        return 0;
    }

    @Override
    public int insertStudent(Student student) {
        Connection connection = JdbcUtil.getInstance().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();

            String sql = "insert into student values ('"+null+"','"+student.getSname()+"','"+student.getSsex()+"','"+student.getSbirthday()+"','"+student.getSclass()+"')";

            int affectedRows = statement.executeUpdate(sql);
            return affectedRows;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }
        return 0;
    }

    @Override
    public int deleteStudent(String id) {
        Connection connection = JdbcUtil.getInstance().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();

            String sql = "delete from student where sno = "+id;

            int affectedRows = statement.executeUpdate(sql);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }
        return 0;
    }
}
