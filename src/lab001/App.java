package lab001;

public class App {
    public static void main(String[] args) {
        Utility util = new Utility();

        StudentList stList = new StudentList();
        SubjectList sjList = new SubjectList();
        GradeList gList = new GradeList(stList, sjList);

        MenuList mainList = new MenuList();
        
        Menu addStudent = new Menu("Add new student");
        Menu updateStudent = new Menu("Update student");
        updateStudent.add("Update student");
        updateStudent.add("Delete student");
        Menu addSubject = new Menu("Add new subject");
        Menu updateSubject = new Menu("Update subject");
        updateSubject.add("Update subject");
        updateSubject.add("Delete subject");
        Menu enterGrade = new Menu("Enter new grade");
        Menu displayStudentReport = new Menu("Display student report");
        Menu displaySubjectReport = new Menu("Display subject report");

        mainList.add(addStudent);
        mainList.add(updateStudent);
        mainList.add(addSubject);
        mainList.add(updateSubject);
        mainList.add(enterGrade);
        mainList.add(displayStudentReport);
        mainList.add(displaySubjectReport);


        /*BEGIN MAIN MENU */
        int listChoice;
        do {

            listChoice = mainList.getListChoice();

            //if choice has sub-menu
            if (listChoice==2 | listChoice==4) {
                int menuChoice;
                /*BEGIN SUB-MENU */
                do {
                    menuChoice = mainList.get(listChoice-1).getMenuChoice();
                    if (menuChoice<=mainList.get(listChoice-1).size() && menuChoice>=1) {
                        
                        switch (menuChoice) {
                            case 1: {
                                //Updating
                                if (listChoice==2) util.printStatus(stList.updateStudent());
                                else util.printStatus(sjList.updateSubject());
                                break;
                            }
                            case 2: {
                                //Deleting
                                if (listChoice==2) util.printStatus(stList.deleteStudent());
                                else util.printStatus(sjList.deleteSubject());
                                break;
                            }
                            default: System.out.println("Exiting console...");
                        }
                    }
                } while(menuChoice<=mainList.get(listChoice-1).size() && menuChoice>=1);
                /*END SUB-MENU */
            }

            //if choice does not have sub-menu
            else {
                switch(listChoice) {
                    case 1: {
                        util.printStatus(stList.addStudent());
                        break;
                    }
                    case 3: {
                        util.printStatus(sjList.addSubject());
                        break;
                    }
                    case 5: {
                        util.printStatus(gList.addNewGrade());
                        break;

                    }
                    case 6: {
                        util.printStatus(gList.printStudentReport());
                        break;

                    }
                    case 7: {
                        util.printStatus(gList.printSubjectReport());
                        break;

                    }
                    default: System.out.println("Exiting console...");
                }
            }

        } while (listChoice<=mainList.size() && listChoice>=1);
        /*END MAIN-MENU */
    }
}
