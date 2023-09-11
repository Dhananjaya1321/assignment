package lk.ijse.pos.servlet.bo;

import lk.ijse.pos.servlet.bo.castom.impl.CustomerBOImpl;
import lk.ijse.pos.servlet.bo.castom.impl.ItemBOImpl;
import lk.ijse.pos.servlet.bo.castom.impl.OrderBOImpl;

public class FactoryBO {
    private static FactoryBO factoryBO;

    private FactoryBO() {
    }

    public static FactoryBO getFactoryBO() {
        if (factoryBO == null) {
            return factoryBO = new FactoryBO();
        } else {
            return factoryBO;
        }
    }

    public SuperBO setBOImpl(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
