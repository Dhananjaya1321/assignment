package lk.ijse.pos.servlet.dto;

public class ItemDTO {
    private String code;
    private String name;
    private String qty;
    private String price;

    public ItemDTO(String code, String qty, String price) {
        this.code = code;
        this.qty = qty;
        this.price = price;
    }

    public ItemDTO(String code, String name, String qty, String price) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public ItemDTO(String qty) {
        this.qty = qty;
    }

    public ItemDTO(String code, String qty) {
        this.code = code;
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ItemDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
