package lk.ijse.pos.servlet.dao.castom;

import lk.ijse.pos.servlet.dao.SQLUtil;
import lk.ijse.pos.servlet.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends SQLUtil<Item> {
    boolean updateItemQTY(Item to, Connection connection) throws SQLException, ClassNotFoundException;
}
