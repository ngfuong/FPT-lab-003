package lab001;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

public class StudentList extends ArrayList<Student> {

    final String DATE_FORMAT = "dd/MM/yyyy";

    /*BEGIN OF CHECK VALIDITY*/
    public int search(String stID) {
        for (int i = 0; i < this.size(); i++)
            if (stID.compareTo(this.get(i).getStID()) == 0)
                return i;
        return -1;
    }
    private boolean isNull(String s) {
        return (s.length()==0);
    }
    private boolean isGender(String gender) {
        return (gender.equalsIgnoreCase("female") | gender.equalsIgnoreCase("male")
                | gender.equalsIgnoreCase("others"));
    }
    private boolean isDate(String DOB) {
        if (DOB.length()<=0) return false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            sdf.parse(DOB);
        }
        catch (ParseException e) {
            return false;
        }

        return true;
    }
    private boolean isEmail(String email) {
        return email.matches("^\\S+[@]\\S+[.]\\S+$");
    }
    private boolean isPhone(String phone) {
        return phone.matches("^[0]\\d{9,11}$");
    }
    /*END OF CHECK VALIDITY*/

    /*BEGIN OF OPTIONS*/
    public boolean addStudent() {
        Scanner sc = new Scanner(System.in);
        String stID, firstName, lastName, gender;
        String DOB;
        String email, phone;

        do {
            System.out.println("Enter new student ID: ");
            stID = sc.nextLine();

            if (isNull(stID)) System.out.println("Error: Input cannot be null!");
            if (search(stID) != -1) System.out.println("Error: ID existed!");
        } while (isNull(stID) | search(stID)!=-1);

        do {
            System.out.println("Enter first name: ");
            firstName = sc.nextLine();
            if (isNull(firstName))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(firstName));

        do {
            System.out.println("Enter last name: ");
            lastName = sc.nextLine();
            if (isNull(lastName))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(lastName));

        do {
            System.out.println("Enter gender: ");
            gender = sc.nextLine();
            if (!isGender(gender))
                System.out.println("Error: Gender must be in {female, male, others} only!");
        } while (!isGender(gender));

        do {
            System.out.println("Enter day of birth (dd/MM/yyyy): ");
            DOB = sc.nextLine();
            if (!isDate(DOB))
                System.out.println("Error: Invalid date!");
        } while (!isDate(DOB));

        do {
            System.out.println("Enter email: ");
            email = sc.nextLine();
            if (!isEmail(email))
                System.out.println("Error: Invalid address!");
        } while (!isEmail(email));

        do {
            System.out.println("Enter phone number: ");
            phone = sc.nextLine();
            if (!isPhone(phone))
                System.out.println("Error: Invalid number!");
        } while (!isPhone(phone));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = sdf.parse(DOB);
            this.add(new Student(stID, firstName, lastName, gender, date, email, phone));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateStudent() {
        Scanner sc = new Scanner(System.in);
        String stID, firstName, lastName, gender;
        String DOB;
        Date date;
        String email, phone;

        if (this.isEmpty()) {
            System.out.println("List empty!");
            return false;
        }

        int pos;

        do {
            System.out.println("Enter student ID to update:");
            stID = sc.nextLine();
            pos = this.search(stID);
            if (pos == -1)
                System.out.println("Error: ID not existed!");
        } while (pos==-1);


            System.out.println("Update first name: ");
            firstName = sc.nextLine();

            System.out.println("Update last name: ");
            lastName = sc.nextLine();

            System.out.println("Update gender: ");
            gender = sc.nextLine();

        do {
            System.out.println("Update day of birth (MM/dd/yyyy): ");
            DOB = sc.nextLine();
            if (!isNull(DOB))
                if (!isDate(DOB))
                    System.out.println("Error: Invalid date!");
        } while (!isNull(DOB) && !isDate(DOB));

        do {
            System.out.println("Update email: ");
            email = sc.nextLine();
            if (!isNull(email))
                if (!isEmail(email))
                    System.out.println("Error: Invalid email!");
        } while (!isNull(email) && !isEmail(email));

        do {
            System.out.println("Update phone number: ");
            phone = sc.nextLine();
            if (!isNull(phone))
                if (!this.isPhone(phone))
                    System.out.println("Error: Invalid number!");
        } while (!isNull(phone) && !isPhone(phone));

        try {
            if (!isNull(firstName)) this.get(pos).setFirstName(firstName);
            if (!isNull(lastName)) this.get(pos).setLastName(lastName);
            if (!isNull(gender)) this.get(pos).setGender(gender);
            if (!isNull(DOB)) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                date = sdf.parse(DOB);
                this.get(pos).setDOB(date);
            }
            if (!isNull(email)) this.get(pos).setEmail(email);
            if (!isNull(phone)) this.get(pos).setPhone(phone);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStudent() {
        Scanner sc = new Scanner(System.in);
        String stID;
        int pos;

        if(isEmpty()) {
            System.out.println("List empty!");
            return false;
        }

        do {
            System.out.println("Enter student ID to delete: ");
            stID = sc.nextLine();
            pos = search(stID);
            if (pos == -1)
                System.out.println("Error: ID does not exist!");
        } while (pos==-1);

        if (this.get(pos).canDelete) {
            System.out.println("Confirm delete? (Y/[n])");
            String choice = sc.nextLine();

            if (choice.equals("Y")) {
                this.remove(pos);
                return true;
            }
            else {
                System.out.println("Delete student: Terminated!");
                return false;
            }
        }
        else {
            System.out.println("Error: Student cannot be deleted!");
            return false;
        }

    }
    /*END OF OPTIONS*/
}