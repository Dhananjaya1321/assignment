package lk.ijse.pos.servlet.bo.castom.impl;

import lk.ijse.pos.servlet.bo.castom.ItemBO;
import lk.ijse.pos.servlet.dao.DAOType;
import lk.ijse.pos.servlet.dao.FactoryDAO;
import lk.ijse.pos.servlet.dao.castom.impl.ItemDAOImpl;
import lk.ijse.pos.servlet.dto.ItemDTO;
import lk.ijse.pos.servlet.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private final ItemDAOImpl itemBO = (ItemDAOImpl) FactoryDAO.getFactoryDAO().setDAOImpl(DAOType.ITEM);

    @Override
    public boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemBO.add(new Item(dto.getCode(), dto.getName(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemBO.update(new Item(dto.getCode(), dto.getName(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public boolean deleteItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemBO.delete(new Item(dto.getCode()));
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemBO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item i : all) {
            itemDTOS.add(
                    new ItemDTO(i.getCode(), i.getName(), i.getQty(), i.getPrice())
            );
        }
        return itemDTOS;
    }
}
