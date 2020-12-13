package lab002;

public class App {

    public static void main(String[] argv) {

        UserList userList = new UserList();
        userList = userList.readData();

        MenuList mainList = new MenuList();

        Menu addUser = new Menu("Add new user");
        Menu checkExist = new Menu("Check username existence in file");
        Menu searchName = new Menu("Get information in memory by name");
        Menu updateUser = new Menu("Update user in memory");
        updateUser.add("Update user in memory");
        updateUser.add("Delete user in memory");
        Menu writeFile = new Menu("Save memory to file");
        Menu printFile = new Menu("Print user list in file");

        mainList.add(addUser);
        mainList.add(checkExist);
        mainList.add(searchName);
        mainList.add(updateUser);
        mainList.add(writeFile);
        mainList.add(printFile);

        /*BEGIN MAIN MENU*/
        int listChoice;
        do {
            listChoice = mainList.getListChoice();

            //if sub-menu
            if (listChoice==4) {
                //BEGIN SUB MENU
                int menuChoice;

                do {
                    menuChoice = mainList.get(listChoice-1).getMenuChoice();
                    if (menuChoice <= mainList.get(listChoice-1).size() && menuChoice >= 1)
                        switch (menuChoice) {
                            case 1:
                                //Update user
                                userList.printStatus(userList.updateUser());
                                break;

                            case 2:
                                //Delete user
                                userList.printStatus(userList.deleteUser());
                                break;

                            default:
                                System.out.println("Exiting console...");
                        }

                } while (menuChoice <= mainList.get(listChoice-1).size() && menuChoice >= 1);
                //END OF SUB MENU
            }

            //if no sub-menu
            else {
                switch(listChoice) {
                    case 1:
                        //Add user
                        userList.printStatus(userList.addUser());
                        break;
                    case 2:
                        //Check exist
                        userList.printStatus(userList.checkExist());
                        break;
                    case 3:
                        //Search info by name
                        userList.printStatus(userList.searchName());
                        break;
                    case 5:
                        //Save list to file
                        userList.printStatus(userList.saveData());
                        break;
                    case 6:
                        //Print list out
                        userList.printStatus(userList.printFile());
                        break;
                    default:
                        System.out.println("Exiting console...");
                }
            }

        } while (listChoice <= mainList.size() && listChoice >= 1);
        /*END OF MAIN MENU*/

    }
}

