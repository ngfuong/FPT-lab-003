package lab003;

import java.util.ArrayList;

public class OrderList extends ArrayList<Order> {
    ProductList productList;
    String name;
    double total=0;

    public OrderList() {
    }

    public OrderList(ProductList productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayOrderList() {
        //print header
        System.out.format("%5s %10s %5s %5s %5s %5s %5s",
                        "Product", "|", "Quantity", "|", "Price", "|", "Amount");
        System.out.println("----------------------------------------------------");
        //print invoice
        for (Order order: this) {
            System.out.format("%5s %10s %5d %5s %5.2f %5s %5d",
                    order.product, "|", order.quantity, "|", order.price, "|", order.amount);
            this.total += order.amount;
        }
        System.out.println("Total: " + this.total);
    }

}
