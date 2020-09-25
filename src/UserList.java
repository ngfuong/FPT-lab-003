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
    private boolean isUsername(String s) {
        return (s.length()>=5 && !s.contains(" "));
    }
    private boolean isPassword(String s) {
        return (!s.contains(" ") && s.length()>=6);
    }
    private boolean isPhone(String s) {
        return s.matches("^[0]\\d{9}$");
    }
    private boolean isEmail(String s) {
        return s.matches("^\\S+[@]\\S+[.]\\S+$");
    }
    /*END OF DATA VALIDATION*/

    /*BEGIN OF UTILITY*/
    //user login
    public int login() {
        Scanner sc = new Scanner(System.in);
        String username, password;
        int pos;

        System.out.println("Logging in...");

        do {
            System.out.println("Enter username: ");
            username = sc.nextLine();
            //if (isNull(username)) System.out.println("Error: Input cannot be null!");
            if (checkExist(username)==0) {
                System.out.println("Error: Read file failed!!");
                return -1;
            }
            else if (checkExist(username)==-1) {
                System.out.println("Error: File empty!");
                return -1;
            }
            else if (checkExist(username)==-2)
                System.out.println("Error: User name not exist!");

        } while (/*isNull(username) |*/ checkExist(username)==-2);

        do {
            System.out.println("Enter password: ");
            password = sc.nextLine();
            pos = search(username);
            if (!encrypt(password).equals(this.get(pos).getPassword()))
                System.out.println("Error: Wrong password!");
        } while (!encrypt(password).equals(this.get(pos).getPassword()));

        System.out.println("Logged in successfully!");
        return pos;
    }
    //check exist with argument
    private int checkExist(String username) {

        try {
            File f = new File(PATH);
            if (f.length()==0) {
                //file empty
                return -1;
            }
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                //exist
                if (line.contains(username)) return 1;
            }
            //not exist
            return -2;
        }
        catch (Exception e) {
            e.printStackTrace();
            //read failed
            return 0;
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
        if (status) System.out.println("Operation: Successful!");
        else System.out.println("Operation: Failed!");
    }
    /*END OF UTILITY*/

    /*BEGIN OF OPTIONS*/
    //add new user
    public boolean addUser() {
        String username, fName, lName, password, confirm, phone, email;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter new username:");
            username = sc.nextLine();
            if (!isUsername(username))
                System.out.println("Error: Username must have at least 5 characters and no spaces");
            if (search(username) != -1)
                System.out.println("Error: Username existed!");
        } while (search(username)!=-1 | !isUsername(username));

        do {
            System.out.println("Enter first name:");
            fName = sc.nextLine();
            if (isNull(fName))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(fName));

        do {
            System.out.println("Enter last name:");
            lName = sc.nextLine();
            if (isNull(lName))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(lName));

        do {
            System.out.println("Enter password:");
            password = sc.nextLine();
            if (!isPassword(password))
                System.out.println("Error: Password must have at least 6 characters and no spaces!");
        } while (!isPassword(password));

        do {
            System.out.println("Confirm password: ");
            confirm = sc.nextLine();

            if (!password.equals(confirm)) System.out.println("Error: Password does not match!");
            else password = confirm = encrypt(password);

        } while (!password.equals(confirm));

        do {
            System.out.println("Enter phone: ");
            phone = sc.nextLine();
            if (!isPhone(phone))
                System.out.println("Error: Invalid number!");
        } while (!isPhone(phone));

        do {
            System.out.println("Enter email: ");
            email = sc.nextLine();
            if (!isEmail(email))
                System.out.println("Error: Invalid email!");
        } while (!isEmail(email));

        try {
            this.add(new User(username, fName, lName, password, confirm, phone, email));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //check exist
    public boolean checkExist() {
        Scanner sc = new Scanner(System.in);
        String username;

        do {
            System.out.println("Enter username to check: ");
            username = sc.nextLine();
            if (isNull(username))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(username));

        return (checkExist(username)==1);
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

        do {
            System.out.println("Enter name to search: ");
            name = sc.nextLine();
            if (isNull(name))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(name));

        ArrayList<User> list = new ArrayList<>();

        for (int i=0;i<this.size();i++) {
            String fullName = this.get(i).getfName() + " " + this.get(i).getlName();
            if (fullName.contains(name)) list.add(this.get(i));
        }
        //sort by first name
        Collections.sort(list, new SortByFirstName());

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

        /*
        if (this.isEmpty()) {
            return false;
        }
        */

        int pos = login();
        if (pos==-1) {
            return false;
        }

        System.out.println("Update first name: ");
        fName = sc.nextLine();

        System.out.println("Update last name: ");
        lName = sc.nextLine();

        do {
            System.out.println("Update password: ");
            password = sc.nextLine();
            //update password
            if (!isNull(password))
                if (!isPassword(password))
                    System.out.println("Error: Password must have over characters and contain no space!");
                else {
                     do {
                         System.out.println("Confirm new password: ");
                         confirm = sc.nextLine();
                         if (!confirm.equals(password)) System.out.println("Error: Password does not match!");
                     } while (!confirm.equals(password));

                     password = encrypt(password);
                }

        } while (!isNull(password) && !isPassword(password));

        do {
            System.out.println("Update phone: ");
            phone = sc.nextLine();
            //update phone
            if (!isNull(phone) && !isPhone(phone))
                System.out.println("Error: Invalid number!");
        } while (!isNull(phone) && !isPhone(phone));

        do {
            System.out.println("Update email: ");
            email = sc.nextLine();
            //update email
            if (!isNull(email) && !isEmail(email)) System.out.println("Error: Invalid email!");
        } while (!isNull(email) && !isEmail(email));

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

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        int pos = login();
        if (pos==-1) {
            System.out.println("Error: Login failed!");
            return false;
        }

        System.out.println("Confirm delete? [Y/n]");
        if (sc.nextLine().equals("Y")) this.remove(pos);
        else {
            System.out.println("Delete user: Terminated!");
            return false;          //do nothing
        }

        return true;
    }
    //write list to file
    //****************UPDATE THIS
    public boolean writeFile() {
        try {
            File f = new File(PATH);
            if (!f.exists()) f.createNewFile();

            //append == true
            FileWriter writer = new FileWriter(f.getName(), true);
            for (User user : this) writer.write(user.toString()+"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Write file failed!");
            return false;
        }
        return true;
    }
    //print list in file
    //note: no add to collection && sort
    public boolean printFile() {
        try {
            File f = new File(PATH);
            if (!f.exists()) return false;

            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String delim = "[{}:',]+";
                String username = line.split(delim)[2];
                String fName = line.split(delim)[4];
                String lName = line.split(delim)[6];
                String password = line.split(delim)[8];
                String confirm = password;
                String phone = line.split(delim)[10];
                String email = line.split(delim)[12];

                //add user to current session's list
                int pos = this.search(username);
                if (pos==-1){
                    this.add(new User(username, fName, lName, password, confirm, phone, email));
                    System.out.println("User " + username+ " added!");
                }
            }
            reader.close();

            //printing
            System.out.println("Printing all...");
            Collections.sort(this, new SortByFirstName());
            for (User user: this)
                System.out.println(user.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Printing file failed!");
            return false;
        }
        return true;
    }
    /*END OF OPTIONS*/
}

