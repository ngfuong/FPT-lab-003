import java.util.Objects;

public class User {
    String username, fName, lName, password, confirm, phone, email;

    public User() {
    }

    public User(String username, String fName, String lName, String password,
                String confirm, String phone, String email) {
        this.username = username;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.confirm = confirm;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username:'" + username + '\'' +
                ",fName:'" + fName + '\'' +
                ",lName:'" + lName + '\'' +
                ",password:'" + password + '\'' +
                ",phone:'" + phone + '\'' +
                ",email:'" + email + '\'' +
                '}';
    }
}
