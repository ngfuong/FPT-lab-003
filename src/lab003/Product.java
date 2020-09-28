package lab003;

public class Product {
    String id, name, cId;
    double price;
    int quantity;

    public Product() {
    }

    public Product(String id, String name, String cId, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.cId = cId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cId='" + cId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
