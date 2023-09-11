package lk.ijse.pos.servlet.dto;

import java.util.ArrayList;

public class CustomDTO {
    private String id;
    private String itemName;
    private String address;
    private String salary;
    private String code;
    private String customerName;
    private String qty;
    private String price;
    private String orderID;
    private String date;
    private ArrayList<ItemDTO> orderDetails;
    private ArrayList<ItemDTO> newQTYs;

    public CustomDTO(String orderID, String date, String id, String code, String qty, String price) {
        this.orderID = orderID;
        this.date = date;
        this.id = id;
        this.code = code;
        this.qty = qty;
        this.price = price;
    }

    public CustomDTO() {
    }

    public CustomDTO(String orderID) {
        this.orderID = orderID;
    }

    public CustomDTO(String id, String itemName, String address, String salary, String code, String customerName, String qty, String price, String orderID, String date) {
        this.id = id;
        this.itemName = itemName;
        this.address = address;
        this.salary = salary;
        this.code = code;
        this.customerName = customerName;
        this.qty = qty;
        this.price = price;
        this.orderID = orderID;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", address='" + address + '\'' +
                ", salary='" + salary + '\'' +
                ", code='" + code + '\'' +
                ", customerName='" + customerName + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                ", orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public ArrayList<ItemDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<ItemDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ArrayList<ItemDTO> getNewQTYs() {
        return newQTYs;
    }

    public void setNewQTYs(ArrayList<ItemDTO> newQTYs) {
        this.newQTYs = newQTYs;
    }
}
