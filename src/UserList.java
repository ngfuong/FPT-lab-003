import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class UserList extends ArrayList<User> {
    final String PATH = System.getProperty("user.dir") + "/user.txt";

    public UserList() {
    }

    /*BEGIN OF DATA VALIDATION*/
    //search username in list
    private int search(String username) {
        for (int i=0; i<this.size();i++)
            if (this.get(i).getUsername().equals(username))
                return i;

        return -1;
    }
    private boolean isNull(String s){
        return (s.length()==0);
    }
    private boolean isPassword(String s) {
        return !(s.matches("\\s") && (s.length()<6));
    }
    private boolean isPhone(String s) {
        return s.matches("^[0]\\d{9}$");
    }
    private boolean isEmail(String s) {
        return s.matches("^\\S+[@]?\\S+[.]?\\S+$");
    }
    /*END OF DATA VALIDATION*/

    /*BEGIN OF UTILITY*/
    //user login
    public int login() {
        Scanner sc = new Scanner(System.in);
        String username, password;

        System.out.println("Logging in...");
        System.out.println("Enter username: ");
        username = sc.nextLine();
        if(isNull(username)) {
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

        try {
            File f = new File(PATH);
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
            //encode
            return  Base64.getEncoder().encodeToString(hash);

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
    /*END OF UTILITY*/

    /*BEGIN OF OPTIONS*/
    //add new user
    public boolean addUser() {
        String username, fName, lName, password, confirm, phone, email;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new username:");
        username = sc.nextLine();
        if (this.search(username)!=-1) {
            System.out.println("Error: Username existed!");
            return false;
        }

        System.out.println("Enter first name:");
        fName = sc.nextLine();
        if (isNull(fName)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        System.out.println("Enter last name:");
        lName = sc.nextLine();
        if (isNull(lName)) {
            System.out.println("Error: Input cannot be null!");
            return false;
        }

        System.out.println("Enter password:");
        password = sc.nextLine();
        if (!isPassword(password)) {
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
        if (!isPhone(phone)) {
            System.out.println("Error: Invalid number!");
            return false;
        }

        System.out.println("Enter email: ");
        email = sc.nextLine();
        if (!isEmail(email)) {
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

        if (isNull(username)) {
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
        if (isNull(name)) {
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
            for (User user: list)
                System.out.println(user.toString());
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
        if (!isNull(password)) {
            if (!isPassword(password)) {
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
        if (!isNull(phone) && !isPhone(phone)) {
            System.out.println("Error: Invalid number!");
            return false;
        }

        System.out.println("Update email: ");
        email = sc.nextLine();
        //update email
        if (!isNull(email) && !isEmail(email)) {
            System.out.println("Error: Invalid email!");
            return false;
        }

        //update
        if (!isNull(fName)) this.get(pos).setfName(fName);
        if (!isNull(lName)) this.get(pos).setlName(lName);
        if (!isNull(password)) {
            this.get(pos).setPassword(password);
            this.get(pos).setConfirm(password);
        }
        if (!isNull(phone)) this.get(pos).setPhone(phone);
        if (!isNull(email)) this.get(pos).setEmail(email);
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
        if (sc.nextLine().equals("Y")) this.remove(pos);
        else return false;          //do nothing

        return true;
    }

    //write list to file
    public boolean writeFile() {

        try {
            FileWriter writer = new FileWriter(PATH);

            for (User user : this) writer.write(user.getUsername());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //print list in file
    //note: no sort
    public boolean printFile() {

        try {
            File f = new File(PATH);
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
    /*END OF OPTIONS*/
}

