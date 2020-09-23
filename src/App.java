public class App {

    public static void main(String args[]) {
        UserList userList = new UserList();

        MenuList mainList = new MenuList();

        Menu addUser = new Menu();
        Menu checkExist = new Menu();
        Menu searchName = new Menu();
        Menu updateUser = new Menu();
        Menu writeFile = new Menu();
        Menu printFile = new Menu();

        mainList.add(addUser);
        addUser.setName("Add new user");

        mainList.add(checkExist);
        checkExist.setName("Check user existence");

        mainList.add(searchName);
        searchName.setName("Search user info by name");

        mainList.add(updateUser);
        updateUser.setName("Update user");
        updateUser.add("Update user");
        updateUser.add("Delete user");

        mainList.add(writeFile);
        writeFile.setName("Save user list to file");

        mainList.add(printFile);
        printFile.setName("Print user list from file");

        /**BEGIN MAIN MENU*/
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
                        userList.printStatus(userList.writeFile());
                        break;
                    case 6:
                        //Print list out
                        userList.printStatus(userList.printFile());
                        break;
                    default:
                        System.out.println("Exiting console...");
                }
            }

        } while (listChoice <= mainList.size() && listChoice >= 0);
        /**END OF MAIN MENU*/

    }
}

