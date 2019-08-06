package com.zgy2.HWork86;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentOperator implements StudentOperatable {

    // 查询所有记录
    @Override
    public List<Student> findAllStu() {

        Connection conn = null;
        try {
            conn = DbUtil.getInstance().getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            String sql = "select * from student";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String sno = rs.getString(1);
                String sname = rs.getString(2);
                String ssex = rs.getString(3);
                String sbirthday = rs.getString(4);
                String clz = rs.getString(5);
                // 从数据库传入对象
                Student stu = new Student();
                stu.setSno(sno);
                stu.setSname(sname);
                stu.setSsex(ssex);
                stu.setSbirthday(sbirthday);
                stu.setClz(clz);
                // 添加到列表
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().closeResourse(rs);
            DbUtil.getInstance().closeResourse(stmt);
            DbUtil.getInstance().closeResourse(conn);
        }

        return list;
    }

    // 根据学号修改
    @Override
    public int updateStuById(String sql, Object... objs) {
        Connection conn = null;
        try {
            conn = DbUtil.getInstance().getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= objs.length; i++) {
                ps.setObject(i,objs[i-1]);
            }
            int a = ps.executeUpdate();
            return a;
         } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeResourse(ps);
            DbUtil.closeResourse(conn);
        }
        return 0;
    }

    // 插入学生信息
    @Override
    public int insertStu(Student stu) {
        Connection conn = null;
        try {
            conn = DbUtil.getInstance().getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String insertSql = "insert into student values(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            // 获取处理sql语句
            preparedStatement = conn.prepareStatement(insertSql);
            // 为占位符依次设置值，索引从1开始
            preparedStatement.setObject(1, stu.getSno());
            preparedStatement.setObject(2, stu.getSname());
            preparedStatement.setObject(3, stu.getSsex());
            preparedStatement.setObject(4, stu.getSbirthday());
            preparedStatement.setObject(5, stu.getClz());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResourse(preparedStatement);
            DbUtil.closeResourse(conn);
        }
        return 0;
    }

    // 根据学号删除
    @Override
    public int deleteStuById(String sno) {
        Connection conn = null;
        try {
            conn = DbUtil.getInstance().getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sqlDel = "delete form student set sno=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sqlDel);
            ps.setString(1, sno);
            int affectedRows = ps.executeUpdate(sqlDel);
            // 返回受影响的行数
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeResourse(ps);
            DbUtil.closeResourse(conn);
        }
        // 修改失败 返回0行
        return 0;
    }

    // 按学号查找某一个学生
    @Override
    public void findStuById(String sno) throws IOException {
        Connection conn = DbUtil.getInstance().getConnection();
        String sql = "select * from student where sno=?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,sno);
            resultSet = ps.executeQuery();
            // 一个学号查找结果为一条，打印结果
            if(resultSet.next()){
                String id = resultSet.getString(1);
                String sname = resultSet.getString(2);
                String ssex = resultSet.getString(3);
                String sbrithday = resultSet.getString(4);
                String clz = resultSet.getString(5);
                System.out.println(id+" "+sname+" "+ssex+" "+sbrithday+" "+clz);
            }else{
                System.out.println("查找学生不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeResourse(resultSet);
            DbUtil.closeResourse(ps);
            DbUtil.closeResource(conn);
        }

    }

    // 姓名的模糊查找 传入一个姓，或名字前几个字，查找学生
    public List<Student> findBySnameLike(String sname) throws IOException {
        Connection conn = DbUtil.getInstance().getConnection();
        String sql = "select * from student where sname like ?";
        PreparedStatement ps = null;
        List<Student> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,sname+"%");
//            ps.setString(2,"%");
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sno = resultSet.getString(1);
                String name = resultSet.getString(2);
                String ssex = resultSet.getString(3);
                String sbirthday = resultSet.getString(4);
                String clz = resultSet.getString(5);
                // 从数据库传入对象
                Student stu = new Student(sno,name,ssex,sbirthday,clz);
                // 添加到列表
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeResourse(resultSet);
            DbUtil.closeResourse(ps);
            DbUtil.closeResourse(conn);
        }
        return list;
    }

    // 根据某一字段(传入参数)降序排列
    public List<Student> findOrderByUserName() throws IOException {
        Connection conn = DbUtil.getInstance().getConnection();
        String sql = "select * from student order by sno desc";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
//            ps.setString(1,str);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sno = resultSet.getString(1);
                String sname = resultSet.getString(2);
                String ssex = resultSet.getString(3);
                String sbirthday = resultSet.getString(4);
                String clz = resultSet.getString(5);
                // 从数据库传入对象
                Student stu = new Student(sno,sname,ssex,sbirthday,clz);
                // 添加到列表
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.getInstance().closeResourse(resultSet);
            DbUtil.getInstance().closeResourse(ps);
            DbUtil.getInstance().closeResourse(conn);
        }
        return list;
    }

    // 根据某字段模糊查询结果分页，每页两条数据
    public List<Student> findLikeOrderByUserName(String sname,int currPage, int pageSize) throws IOException {
        Connection conn = DbUtil.getInstance().getConnection();
        String sql = "select * from student where sname like ? limit ?,?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,sname+"%");
            ps.setInt(2,(currPage-1)*pageSize);
            ps.setInt(3,pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sno = resultSet.getString(1);
                String name = resultSet.getString(2);
                String ssex = resultSet.getString(3);
                String sbirthday = resultSet.getString(4);
                String clz = resultSet.getString(5);
                // 从数据库传入对象
                Student stu = new Student(sno,name,ssex,sbirthday,clz);
                // 添加到列表
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.getInstance().closeResourse(resultSet);
            DbUtil.getInstance().closeResourse(ps);
            DbUtil.getInstance().closeResourse(conn);
        }
        return list;
    }

}
