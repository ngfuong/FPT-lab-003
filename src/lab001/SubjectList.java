package lab001;

import java.text.ParseException;
import java.util.*;

public class SubjectList extends ArrayList<Subject> {

    /* BEGIN OF CHECK VALIDITY */
    public int search(String sjID) {
        for (int i = 0; i < this.size(); i++)
            if (sjID.compareTo(this.get(i).getSjID()) == 0)
                return i;

        return -1;
    }
    private boolean isNull(String s) {
        return (s.length()==0);
    }
    private boolean isCredit(int credit) {
        return (credit>=0 && credit <=30);
    }
    /* END OF CHECK VALIDITY*/

    /*BEGIN OF OPTIONS*/
    public boolean addSubject() {
        Scanner sc = new Scanner(System.in);
        String sjID, sjName;
        String input;           //input for credit
        int credit=0;

        do {
            System.out.println("Enter new subject ID: ");
            sjID = sc.nextLine();
            if (search(sjID) != -1)
                System.out.println("Error: ID existed!");
        } while (search(sjID)!=-1);

        do {
            System.out.println("Enter subject name: ");
            sjName = sc.nextLine();
            if (isNull(sjName))
                System.out.println("Error: Input cannot be null!");
        } while (isNull(sjName));

        do {
            System.out.println("Enter credit: ");
            input = sc.nextLine();
            if (isNull(input))
                System.out.println("Error: Input cannot be null!");
            else {
                try {
                    credit = Integer.parseInt(input);
                    if (!isCredit(credit))
                        System.out.println("Error: Credit out of bounds!");
                } catch (Exception e) {
                    System.out.println("Error: Data type conflict!");
                    return false;
                }
            }
        } while (isNull(input) | !isCredit(credit));

        try {
            this.add(new Subject(sjID, sjName, credit));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean updateSubject() {
        Scanner sc = new Scanner(System.in);
        String sjID, sjName;
        String input;
        int credit=0;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        int pos;

        do {
            System.out.println("Enter subject ID to update:");
            sjID = sc.nextLine();
            pos = this.search(sjID);
            if (pos == -1)
                System.out.println("Error: ID not existed!");

        } while (pos==-1);

        System.out.println("Update subject name: ");
        sjName = sc.nextLine();

        do {
            System.out.println("Update credit: ");
            input = sc.nextLine();
            if (!isNull(input))
                try {
                    credit = Integer.parseInt(input);
                    if (!isCredit(credit))
                        System.out.println("Error: Credit out of bounds!");
                } catch (Exception e) {
                    System.out.println("Error: Data type conflict!");
                    return false;
                }

        } while (!isNull(input) && !isCredit(credit));

        try {
            if (!isNull(sjName)) this.get(pos).setSjName(sjName);
            if (!isNull(input)) this.get(pos).setCredit(credit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean deleteSubject() {
        Scanner sc = new Scanner(System.in);
        String sjID;
        int pos;

        if (this.isEmpty()) {
            System.out.println("Error: List empty!");
            return false;
        }

        do {
            System.out.println("Enter subject ID to delete: ");
            sjID = sc.nextLine();
            pos = this.search(sjID);
            if (pos == -1) System.out.println("Error: ID does not exist!");
        } while (pos==-1);

        if (this.get(pos).canDelete) {
            System.out.println("Confirm delete? (Y/[n])");
            String choice = sc.nextLine();

            if (choice.equals("Y")) {
                this.remove(pos);
                return true;
            }
            else {
                System.out.println("Delete subject: Terminated!");
                return false;
            }
        }
        else {
            System.out.println("Error: Subject cannot be deleted!");
            return false;
        }
    }
    /*END OF OPTIONS*/

}
