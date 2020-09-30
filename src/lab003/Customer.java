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

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        //create new order
        OrderList orderList = new OrderList();
        do {
            orderList.productList.displayProduct();
            do {
                System.out.println("Select product (no): ");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 | choice > this.size());

            Product product = productList.get(choice - 1);

            System.out.println("Input quantity: ");
            quantity = Integer.parseInt(sc.nextLine());

            orderList.add(new Order(product.name, product.price, quantity));

            System.out.println("Do you want to order now? (Y/[N])");
            cont = (sc.nextLine().equals("Y"))? true:false;
        } while (cont);

        System.out.println("Input your name: ");
        orderList.name = sc.nextLine();

        this.add(orderList);
        return true;
    }

    public void viewOrderList() {
        for (OrderList list: this) {
            System.out.println("Customer: " + list.name);
            list.displayOrderList();
        }
    }

}
