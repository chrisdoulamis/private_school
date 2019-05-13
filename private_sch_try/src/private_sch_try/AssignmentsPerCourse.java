
package private_sch_try;

import java.util.ArrayList;

public class AssignmentsPerCourse {
    
    public Course course;
    public ArrayList<Assignment> assignments;
    
    public AssignmentsPerCourse(){
    
    }

    public AssignmentsPerCourse(Course course, ArrayList<Assignment> assignments) {
        this.course = course;
        this.assignments = assignments;
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "AssignmentsPerCourse{" + "course=" + course + ", assignments=" + assignments + '}';
    }
    
}
