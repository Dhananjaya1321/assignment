package lk.ijse.pos.servlet.dao;

import lk.ijse.pos.servlet.dao.castom.impl.CustomerDAOImpl;
import lk.ijse.pos.servlet.dao.castom.impl.ItemDAOImpl;
import lk.ijse.pos.servlet.dao.castom.impl.OrderDAOImpl;
import lk.ijse.pos.servlet.dao.castom.impl.QueryDAOImpl;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    private FactoryDAO() {
    }

    public static FactoryDAO getFactoryDAO() {
        if (factoryDAO == null) {
            return factoryDAO = new FactoryDAO();
        } else {
            return factoryDAO;
        }
    }

    public SuperDAO setDAOImpl(DAOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
