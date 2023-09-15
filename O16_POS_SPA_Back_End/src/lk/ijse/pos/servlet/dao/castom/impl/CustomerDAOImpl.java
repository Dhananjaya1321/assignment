package lk.ijse.pos.servlet.dao.castom.impl;

import lk.ijse.pos.servlet.dao.CrudUtil;
import lk.ijse.pos.servlet.dao.castom.CustomerDAO;
import lk.ijse.pos.servlet.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer to, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(
                connection,
                "insert into Customer values(?,?,?,?)",
                to.getId(),
                to.getName(),
                to.getAddress(),
                to.getSalary()
        );
    }

    @Override
    public boolean update(Customer to,Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(
                connection,
                "update Customer set name=?,address=?,salary=? where id=?",
                to.getName(),
                to.getAddress(),
                to.getSalary(),
                to.getId()
                );
    }

    @Override
    public boolean delete(Customer to,Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery( connection,"delete from Customer where id=?", to.getId());
    }

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery( connection,"select * from Customer");
        ArrayList<Customer> customers=new ArrayList<>();
        while (resultSet.next()){
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return customers;
    }
}
