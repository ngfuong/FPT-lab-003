import java.util.Comparator;
public class SortByFirstName implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        if (user1.fName.compareTo(user2.fName) > 0) return 1;
        if (user1.fName.compareTo(user2.fName) < 0) return -1;
        return 0;
    }
}
