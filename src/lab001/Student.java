package lab001;

import java.util.Date;
import java.lang.String;
//import java.util.Objects;

public class Student {
    String stID, firstName, lastName, gender;
    Date DOB;
    String email, phone;
    boolean canDelete=true;

    public Student() {
    }

    public Student(String stID, String firstName, String lastName, String gender, Date DOB, String email, String phone) {
        this.stID = stID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.DOB = DOB;
        this.email = email;
        this.phone = phone;
    }

    public String getStID() {
        return this.stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return this.DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCanDelete() {
        return this.canDelete;
    }

    public boolean getCanDelete() {
        return this.canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public String toString() {
        return "{" +
            " stID='" + getStID() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", DOB='" + getDOB() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", canDelete='" + isCanDelete() + "'" +
            "}";
    }

    
}
