package lk.ijse.pos.servlet.dao;

import lk.ijse.pos.servlet.listener.Listener;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T setQuery(Connection connection,String sql, Object... args) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");


        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject((i + 1), args[i]);
        }
        if (sql.startsWith("select") || sql.startsWith("SELECT")) {
            return (T) preparedStatement.executeQuery();
        }
        return (T) (Boolean) (preparedStatement.executeUpdate() > 0);
    }
}
