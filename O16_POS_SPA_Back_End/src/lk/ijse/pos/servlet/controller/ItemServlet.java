package lk.ijse.pos.servlet.controller;

import lk.ijse.pos.servlet.bo.BOTypes;
import lk.ijse.pos.servlet.bo.FactoryBO;
import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.bo.castom.impl.ItemBOImpl;
import lk.ijse.pos.servlet.dto.ItemDTO;
import lk.ijse.pos.servlet.listener.Listener;
import lk.ijse.pos.servlet.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


@WebServlet(urlPatterns = {"/item"})
public class ItemServlet extends HttpServlet {
    private final ItemBOImpl itemBO = (ItemBOImpl) FactoryBO.getFactoryBO().setBOImpl(BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");
        try (Connection connection = dataSource.getConnection()) {
            ArrayList<ItemDTO> items = itemBO.getAllItems(connection);
            JsonArrayBuilder allItems = Json.createArrayBuilder();
            for (ItemDTO i : items) {
                JsonObjectBuilder itemObject = Json.createObjectBuilder();
                itemObject.add("code", i.getCode());
                itemObject.add("description", i.getName());
                itemObject.add("qty", i.getQty());
                itemObject.add("unitPrice", i.getPrice());
                allItems.add(itemObject.build());
            }
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allItems.build()));
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");
        try (Connection connection = dataSource.getConnection()) {
            if (itemBO.addItem(new ItemDTO(req.getParameter("code"), req.getParameter("description"), req.getParameter("itemQty"), req.getParameter("unitPrice")), connection)) {
                resp.getWriter().print(ResponseUtil.genJson("Success", "Successfully Added.!"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");
        try (Connection connection = dataSource.getConnection()) {
            if (itemBO.updateItem(new ItemDTO(jsonObject.getString("code"), jsonObject.getString("description"), jsonObject.getString("qtyOnHand"), jsonObject.getString("unitPrice")), connection)) {
                resp.getWriter().print(ResponseUtil.genJson("Success", "Item Updated..!"));
            } else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Item Updated Failed..!"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");
        try (Connection connection = dataSource.getConnection()) {
            if (itemBO.deleteItem(new ItemDTO(req.getParameter("code")), connection)) {
                resp.getWriter().print(ResponseUtil.genJson("Success", "Item Deleted..!"));
            } else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Item Delete Failed..!"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }
}
