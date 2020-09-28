package lab003;

import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileIO fio = new FileIO();
        Utility util = new Utility();

        MenuList mainList= new MenuList();

        Menu addCategory = new Menu("Add new category");
        Menu updateCategory = new Menu("Update category");
        updateCategory.add("Update category");
        updateCategory.add("Delete category");
        Menu addProduct = new Menu("Add product");
        Menu updateProduct = new Menu("Update product");
        updateProduct.add("Update product");
        updateProduct.add("Delete product");
        Menu orderProduct = new Menu("Order product");
        Menu showOrderList = new Menu("Show order list report");

        mainList.add(addCategory);
        mainList.add(updateCategory);
        mainList.add(addProduct);
        mainList.add(updateProduct);
        mainList.add(orderProduct);
        mainList.add(showOrderList);

        /*BEGIN OF FILE IO*/
        //category
        System.out.println("Enter category file name: ");
        String cName = sc.nextLine();
        String cPath = fio.getFilePath(cName.length()>0 ? cName:"category.txt");
        File cFile = fio.readData(cPath, "c");
        //product
        System.out.println("Enter product file name: ");
        String pName = sc.nextLine();
        String pPath = fio.getFilePath(pName.length()>0 ? pName:"product.txt");
        File pFile = fio.readData(pPath, "p");

        CategoryList categoryList = CategoryList.readData();
        ProductList productList = ProductList(categoryList);
        /*ENF OF FILE IO*/

        /*BEGIN OF MAIN MENU*/
        int listChoice;
        do {
            listChoice = mainList.getListChoice();

            if (listChoice==2 | listChoice==4) {
                /*BEGIN OF SUB MENU*/
                int menuChoice;
                do {
                    menuChoice = mainList.get(listChoice-1).getMenuChoice();
                    if (menuChoice<=mainList.get(listChoice-1).size() && menuChoice>=1){
                        switch (menuChoice) {
                            case 1: //updating
                                listChoice==2?
                                        util.printStatus(categoryList.updateCategory()) :
                                        util.printStatus(productList.updateProduct());
                                break;
                            case 2: //deleting
                                listChoice==2?
                                        util.printStatus(categoryList.deleteCategory()) :
                                        util.printStatus(productList.deleteProduct());
                                break;
                            default:
                                System.out.println("Exiting menu...");
                        }
                    }
                } while (menuChoice<=mainList.get(listChoice-1).size() && menuChoice>=1);
                /*END OF SUB MENU*/
            }

            else  {
                switch(listChoice) {
                    case 1:
                        //add new category
                        util.printStatus(categoryList.addCategory());
                    case 3:
                        //add new product
                        util.printStatus(productList.addProduct());
                    case 5:
                        //order product
                        //util.printStatus(orderList.)
                    case 6:
                        //view order list
                    default:
                        System.out.println("Exiting program...");
                }
            }
        } while (listChoice<=mainList.size() && listChoice>=1);
        /*END OF MAIN MENU*/
    }
}
