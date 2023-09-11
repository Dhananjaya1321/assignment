package lk.ijse.pos.servlet.entity;

public class Order {
    private String orderID;
    private String date;
    private String id;

    public Order() {
    }

    public Order(String orderID, String date, String id) {
        this.orderID = orderID;
        this.date = date;
        this.id = id;
    }

    public Order(String orderID, String date) {
        this.orderID = orderID;
        this.date = date;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
