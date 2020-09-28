package lab003;

public class Order {
    String product;
    double price, amount;
    int quantity;

    public Order() {
    }

    public Order(String product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amount = price*quantity;
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
