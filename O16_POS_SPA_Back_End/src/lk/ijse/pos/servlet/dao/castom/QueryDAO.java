package lk.ijse.pos.servlet.dao.castom;

import lk.ijse.pos.servlet.dao.SQLUtil;
import lk.ijse.pos.servlet.dao.SuperDAO;
import lk.ijse.pos.servlet.entity.CustomEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    ArrayList<CustomEntity> searchOrder(CustomEntity customEntity,Connection connection) throws SQLException, ClassNotFoundException;

    boolean addOrderDetails(CustomEntity to, Connection connection) throws SQLException, ClassNotFoundException;
}
