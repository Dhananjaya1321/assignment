package lk.ijse.pos.servlet.bo.castom;

import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    ArrayList<CustomDTO> searchOrder(CustomDTO dto) throws SQLException, ClassNotFoundException;

    boolean purchaseOrder(CustomDTO dto) throws SQLException, ClassNotFoundException;
}
