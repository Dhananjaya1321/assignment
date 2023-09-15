package lk.ijse.pos.servlet.bo.castom.impl;

import lk.ijse.pos.servlet.bo.castom.CustomerBO;
import lk.ijse.pos.servlet.dao.DAOType;
import lk.ijse.pos.servlet.dao.FactoryDAO;
import lk.ijse.pos.servlet.dao.castom.impl.CustomerDAOImpl;
import lk.ijse.pos.servlet.dto.CustomerDTO;
import lk.ijse.pos.servlet.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAOImpl customerDAO = (CustomerDAOImpl) FactoryDAO.getFactoryDAO().setDAOImpl(DAOType.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()),connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()),connection);
    }

    @Override
    public boolean deleteCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(new Customer(dto.getId()),connection);
    }

    @Override
    public ArrayList<CustomerDTO> getCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll(connection);
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(
                    new CustomerDTO(
                            c.getId(),
                            c.getName(),
                            c.getAddress(),
                            c.getSalary()
                    )
            );
        }
        return customerDTOS;
    }
}
