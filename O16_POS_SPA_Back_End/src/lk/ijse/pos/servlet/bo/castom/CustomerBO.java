package lk.ijse.pos.servlet.bo.castom;

import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getCustomers(Connection connection) throws SQLException, ClassNotFoundException;
}
