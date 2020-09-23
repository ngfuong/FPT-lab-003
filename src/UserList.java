import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.util.ArrayList;

public class UserList extends ArrayList<User> {
    public UserList() {
    }

    /**BEGIN DATA VALIDATION*/
    //search username in list
    private int search(String username) {
        for (int i=0; i<this.size();i++)
            if (this.get(i).getUsername().equals(username))
                return i;

        return -1;
    }

    private boolean checkNull(String s){
        if (s.length()==0)
            return false;
        return true;
    }

    private boolean checkPassword(String s) {
        if (s.length()<6) return false;
        if (s.matches("\\s")) return false;
        return true;
    }

    private boolean checkPhone(String s) {
        if (s.length()!=10) return false;
        if (!s.matches("^[0]\\d+$]")) return false;
        return true;
    }

    private boolean checkEmail(String s) {
        if (!s.matches("^\\S+[@]?\\S+[.]?\\S+$"))
            return false;
        return true;
    }
    /**END OF DATA VALIDATION*/


    /**BEGIN UTILITY*/
    //user login
    public int login() {
        Scanner sc = new Scanner(System.in);
        String username, password;

        System.out.println("Enter username: ");
        username = sc.nextLine();
        if(!checkNull(username)) {
            System.out.println("Error: Input cannot be null!");
            return -1;
        }

        if (!checkExist(username)) return -1;

        System.out.println("Enter password: ");
        password = sc.nextLine();

        int pos = search(username);
        if (!password.equals(this.get(pos).getPassword())) {
            System.out.println("Error: Wrong password!");
            return -1;
        }
        else {
            System.out.println("Logged in successfully!");
        }
        return pos;
    }

    //check exist with argument
    private boolean checkExist(String username) {
        String path = "..\\user.txt";

        try {
            File f = new File(path);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equals(username)) {
                    System.out.println("User exist!");
                    return true;
                }
            }
            System.out.println("Error: No user found!");
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Read file failed!");
            return false;
        }
    }

    //encrypt password
    public String encrypt(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);
            return encoded;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Encryption failed!");
            return null;
        }
    }

    //print operation status
    public void printStatus (boolean status) {
        if (status)
            System.out.println("Operation: Successful!");
        else System.out.println("Operation: Failed!");
    }
    /**END OF UTILITY*/


    /**BEGIN OPTIONS*/
    //add new user
    public boolean addUser() {
        String username, fName, lName, password, confirm, phone, email;
        boolean operation = true;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new username:");
        username = sc.nextLine();
        if (this.search(username)!=-1) {
            System.out.println("Error: Username existed!");
            return false;
        }


        System.out.print("Enter first name:");
        fName = sc.nextLine();
        if (!checkNull(fName)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        System.out.println("Enter last name:");
        lName = sc.nextLine();
        if (!checkNull(lName)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        System.out.println("Enter password:");
        password = sc.nextLine();
        if (!checkPassword(password)) {
            System.out.println("Error: Password must be over-6-char long and has no spaces!");
            return false;
        }

        System.out.println("Confirm password: ");
        confirm = sc.nextLine();
        if (!password.equals(confirm)) {
            System.out.println("Error: Password does not match!");
            return false;
        }

        System.out.println("Enter phone: ");
        phone = sc.nextLine();
        if (!checkPhone(phone)) {
            System.out.println("Error: Invalid number!");
            return false;
        }

        System.out.println("Enter email: ");
        email = sc.nextLine();
        if (!checkEmail(email)) {
            System.out.println("Error: Invalid email!");
            return false;
        }

        return operation;
    }

    //check exist
    public boolean checkExist() {
        String username;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username to check: ");
        username = sc.nextLine();

        if (!checkNull(username)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        return checkExist(username);
    }

    //search information by name
    public boolean searchName() {
        Scanner sc = new Scanner(System.in);
        String name;
        boolean operation = true;
        int pos=-1;

        System.out.println("Enter name to search: ");
        name = sc.nextLine();
        if (!checkNull(name)) {
            System.out.println("Error: Input cannot be null!");
            return operation = false;
        }

        for (int i=0;i<this.size();i++) {
            String fullname = this.get(i).getfName() + " "
                                + this.get(i).getlName();
            if (fullname.contains(name)) {
                pos = i;
                break;
            }
        }

        if (pos==-1) {
            System.out.println("No user found!");
            return operation = false;
        }
        else {
            System.out.println("User information: ");
            this.get(pos).toString();
        }
        return operation;
    }

    //update user - update
    public boolean updateUser() {
        Scanner sc = new Scanner(System.in);
        String fName, lName, password, confirm, phone, email;
        boolean operation = true;

        int pos = login();
        if (pos==-1) {
            System.out.println("Error: Login failed!");
            return operation = false;
        }

        System.out.println("Update first name: ");
        fName = sc.nextLine();

        System.out.println("Update last name: ");
        lName = sc.nextLine();

        System.out.println("Update password: ");
        password = sc.nextLine();
        if (!checkPassword(password)) {
            System.out.println("Error: Password must be 6-char long and contains no space!");
            return operation = false;
        }

        if (checkNull(password)) {
            System.out.println("Confirm new password: ");
            confirm = sc.nextLine();
            if (!confirm.equals(password)) {
                System.out.println("Error: Password does not match!");
                return operation = false;
            }
        }

        System.out.println("Update phone: ");
        phone = sc.nextLine();
        if (checkNull(phone)&&checkPhone(phone)) {
            System.out.println("Error: Invalid number!");
            return operation = false;
        }

        System.out.println("Update email: ");
        email = sc.nextLine();
        if (checkNull(email)&&checkEmail(email)) {
            System.out.println("Error: Invalid email!");
            return operation = false;
        }

        if (operation==true) {
            if (checkNull(fName)) this.get(pos).setfName(fName);
            if (checkNull(lName)) this.get(pos).setlName(lName);
            if (checkNull(password)) this.get(pos).setPassword(password);
            if (checkNull(phone)) this.get(pos).setPhone(phone);
            if (checkNull(email)) this.get(pos).setEmail(email);
        }
        return operation;
    }

    //update user - delete
    public boolean deleteUser() {
        Scanner sc = new Scanner(System.in);
        boolean operation = true;
        String username;

        int pos = login();
        if (pos==-1) {
            System.out.println("Error: Login failed!");
            return operation = false;
        }

        System.out.println("Delete username: ");
        username = sc.nextLine();

        if (checkNull(username)) {
            System.out.println("Error: Input cannot be null!");
            return operation = false;
        }

        if (search(username)==-1) {
            System.out.println("Username not found!");
            return operation = false;
        }

        if (operation == true) this.remove(pos);
        return operation;
    }

    //write list to file
    public boolean writeFile() {
        boolean operation = true;
        Scanner sc = new Scanner(System.in);
        String path = "..\\user.txt";
        String line;

        try {
            FileWriter writer = new FileWriter(path);

            int i=0;
            while (!this.isEmpty()) {
                writer.write(this.get(i++).toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return operation = false;
        }

        return operation;
    }

    //print list in file
    public boolean printFile() {
        boolean operation = true;
        String path = "..\\user.txt";

        try {
            File f = new File(path);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            operation = false;
        }
        return operation;
    }
    /**END OF OPTIONS*/
}

