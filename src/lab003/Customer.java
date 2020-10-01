package lab003;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends ArrayList<OrderList> {
    ProductList productList;

    public Customer() {
    }

    public Customer(ProductList productList) {
        this.productList = productList;
    }


    public boolean makeOrder() {
        Scanner sc = new Scanner(System.in);
        int choice, quantity;
        boolean cont;

        if (this.productList == null) {
            System.out.println("Error: Product list empty!");
            return false;
        }

        //create new order
        OrderList orderList = new OrderList(this.productList);
        do {
            orderList.productList.displayProduct();
            do {
                System.out.println("Select product (no): ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice<1 | choice>productList.size())
                    System.out.println("Error: Choice out of bounds!");
            } while (choice<1 | choice>productList.size());

            Product product = productList.get(choice - 1);

            System.out.println("Input quantity: ");
            quantity = Integer.parseInt(sc.nextLine());

            orderList.add(new Order(product.name, product.price, quantity));

            System.out.println("Do you want to order now? (Y/[N])");
            cont = (!sc.nextLine().equals("Y"));
        } while (cont);

        System.out.println("Input your name: ");
        orderList.name = sc.nextLine();

        this.add(orderList);
        return true;
    }

    public boolean viewOrderList() {
        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        for (OrderList list: this) {
            System.out.println("Customer: " + list.name);
            list.displayOrderList();
        }
        return true;
    }

}
