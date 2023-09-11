package lk.ijse.pos.servlet.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SQLUtil<T> extends SuperDAO{
    boolean add(T to) throws SQLException, ClassNotFoundException;
    boolean update(T to) throws SQLException, ClassNotFoundException;
    boolean delete(T to) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
}
