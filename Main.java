package prestate;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        try {
            saveOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrder() throws IOException {

        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {


            String sql = "insert into draw values(null, 1, 5)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();


            String sql1 = "update save set mcout = mcout-5 where mid=1";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(preparedStatement);
            JdbcUtil.closeResource(connection);
        }


    }

}

