
package private_sch_try;

import java.util.ArrayList;

/**
 *
 * @author CHRIS
 */
public class AssignmentsPerStudentPerCourse {
    private ArrayList<Assignment> ass;
    private ArrayList<Student> st;
    private ArrayList<Course> c;
    private ArrayList<Integer> id;
    
    public AssignmentsPerStudentPerCourse(){
    
    }

    public AssignmentsPerStudentPerCourse(ArrayList<Assignment> ass, ArrayList<Student> st, ArrayList<Course> c, ArrayList<Integer> id) {
        this.ass = ass;
        this.st = st;
        this.c = c;
        this.id = id;
    }

    public ArrayList<Assignment> getAss() {
        return ass;
    }

    public void setAss(ArrayList<Assignment> ass) {
        this.ass = ass;
    }

    public ArrayList<Student> getSt() {
        return st;
    }

    public void setSt(ArrayList<Student> st) {
        this.st = st;
    }

    public ArrayList<Course> getC() {
        return c;
    }

    public void setC(ArrayList<Course> c) {
        this.c = c;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return "AssignmentsPerStudentPerCourse{" + "ass=" + ass + ", st=" + st + ", c=" + c + ", id=" + id + '}';
//    }

    
    
    
    
    
    
}
