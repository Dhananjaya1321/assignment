package lk.ijse.pos.servlet.bo.castom;

import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;
}
