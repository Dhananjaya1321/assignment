package lk.ijse.pos.servlet.controller;


import lk.ijse.pos.servlet.bo.BOTypes;
import lk.ijse.pos.servlet.bo.FactoryBO;
import lk.ijse.pos.servlet.bo.SuperBO;
import lk.ijse.pos.servlet.bo.castom.impl.CustomerBOImpl;
import lk.ijse.pos.servlet.dto.CustomerDTO;
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


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private final CustomerBOImpl customerBO = (CustomerBOImpl) FactoryBO.getFactoryBO().setBOImpl(BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = dataSource.getConnection()) {
            ArrayList<CustomerDTO> customers = customerBO.getCustomers(connection);
            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            for (CustomerDTO c:customers) {
                JsonObjectBuilder customerObject = Json.createObjectBuilder();
                customerObject.add("id", c.getId());
                customerObject.add("name", c.getName());
                customerObject.add("address", c.getAddress());
                customerObject.add("salary", c.getSalary());
                allCustomers.add(customerObject.build());
            }
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allCustomers.build()));
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = Listener.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletContext.getAttribute("dbcp");
        try (Connection connection=dataSource.getConnection()){
            if (customerBO.addCustomer(new CustomerDTO(req.getParameter("cusID"),req.getParameter("cusName"),req.getParameter("cusAddress"),req.getParameter("cusSalary")),connection)) {
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
        try (Connection connection=dataSource.getConnection()){
            if (customerBO.updateCustomer(new CustomerDTO(jsonObject.getString("id"), jsonObject.getString("name"),jsonObject.getString("address"),jsonObject.getString("salary")),connection)) {
                resp.getWriter().print(ResponseUtil.genJson("Success", "Customer Updated..!"));
            } else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Customer Updated Failed..!"));
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
        try (Connection connection=dataSource.getConnection()){
            if (customerBO.deleteCustomer(new CustomerDTO(req.getParameter("cusID")),connection)) {
                resp.getWriter().print(ResponseUtil.genJson("Success", "Customer Deleted..!"));
            } else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Customer Delete Failed..!"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }
}
