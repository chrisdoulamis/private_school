
package private_sch_try;

import java.util.ArrayList;

/**
 *
 * @author CHRIS
 */
public class Assignments_Per_Student_Per_Course {
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> students;
    private Course course;
    
    public Assignments_Per_Student_Per_Course(){
        
    }

    public Assignments_Per_Student_Per_Course(ArrayList<Assignment> assignments, ArrayList<Student> students, Course course) {
        this.assignments = assignments;
        this.students = students;
        this.course = course;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Assignments_Per_Student_Per_Course{" + "assignments=" + assignments + ", students=" + students + ", course=" + course + '}';
    }
    
}
