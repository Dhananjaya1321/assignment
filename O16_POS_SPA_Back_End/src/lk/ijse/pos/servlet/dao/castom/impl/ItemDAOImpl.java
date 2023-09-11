package lk.ijse.pos.servlet.dao.castom.impl;

import lk.ijse.pos.servlet.dao.CrudUtil;
import lk.ijse.pos.servlet.dao.castom.ItemDAO;
import lk.ijse.pos.servlet.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item to) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(
                "insert into Item values(?,?,?,?)",
                to.getCode(),
                to.getName(),
                to.getQty(),
                to.getPrice()
        );
    }

    @Override
    public boolean update(Item to) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(
                "update Item set description=?,qtyOnHand=?,unitPrice=? where code=?",
                to.getName(),
                to.getQty(),
                to.getPrice(),
                to.getCode()
        );
    }
    @Override
    public boolean updateItemQTY(Item to) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(
                "update Item set qtyOnHand=? where code=?",
                to.getQty(),
                to.getCode()
        );
    }

    @Override
    public boolean delete(Item to) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(
                "delete from Item where code=?",
                to.getCode()
        );
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select * from Item");
        ArrayList<Item> items=new ArrayList<>();
        while (resultSet.next()){
            items.add(
                    new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return items;
    }
}
