
package private_sch_try;

import java.util.Objects;

public class Trainer {
    private String fname;
    private String lname;
    private String subject;
    
    public Trainer(){
    
    }

    public Trainer(String fname, String lname, String subject) {
        this.fname = fname;
        this.lname = lname;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Trainer { " + "fname=" + fname + ", lname=" + lname + ", subject=" + subject + '}';
    }
   
}
