package lk.ijse.pos.servlet.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SQLUtil<T> extends SuperDAO{
    boolean add(T to, Connection connection) throws SQLException, ClassNotFoundException;
    boolean update(T to,Connection connection) throws SQLException, ClassNotFoundException;
    boolean delete(T to,Connection connection) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll(Connection connection) throws SQLException, ClassNotFoundException;
}
