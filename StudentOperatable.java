package com.zgy2.HWork86;

import java.io.IOException;
import java.util.List;

public interface StudentOperatable {
    /**
     * 查询所有数据
     * @return 将所有数据封装到集合中返回
     */
    List<Student> findAllStu();

    /**
     * @param
     * @return 执行sql后返回受影响的行数
     */
    int updateStuById(String sql,Object... objs);

    /**
     * @param stu
     * @return 执行sql后返回受影响的行数
     */
    int insertStu(Student stu);

    /**
     * @param sno
     * @return 执行sql后返回受影响的行数
     */
    int deleteStuById(String sno);

    /**
     * @param sno
     */
    void findStuById(String sno) throws IOException;
}
