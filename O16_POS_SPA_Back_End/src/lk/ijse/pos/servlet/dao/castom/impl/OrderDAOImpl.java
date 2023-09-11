package lk.ijse.pos.servlet.dao.castom.impl;

import lk.ijse.pos.servlet.dao.CrudUtil;
import lk.ijse.pos.servlet.dao.castom.OrderDAO;
import lk.ijse.pos.servlet.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order to) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("insert into Orders values(?,?,?)", to.getOrderID(), to.getDate(), to.getId());
    }

    @Override
    public boolean update(Order to) {
        return false;
    }

    @Override
    public boolean delete(Order to) {
        return false;
    }

    @Override
    public ArrayList getAll() {
        return null;
    }
}
