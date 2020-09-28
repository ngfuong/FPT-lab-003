package lab001;

public class Subject {
    String sjID, sjName;
    int credit;
    boolean canDelete=true;

    public Subject() {
    }

    public Subject(String sjID, String sjName, int credit) {
        this.sjID = sjID;
        this.sjName = sjName;
        this.credit = credit;
    }

    public String getSjID() {
        return this.sjID;
    }

    public void setSjID(String sjID) {
        this.sjID = sjID;
    }

    public String getSjName() {
        return this.sjName;
    }

    public void setSjName(String sjName) {
        this.sjName = sjName;
    }

    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
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
            " sjID='" + getSjID() + "'" +
            ", sjName='" + getSjName() + "'" +
            ", credit='" + getCredit() + "'" +
            ", canDelete='" + isCanDelete() + "'" +
            "}";
    }


}
