package lab003;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CategoryList extends ArrayList<Category> {

    public CategoryList() {
    }

    /*BEGIN OF FILE IO*/
    final String PATH = System.getProperty("user.dir") + "/";
    public void readData() {
        Scanner sc = new Scanner(System.in);
        //CategoryList list = new CategoryList();

        System.out.println("Enter category file name: ");
        String fName = sc.nextLine();
        fName = (fName.length()==0)? "category.txt" : fName;

        try {
            File f = new File(PATH + fName);
            if (!f.exists()) f.createNewFile();

            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String delim = "[{}:',]+";

                String id = line.split(delim)[2];
                String name = line.split(delim)[4];

                this.add(new Category(id, name));
            }
            //return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Read file failed!");
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
    private int searchName(String name){
        for (int i=0;i<this.size();i++)
            if (this.get(i).name.equalsIgnoreCase(name))
                return i;

        return -1;
    }
    private boolean isNull(String s) {
        return s.length()==0;
    }

    /*END OF DATA VALIDATION*/
    public boolean addCategory() {
        String id, name;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter new category ID: ");
            id = sc.nextLine();
            if (isNull(id)) System.out.println("Error: Input cannot be null!");
            if (searchID(id) != -1) System.out.println("Error: ID existed!");
        } while (isNull(id) | searchID(id)!=-1);

        do {
            System.out.println("Enter new category name: ");
            name = sc.nextLine();
            if (isNull(name)) System.out.println("Error: Input cannot be null!");
            if (searchName(id) != -1) System.out.println("Error: Name existed!");
        } while (isNull(name) | searchName(name)!=-1);

        try {
            this.add(new Category(id, name));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCategory() {
        Scanner sc = new Scanner(System.in);
        String id, name;
        int pos=-1;

        if (this.isEmpty()) {
            System.out.println("Error: No category!");
            return false;
        }

        do {
            System.out.println("Enter category ID: ");
            id = sc.nextLine();
            if (isNull(id))
                System.out.println("Error: Input cannot be null!");
            else {
                pos = this.searchID(id);
                if (pos==-1)
                    System.out.println("Error: ID not existed!");
            }
        } while (isNull(id) | pos==-1);

        System.out.println("Update category name:");
        name = sc.nextLine();

        if (!isNull(name)) this.get(pos).setName(name);
        return true;
    }
    public boolean deleteCategory() {
        Scanner sc = new Scanner(System.in);
        String id;
        int pos = -1;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        do {
            System.out.println("Enter category ID to delete:");
            id = sc.nextLine();
            if (isNull(id))
                System.out.println("Error: Input cannot be null!");
            pos = this.searchID(id);
            if (pos==-1)
                System.out.println("Error: ID not exist!");
        } while (isNull(id) | pos==-1);

        System.out.println("Confirm delete? [Y/n]");
        if (sc.nextLine().equals("Y")) {
            this.remove(pos);
            return true;
        }
        else {
            return false;          //do nothing
        }

    }
}
