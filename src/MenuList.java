import java.util.ArrayList;
import java.util.Scanner;
public class MenuList extends ArrayList<Menu> {

    public void printList() {
        System.out.println("++++ GRADE MANAGER ++++");
        int i = 1;
        for (Menu menu: this) {
            menu.print(i++);
        }

        System.out.println("Others - Quit program");
    }

    public int getListChoice() {
        Scanner sc = new Scanner(System.in);

        this.printList();

        System.out.println("+++++++++++++++++++++++");
        System.out.println("User choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        return choice;
    }
}
