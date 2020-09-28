package lab001;

import java.util.*;

public class GradeList extends ArrayList<Grade>{
    StudentList stList;
    SubjectList sjList;

    public GradeList(StudentList stList, SubjectList sjList) {
        this.stList = stList;
        this.sjList = sjList;
    }

    /*BEGIN OF VALIDATION*/
    //get student pos via ID
    private int searchStudent(String stID) {
        return stList.search(stID);
    }
    //get subject pos via ID
    private int searchSubject(String sjID) {
        return sjList.search(sjID);
    }
    private int searchGrade(String stID, String sjID) {
        for (int i = 0; i < this.size(); i++)
            if (stID.compareTo(this.get(i).getSt().getStID()) == 0
                    && sjID.compareTo(this.get(i).getSj().getSjID()) == 0)
                return i;
        return -1;
    }
    private boolean isGrade(double num) {
        return (num>=0 && num<=10);
    }
    /*END OF VALIDATION*/

    /*BEGIN OF OPTIONS*/
    public boolean addNewGrade() {
        Scanner sc = new Scanner(System.in);
        String stID, sjID;
        int stPos, sjPos, gPos;

        if (stList.isEmpty() | sjList.isEmpty()) {
            System.out.println("Error: Insufficient students/subjects!");
            return false;
        }

        do {
            System.out.println("Enter student ID: ");
            stID = sc.nextLine();
            stPos = this.searchStudent(stID);
            if (stPos == -1)
                System.out.println("Error: ID not existed!");
        } while (stPos==-1);

        do {
            System.out.println("Enter subject ID: ");
            sjID = sc.nextLine();
            sjPos = this.searchSubject(sjID);
            if (sjPos == -1)
                System.out.println("Error: ID not existed!");
        } while (sjPos==-1);

        gPos = this.searchGrade(stID, sjID);
        //if grade existed
        if (gPos != -1) {
            System.out.println("Error: Grade existed!");
            System.out.println("Overwrite? [Y/n]");

            String choice = sc.nextLine();
            if (choice.equals("Y")) {
                double lab, test, FE;

                do {
                    System.out.println("Enter new lab grade: ");
                    lab = Double.parseDouble(sc.nextLine());
                    if (!isGrade(lab))
                        System.out.println("Error: Grade out of bounds!");
                } while (!isGrade(lab));

                do {
                    System.out.println("Enter new test grade: ");
                    test = Double.parseDouble(sc.nextLine());
                    if (!isGrade(test))
                        System.out.println("Error: Grade out of bounds!");
                } while (!isGrade(test));

                do {
                    System.out.println("Enter new FE grade: ");
                    FE = Double.parseDouble(sc.nextLine());
                    if (!this.isGrade(FE))
                        System.out.println("Error: Grade out of bounds!");
                } while (!isGrade(FE));

                try {
                    this.get(gPos).setLab(lab);
                    this.get(gPos).setTest(test);
                    this.get(gPos).setFE(FE);
                    System.out.println("Overwrite: Successful!");
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Overwrite: Failed!");
                    return false;
                }
            }
            else {
                System.out.println("Overwrite: Terminated!");
                return false;
            }
        }
        //if grade not existed
        else {
            double lab, test, FE;

            do {
                System.out.println("Enter lab grade: ");
                lab = Double.parseDouble(sc.nextLine());
                if (!isGrade(lab))
                    System.out.println("Error: Grade out of bounds!");
            } while (!isGrade(lab));

            do {
                System.out.println("Enter test grade: ");
                test = Double.parseDouble(sc.nextLine());
                if (!isGrade(test))
                    System.out.println("Error: Grade out of bounds!");
            } while (!isGrade(test));

            do {
                System.out.println("Enter FE grade: ");
                FE = Double.parseDouble(sc.nextLine());
                if (!isGrade(FE))
                    System.out.println("Error: Grade out of bounds!");
            } while (!isGrade(FE));

            try {
                this.add(new Grade(stList.get(stPos), sjList.get(sjPos), lab, test, FE));
                stList.get(stPos).setCanDelete(false);
                sjList.get(sjPos).setCanDelete(false);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    }
    public boolean printStudentReport() {
        String stID;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student ID: ");
        stID = sc.nextLine();
        int stPos = stList.search(stID);
        if (stPos==-1) {
            System.out.println("Error: No report supported!");
            return false;
        }

        Student st = stList.get(stPos);
        System.out.println("- Student ID: " + stID);
        System.out.println("- Student name: " + st.getFirstName() +" "+st.getLastName());
        System.out.println("List of subjects sorted by Subject Name");

        //print header
        System.out.format("%5s %5s %25s %15s %10s %5s %10s",
                "No", "|", "Subject Name", "|", "Avg Mark", "|", "Status");
        System.out.println("-----------------------------------------------------------------------------------------");

        //print invoice
        int count = 1;
        for (Grade g:this) {
            if (g.getSt().getStID().equals(stID)) {
                double avg = g.average();
                String status = avg>=5? "Pass\n" : "Fail\n";
                //                 | ++ No ++ | +++++++Subject name ++++++++ | ++ Average mark ++ | ++ Status ++ |
                System.out.format("%5d %5s %25s %15s %10.2f %5s %10s",
                        count, "|", g.getSj().getSjName(), "|", avg, "|", status);
                count++;
            }
        }

        return true;

    }
    public boolean printSubjectReport() {
        String sjID;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter subject ID: ");
        sjID = sc.nextLine();
        int sjPos= this.searchSubject(sjID);
        if (sjPos==-1) {
            System.out.println("Error: No report supported!");
            return false;
        }

        Subject sj = sjList.get(sjPos);
        System.out.println("- Subject ID: " + sjID);
        System.out.println("- Subject name: " + sj.getSjName());
        System.out.println("List of student sorted by Student Name: ");

        //print header
        System.out.format("%15s %5s %25s %15s %10s %5s %10s",
                "Student ID", "|", "Student name", "|", "Avg Mark", "|", "Status");
        System.out.println("-----------------------------------------------------------------------------------------");

        //print invoice
        for (Grade g:this) {
            if (sjID.equals(g.getSj().getSjID())) {
                double avg = g.average();
                String status = avg>=5? "Pass\n" : "Fail\n";
                //                 | ++ No ++ | +++++++ Student name ++++++++ | ++ Average mark ++ | ++ Status ++ |
                //                      5       13                                 46                   65
                System.out.format("%15s %5s %25s %15s %10.2f %5s %10s",
                        g.getSt().getStID(), "|", g.getSt().getFirstName()+" "+g.getSt().getLastName(), "|", avg, "|", status);
                //System.out.println(count + "  " + g.getSj().getSjName()+"   " + avg + " "+status);
            }
        }
        return true;

    }
    /*END OF OPTIONS*/
}
