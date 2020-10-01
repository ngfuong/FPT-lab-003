package lab002;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String>{
    String name;
    String choice;

    public Menu() {
    }

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void print(int i) {
        System.out.println(i+"-"+this.name);

        //have sub-menu
        if (this.size()!=0) {
            return;
        }

        int count = 1;
        for (String s:this)
            System.out.println(i+"-"+count++ + "-"+s);
    }

    public void printMenu() {
        int i=1;
        for (String s:this)
            System.out.println(i++ + "-" +s);
        System.out.println("Others - Quit this menu");
    }

    public int getMenuChoice() {
        System.out.println("+++++++++++++++++++++++");

        this.printMenu();

        Scanner sc = new Scanner(System.in);
        System.out.println("User option:");

        System.out.println("+++++++++++++++++++++++");

        //return subChoice
        return Integer.parseInt(sc.nextLine());
    }
}
