package lk.ijse.pos.servlet.dao.castom.impl;

import lk.ijse.pos.servlet.dao.CrudUtil;
import lk.ijse.pos.servlet.dao.castom.OrderDAO;
import lk.ijse.pos.servlet.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order to,Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(connection,"insert into Orders values(?,?,?)", to.getOrderID(), to.getDate(), to.getId());
    }

    @Override
    public boolean update(Order to,Connection connection) {
        return false;
    }

    @Override
    public boolean delete(Order to,Connection connection) {
        return false;
    }

    @Override
    public ArrayList getAll(Connection connection) {
        return null;
    }
}
