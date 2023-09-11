package lk.ijse.pos.servlet.bo.castom;

import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getCustomers() throws SQLException, ClassNotFoundException;
}
