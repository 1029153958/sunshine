package day02homework.transcation;

import day01homework.JdbcUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainTranscation {

    public static void main(String[] args) {
        try {
            saveGrade();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存学生的分数，grade + subgrade = 100
     */
    public static void saveGrade() throws IOException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {

            String sql = "update transaction set grade = grade + 5 where sno = '001'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            String sql1 = "update transaction set subgrade = subgrade - 5 where sno = '001'";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResourse(preparedStatement);
            JdbcUtil.closeResourse(connection);
        }
    }
}

