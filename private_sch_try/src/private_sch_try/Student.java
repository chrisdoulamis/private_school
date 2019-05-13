package private_sch_try;

import java.util.Date;
import java.util.Objects;

public class Student {
    private String fname;
    private String lname;
    private String birthday;
    private int tuitions;
    
    public Student(){
    
    }

    public Student(String fname, String lname, String birthday, int tuitions) {
        this.fname = fname;
        this.lname = lname;
        this.birthday = birthday;
        this.tuitions = tuitions;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getTuitions() {
        return tuitions;
    }

    public void setTuitions(int tuitions) {
        this.tuitions = tuitions;
    }

    @Override
    public String toString() {
        return "Student{" + "fname=" + fname + ", lname=" + lname + ", birthday=" + birthday + ", tuitions=" + tuitions + '}';
    }
    
}
