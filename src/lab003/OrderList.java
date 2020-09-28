package lab003;

import java.util.Scanner;
import java.util.ArrayList;

public class OrderList extends ArrayList<Order> {
    ProductList productList;
    public OrderList() {
    }

    public OrderList(ProductList productList) {
        this.productList = productList;
    }

    private void displayProduct(){
        //print header
        System.out.println("Product listing:");
        System.out.format("%5s %5s %20s %15s %10s",
                "No", "|", "Product name", "|", "Price");
        System.out.println("-----------------------------------------");
        //print invoice
        int count = 1;
        for (Product product: productList)
            System.out.format("%5d %5s %20s %15s %10f.2",
                    count, "|", product.name, "|", product.price);
        count++;
    }
    private void displayOrder() {
        //print header
        System.out.format("%5s %10s %5s %5s %5s %5s %5s",
                        "Product", "|", "Quantity", "|", "Price", "|", "Amount");
        System.out.println("----------------------------------------------------");
        //print invoice
        for (Order order: this)
            System.out.format("%5s %10s %5d %5s %5.2f %5s %5d",
                        order.product, "|", order.quantity, "|", order.price, "|", order.amount);
        System.out.println("Total: ", );
    }
    public boolean orderProduct(){
        Scanner sc = new Scanner(System.in);
        int choice, quantity;
        boolean cont;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        do {
            this.displayProduct();
            do {
                System.out.println("Select product (no): ");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 | choice > this.size());

            Product product = productList.get(choice - 1);

            System.out.println("Input quantity: ");
            quantity = Integer.parseInt(sc.nextLine());

            this.add(new Order(product.name, product.price, quantity));

            System.out.println("Do you want to order now? (Y/[N])");
            cont = (sc.nextLine().equals("Y"))? true:false;
        } while (cont);

        this.displayOrder();
        System.out.println("Total: ", );



        return true;
    }
}
