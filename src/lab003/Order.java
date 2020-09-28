package lab003;

public class Order {
    String product, quantity;
    double price;
    int amount;

    public Order() {
    }

    public Order(String product, String quantity, double price, int amount) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
