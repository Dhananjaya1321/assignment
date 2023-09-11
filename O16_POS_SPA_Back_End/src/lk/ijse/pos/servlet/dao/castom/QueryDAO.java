package lk.ijse.pos.servlet.dao.castom;

import lk.ijse.pos.servlet.dao.SQLUtil;
import lk.ijse.pos.servlet.dao.SuperDAO;
import lk.ijse.pos.servlet.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    ArrayList<CustomEntity> searchOrder(CustomEntity customEntity) throws SQLException, ClassNotFoundException;

    boolean addOrderDetails(CustomEntity to) throws SQLException, ClassNotFoundException;
}
