package lab003;

public class App {
    public static void main(String[] args) {
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
        CategoryList categoryList = new CategoryList();
        categoryList.readData();
        ProductList productList = new ProductList(categoryList);
        productList.readData();
        /*ENF OF FILE IO*/

        Customer customerList = new Customer();

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
                                if (listChoice==2)
                                        util.printStatus(categoryList.updateCategory());
                                else util.printStatus(productList.updateProduct());
                                break;
                            case 2: //deleting
                                if (listChoice==2)
                                        util.printStatus(categoryList.deleteCategory());
                                else util.printStatus(productList.deleteProduct());
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
                        break;
                    case 3:
                        //add new product
                        util.printStatus(productList.addProduct());
                        break;
                    case 5:
                        //order product
                        util.printStatus(customerList.makeOrder());
                        break;
                    case 6:
                        //view order list
                        customerList.viewOrderList();
                        break;
                    default:
                        System.out.println("Exiting program...");
                }
            }
        } while (listChoice<=mainList.size() && listChoice>=1);
        /*END OF MAIN MENU*/
    }
}
