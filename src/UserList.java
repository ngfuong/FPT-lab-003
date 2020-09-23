import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

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
        else password = confirm = encrypt(password);

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

        this.add(new User(username, fName, lName, password, confirm, phone, email));

        return true;
    }

    //check exist
    public boolean checkExist() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username to check: ");
        String username = sc.nextLine();

        if (!checkNull(username)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        return checkExist(username);
    }

    //search information by name
    //output list of user info
    public boolean searchName() {
        Scanner sc = new Scanner(System.in);
        String name;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        System.out.println("Enter name to search: ");
        name = sc.nextLine();
        if (!checkNull(name)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        ArrayList<User> list = new ArrayList<>();

        for (int i=0;i<this.size();i++) {
            String fullName = this.get(i).getfName() + " " + this.get(i).getlName();
            if (fullName.contains(name)) list.add(this.get(i));
        }
        //sort by first name
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                if (user1.fName.compareTo(user2.fName)>0) return 1;
                if (user1.fName.compareTo(user2.fName)<0) return -1;
                return 0;
            }
        });

        if (list.isEmpty()) {
            System.out.println("No user found!");
            return false;
        }
        else {
            System.out.println("User information: ");
            for (User user: this)
                user.toString();
        }

        return true;
    }

    //update user - update
    public boolean updateUser() {
        Scanner sc = new Scanner(System.in);
        String fName, lName, password, confirm, phone, email;

        int pos = login();
        if (pos==-1) {
            System.out.println("Error: Login failed!");
            return false;
        }

        System.out.println("Update first name: ");
        fName = sc.nextLine();

        System.out.println("Update last name: ");
        lName = sc.nextLine();

        System.out.println("Update password: ");
        password = sc.nextLine();
        //update password
        if (checkNull(password)) {
            if (!checkPassword(password)) {
                System.out.println("Error: Password must have over characters and contain no space!");
                return false;
            }

            System.out.println("Confirm new password: ");
            confirm = sc.nextLine();
            if (!confirm.equals(password)) {
                System.out.println("Error: Password does not match!");
                return false;
            }
            else password = encrypt(password);
        }

        System.out.println("Update phone: ");
        phone = sc.nextLine();
        //update phone
        if (checkNull(phone)&&checkPhone(phone)) {
            System.out.println("Error: Invalid number!");
            return false;
        }

        System.out.println("Update email: ");
        email = sc.nextLine();
        //update email
        if (checkNull(email)&&checkEmail(email)) {
            System.out.println("Error: Invalid email!");
            return false;
        }

        //update
        if (checkNull(fName)) this.get(pos).setfName(fName);
        if (checkNull(lName)) this.get(pos).setlName(lName);
        if (checkNull(password)) {
            this.get(pos).setPassword(password);
            this.get(pos).setConfirm(password);
        }
        if (checkNull(phone)) this.get(pos).setPhone(phone);
        if (checkNull(email)) this.get(pos).setEmail(email);
        return true;
    }

    //update user - delete
    public boolean deleteUser() {
        Scanner sc = new Scanner(System.in);

        int pos = login();
        if (pos==-1) {
            System.out.println("Error: Login failed!");
            return false;
        }

        System.out.println("Confirm delete? [Y/n]");
        if (sc.nextLine()=="Y") this.remove(pos);
        else return false;          //do nothing

        return true;
    }

    //write list to file
    public boolean writeFile() {
        String path = "..\\user.txt";

        try {
            FileWriter writer = new FileWriter(path);

            int i=0;
            while (!this.isEmpty()) {
                //write username only
                writer.write(this.get(i++).getUsername());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //print list in file
    public boolean printFile() {
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
            return false;
        }
        return true;
    }
    /**END OF OPTIONS*/
}

