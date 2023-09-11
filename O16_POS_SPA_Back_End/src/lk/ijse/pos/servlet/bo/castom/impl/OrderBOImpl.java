package lk.ijse.pos.servlet.bo.castom.impl;

import lk.ijse.pos.servlet.bo.castom.OrderBO;
import lk.ijse.pos.servlet.dao.DAOType;
import lk.ijse.pos.servlet.dao.FactoryDAO;
import lk.ijse.pos.servlet.dao.castom.impl.ItemDAOImpl;
import lk.ijse.pos.servlet.dao.castom.impl.OrderDAOImpl;
import lk.ijse.pos.servlet.dao.castom.impl.QueryDAOImpl;
import lk.ijse.pos.servlet.dto.CustomDTO;
import lk.ijse.pos.servlet.dto.ItemDTO;
import lk.ijse.pos.servlet.entity.CustomEntity;
import lk.ijse.pos.servlet.entity.Item;
import lk.ijse.pos.servlet.entity.Order;
import lk.ijse.pos.servlet.listener.Listener;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final QueryDAOImpl queryDAO = (QueryDAOImpl) FactoryDAO.getFactoryDAO().setDAOImpl(DAOType.QUERY);
    private final OrderDAOImpl orderDAO = (OrderDAOImpl) FactoryDAO.getFactoryDAO().setDAOImpl(DAOType.ORDER);
    private final ItemDAOImpl itemDAOerDAO = (ItemDAOImpl) FactoryDAO.getFactoryDAO().setDAOImpl(DAOType.ITEM);

    @Override
    public ArrayList<CustomDTO> searchOrder(CustomDTO dto) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> customEntities = queryDAO.searchOrder(new CustomEntity(dto.getOrderID()));
        ArrayList<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomEntity c : customEntities) {
            customDTOS.add(
                    new CustomDTO(c.getOrderID(), c.getDate(), c.getId(), c.getCode(), c.getQty(), c.getPrice())
            );
        }
        return customDTOS;
    }

    @Override
    public boolean purchaseOrder(CustomDTO dto) throws SQLException, ClassNotFoundException {
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");
        Connection connection = dataSource.getConnection();

        connection.setAutoCommit(false);
        try {
            boolean isAdded = orderDAO.add(new Order(dto.getOrderID(), dto.getDate(), dto.getId()));
            if (isAdded) {
                ArrayList<ItemDTO> itemDTOS = dto.getOrderDetails();
                int count = 0;
                for (ItemDTO i : itemDTOS) {
                    boolean isAddedOrderDetails = queryDAO.addOrderDetails(new CustomEntity(dto.getOrderID(), i.getCode(), i.getQty(), i.getPrice()));
                    if (isAddedOrderDetails) {
                        count++;
                    }
                }
                if (count != itemDTOS.size()) {
                    connection.rollback();
                }

                count = 0;
                ArrayList<ItemDTO> newQTYs = dto.getNewQTYs();
                for (ItemDTO i : newQTYs) {
                    boolean isUpdated = itemDAOerDAO.updateItemQTY(new Item(i.getCode(), i.getQty()));
                    if (isUpdated) {
                        count++;
                    }
                }
                if (count != newQTYs.size()) {
                    connection.rollback();
                }

                return true;
            } else {
                connection.rollback();
                return true;
            }
        } finally {
            connection.setAutoCommit(false);
        }
    }
}
