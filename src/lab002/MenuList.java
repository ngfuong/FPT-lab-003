package lab002;

import java.util.ArrayList;
import java.util.Scanner;
public class MenuList extends ArrayList<Menu> {

    public void printList() {
        System.out.println("++++ USER MANAGER ++++");
        int i = 1;
        for (Menu menu: this) {
            menu.print(i++);
        }

        System.out.println("Others - Quit program");
    }

    public int getListChoice() {
        Scanner sc = new Scanner(System.in);
        int choice=-1;

        this.printList();

        System.out.println("+++++++++++++++++++++++");

        boolean cont;
        do {
            System.out.println("User choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                cont = false;
            } catch (Exception e) {
                System.out.println("Error: Input must be a number!");
                cont=true;
            }
        } while (cont);

        return choice;
    }
}
