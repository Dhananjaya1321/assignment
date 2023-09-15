package lk.ijse.pos.servlet.dao.castom.impl;

import lk.ijse.pos.servlet.dao.CrudUtil;
import lk.ijse.pos.servlet.dao.castom.QueryDAO;
import lk.ijse.pos.servlet.entity.CustomEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> searchOrder(CustomEntity customEntity,Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery(connection,"select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.oid = OrderDetails.oid where Orders.oid=?", customEntity.getOrderID());
        ArrayList<CustomEntity> customEntities = new ArrayList<>();
        while (resultSet.next()) {
            customEntities.add(
                    new CustomEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return customEntities;
    }

    @Override
    public boolean addOrderDetails(CustomEntity to, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery(connection,"insert into OrderDetails values(?,?,?,?)", to.getOrderID(), to.getCode(), to.getQty(), to.getPrice());
    }

}
