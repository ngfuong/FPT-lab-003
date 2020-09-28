package lab001;

public class Grade {
    Student st;
    Subject sj;
    double lab, test, FE;

    public Grade() {
    }

    public Grade(Student st, Subject sj, double lab, double test, double FE) {
        this.st = st;
        this.sj = sj;
        this.lab = lab;
        this.test = test;
        this.FE = FE;
    }

    public Student getSt() {
        return this.st;
    }

    public void setSt(Student st) {
        this.st = st;
    }

    public Subject getSj() {
        return this.sj;
    }

    public void setSj(Subject sj) {
        this.sj = sj;
    }

    public double getLab() {
        return this.lab;
    }

    public void setLab(double lab) {
        this.lab = lab;
    }

    public double getTest() {
        return this.test;
    }

    public void setTest(double test) {
        this.test = test;
    }

    public double getFE() {
        return this.FE;
    }

    public void setFE(double FE) {
        this.FE = FE;
    }

    @Override
    public String toString() {
        return "{" +
            " st='" + getSt() + "'" +
            ", sj='" + getSj() + "'" +
            ", lab='" + getLab() + "'" +
            ", test='" + getTest() + "'" +
            ", FE='" + getFE() + "'" +
            "}";
    }
    
    public double average() {
        return 0.3*this.lab+0.2*this.test+0.5*this.FE;
    }
}
