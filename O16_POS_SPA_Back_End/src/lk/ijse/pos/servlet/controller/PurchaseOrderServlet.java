package lk.ijse.pos.servlet.controller;

import lk.ijse.pos.servlet.bo.BOTypes;
import lk.ijse.pos.servlet.bo.FactoryBO;
import lk.ijse.pos.servlet.bo.castom.impl.OrderBOImpl;
import lk.ijse.pos.servlet.dto.CustomDTO;
import lk.ijse.pos.servlet.dto.ItemDTO;
import lk.ijse.pos.servlet.util.ResponseUtil;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = {"/purchase_order"})
public class PurchaseOrderServlet extends HttpServlet {
    private final OrderBOImpl orderBO = (OrderBOImpl) FactoryBO.getFactoryBO().setBOImpl(BOTypes.ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<CustomDTO> customDTOS = orderBO.searchOrder(new CustomDTO(req.getParameter("oid")));

            JsonArrayBuilder allOrders = Json.createArrayBuilder();
            for (CustomDTO c: customDTOS) {
                JsonObjectBuilder orders = Json.createObjectBuilder();

                orders.add("oid", c.getOrderID());
                orders.add("date", c.getDate());
                orders.add("customerID", c.getId());
                orders.add("itemCode", c.getCode());
                orders.add("qty", c.getQty());
                orders.add("unitPrice", c.getPrice());

                allOrders.add(orders.build());
            }
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allOrders.build()));
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject orderJsonOb = Json.createReader(req.getReader()).readObject();

        try {
            CustomDTO customDTO=new CustomDTO();
            customDTO.setOrderID(orderJsonOb.getString("oid"));
            customDTO.setDate(orderJsonOb.getString("date"));
            customDTO.setId(orderJsonOb.getString("cusID"));

            JsonArray orderDetails = orderJsonOb.getJsonArray("orderDetails");
            ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
            ArrayList<ItemDTO> itemQTYDTOS=new ArrayList<>();

            for (JsonValue orderDetail : orderDetails) {
                JsonObject odObject = orderDetail.asJsonObject();
                String itemCode = odObject.getString("code");
                String qty = odObject.getString("qty");
                String avQty = odObject.getString("avQty");
                String unitPrice = odObject.getString("price");

                itemDTOS.add(new ItemDTO(itemCode,qty,unitPrice));

                int availableQty = Integer.parseInt(avQty);
                int purchasingQty = Integer.parseInt(qty);

                itemQTYDTOS.add(new ItemDTO(itemCode,Integer.toString(availableQty - purchasingQty)));
            }


            customDTO.setOrderDetails(itemDTOS);
            customDTO.setNewQTYs(itemQTYDTOS);


            if (orderBO.purchaseOrder(customDTO)) {
                resp.getWriter().print(ResponseUtil.genJson("Success", "Successfully Added.!"));
            }else {
                resp.getWriter().print(ResponseUtil.genJson("Error", "Error"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }
}
