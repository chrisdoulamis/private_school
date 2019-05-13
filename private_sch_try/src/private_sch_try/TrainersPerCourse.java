
package private_sch_try;

import java.util.ArrayList;



public class TrainersPerCourse {
    
    public Course course;
    public ArrayList<Trainer> trainers;
    
    public TrainersPerCourse(){
    
    }

    public TrainersPerCourse(Course course, ArrayList<Trainer> trainers) {
        this.course = course;
        this.trainers = trainers;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }

    @Override
    public String toString() {
        return "TrainersPerCourse{" + "course=" + course + ", trainers=" + trainers + '}';
    }
    
}
