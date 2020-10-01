package lab003;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductList extends ArrayList<Product> {
    CategoryList categoryList;

    public ProductList() {
    }

    public ProductList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    /*BEGIN OF FILE IO*/
    final String PATH = System.getProperty("user.dir") + "/";
    public void readData() {
        Scanner sc = new Scanner(System.in);
        //ProductList list = new ProductList();

        System.out.println("Enter product file name: ");
        String fName = sc.nextLine();
        fName = (fName.length()==0)? "product.txt" : fName;

        try {
            File f = new File(PATH + fName);
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String delim = "[{}:',]+";

                String id = line.split(delim)[2];
                String name = line.split(delim)[4];
                String cId = line.split(delim)[6];
                double price = Double.parseDouble(line.split(delim)[8]);
                int quantity = Integer.parseInt(line.split(delim)[10]);

                this.add(new Product(id, name, cId, price, quantity));
            }
            //return list;
        } catch (Exception e) {
            //e.printStackTrace();
            //System.out.println("Error: Read file failed!");
            //return list;
        }
    }
    /*END OF FILE IO*/

    /*BEGIN OF DATA VALIDATION*/
    private int searchID(String id) {
        for (int i=0; i<this.size(); i++)
            if (this.get(i).id.equals(id))
                return i;

        return -1;
    }
    private boolean isPrice(double price){
        return (price>0);
    }
    private boolean isQuantity(int quantity) {
        return (quantity>0);
    }
    /*USE SEARCHID IN CATEGORYLIST INSTEAD*/
    private int getCategoryPos(String cId) {
        for (int i=0; i<this.size(); i++)
            if (categoryList.get(i).id.equals(cId)) return i;

        return -1;
    }
    /*END*/
    private boolean isNull(String s) {
        return s.length()==0;
    }
    /*END OF DATA VALIDATION*/

    public boolean addProduct() {
        Scanner sc = new Scanner(System.in);
        String id, cId, name;
        double price;
        int quantity;
        int pos;

        if (categoryList.isEmpty()) {
            System.out.println("Error: Insufficient categories!");
            return false;
        }

        do {
            System.out.println("Enter new product ID: ");
            id = sc.nextLine();
            if (isNull(id))
                System.out.println("Error: Input cannot be null!");
            pos = this.searchID(id);
            if (pos!=-1)
                System.out.println("Error: ID existed!");
        } while (isNull(id) | pos!=-1);

        do {
            System.out.println("Enter name: ");
            name = sc.nextLine();
            if (isNull(name))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(name));

        do {
            System.out.println("Enter price:");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (!isPrice(price))
                    System.out.println("Error: Price must be a positive number!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: Price must be a positive number!");
                return false;
            }
        } while (!isPrice(price));

        do {
            System.out.println("Enter quantity: ");
            try {
                quantity = Integer.parseInt(sc.nextLine());
                if (!isQuantity(quantity))
                    System.out.println("Error: Quantity must be a positive integer!");
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Error: Quantity must be a positive number!");
                return false;
            }
        } while (!isQuantity(quantity));

        do {
            System.out.println("Enter category ID: ");
            cId = sc.nextLine();
            if (this.categoryList.searchID(cId)==-1)
                System.out.println("Error: ID not existed!");
        } while (this.categoryList.searchID(cId)==-1);

        try {
            this.add(new Product(id, name, cId, price, quantity));
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean updateProduct() {
        Scanner sc = new Scanner(System.in);
        String id, cId, name;
        double price=0;
        int quantity=0;
        int pos;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        do {
            System.out.println("Enter product ID: ");
            id = sc.nextLine();
            pos = searchID(id);
            if (pos==-1)
                System.out.println("Error: ID not existed!");
        } while (pos==-1);

        System.out.println("Update name: ");
        name = sc.nextLine();

        String inputPrice;
        do {
            System.out.println("Update price:");
            inputPrice = sc.nextLine();
            if (!isNull(inputPrice))
                try {
                    price = Double.parseDouble(inputPrice);
                    if (!isPrice(price)) {
                        System.out.println("Error: Price must be a positive number!");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Price must be a postitive number!");
                    return false;
                }
        } while (!isNull(inputPrice) && !isPrice(price));

        String inputQuantity;
        do {
            System.out.println("Update quantity:");
            inputQuantity = sc.nextLine();
            if (!isNull(inputQuantity))
                try {
                    quantity = Integer.parseInt(inputQuantity);
                    if (!isQuantity(quantity)) {
                        System.out.println("Error: Quantity must be a positive integer!");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Quantity must be a positive number!");
                    return false;
                }
        } while (!isNull(inputQuantity) && !isQuantity(quantity));

        do {
            System.out.println("Update category ID:");
            cId = sc.nextLine();
            if (!isNull(cId))
                if (getCategoryPos(cId)==-1)
                    System.out.println("Error: ID not existed!");
        } while (!isNull(cId) && getCategoryPos(cId)==-1);

        try {
            if (!isNull(name)) this.get(pos).setName(name);
            if (!isNull(inputPrice)) this.get(pos).setPrice(price);
            if (!isNull(inputQuantity)) this.get(pos).setQuantity(quantity);
            if (!isNull(cId)) this.get(pos).setcId(cId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public boolean deleteProduct() {
        Scanner sc = new Scanner(System.in);
        String id;
        int pos;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        do {
            System.out.println("Enter product ID to delete:");
            id = sc.nextLine();
            pos = this.searchID(id);
            if (pos==-1)
                System.out.println("Error: ID not existed!");
        } while (pos==-1);

        System.out.println("Confirm delete? [Y/n]");
        if (sc.nextLine().equals("Y")) this.remove(pos);
        else {
            return false;          //do nothing
        }

        return true;
    }
    /*UTIL*/
    public void displayProduct(){
        //print header
        System.out.println("Product listing:");
        System.out.format("%5s %5s %20s %15s %10s\n",
                "No", "|", "Product name", "|", "Price");
        System.out.println("---------------------------------------------------------------");
        //print invoice
        int count = 1;
        for (Product product: this) {
            System.out.format("%5d %5s %20s %15s %10.2f\n",
                    count, "|", product.name, "|", product.price);
            count++;
        }
    }
    /*END OF UTIL*/
}
