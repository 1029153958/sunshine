package day02homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentOperator implements StudentOperatorable {
	
    /**
     * 模糊查询
     *
     * @param name
     * @return
     */
    public List<Student> findByUserNameLike(String name) {

        List<Student> students = new ArrayList<>();

        Connection connection = JdbcUtil.getInstance().getConnection();
        String sql = "select * from student where sname like ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name + "%");
            set = preparedStatement.executeQuery();
            while (set.next()) {
                String sno = set.getString(1);
                String sname = set.getString(2);
                String ssex = set.getString(3);
                Date sbirthday = set.getDate(4);
                String sclass = set.getString(5);

                Student s = new Student();
                s.setSno(sno);
                s.setSname(sname);
                s.setSsex(ssex);
                s.setSbirthday(sbirthday);
                s.setSclass(sclass);

                students.add(s);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<Student> findOrderByUserName() {

        List<Student> students = new ArrayList<>();

        Connection connection = JdbcUtil.getInstance().getConnection();
        String sql = "select * from student order by sname";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while (set.next()) {
                String sno = set.getString(1);
                String sname = set.getString(2);
                String ssex = set.getString(3);
                Date sbirthday = set.getDate(4);
                String sclass = set.getString(5);

                Student s = new Student();
                s.setSno(sno);
                s.setSname(sname);
                s.setSsex(ssex);
                s.setSbirthday(sbirthday);
                s.setSclass(sclass);

                students.add(s);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 模糊查询 + 分页
     *
     * @param name
     * @param currPage
     * @param pageSize
     * @return
     */
    public List<Student> findByUserNameLikeOrderLimit(String name, int currPage, int pageSize) {

        List<Student> students = new ArrayList<>();

        Connection connection = JdbcUtil.getInstance().getConnection();
        String sql = "select * from student where sname like ? limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name + "%");
            preparedStatement.setInt(2, (currPage - 1) * 2);
            preparedStatement.setInt(3, pageSize);
            set = preparedStatement.executeQuery();
            while (set.next()) {
                String sno = set.getString(1);
                String sname = set.getString(2);
                String ssex = set.getString(3);
                Date sbirthday = set.getDate(4);
                String sclass = set.getString(5);

                Student s = new Student();
                s.setSno(sno);
                s.setSname(sname);
                s.setSsex(ssex);
                s.setSbirthday(sbirthday);
                s.setSclass(sclass);

                students.add(s);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
