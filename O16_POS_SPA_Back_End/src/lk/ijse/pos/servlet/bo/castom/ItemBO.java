package lk.ijse.pos.servlet.bo.castom;

import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}
