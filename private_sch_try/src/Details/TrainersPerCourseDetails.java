/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.CourseDao;
import dao.TrainerDao;
import dao.TrainersPerCourseDao;
import java.util.Scanner;

/**
 *
 * @author CHRIS
 */
public class TrainersPerCourseDetails {
    
    public void getTrainersPerCourseDetails(Scanner sc) {
        TrainerDao trainer = new TrainerDao();
        CourseDao course = new CourseDao();
        int num_course;
        int [] trainers=new int[trainer.getListOfIdTrainers().size()];
        int sum=1;
        System.out.println("The list of trainers is : ");
        for( int i=0 ; i<trainer.getListOfIdTrainers().size() ; i++ ){
            System.out.println("Trainer " + (i+1) + ") :");
            System.out.println(trainer.getListOfTrainers().get(i));
        }
        for( int i=0 ; i<course.getListOfIdCourses().size() ; i++){
            System.out.println("Course " + (i+1) + ") :");
            System.out.println(course.getListOfCourses().get(i));
        }
        System.out.println("Please give the number of Course : ");
        num_course=sc.nextInt();
        do{
            System.out.println("Please give the " +sum+") trainer to put in the Course");
            trainers[sum-1]=sc.nextInt();
            sum=sum+1;
            System.out.println("If you want to put another trainer pres : yes ");
            System.out.println("Else press any button to logOff");
        }while("yes".equals(sc.next()));
        TrainersPerCourseDao tpc = new TrainersPerCourseDao();
            for(int i: trainers){
                if(i>0){
                    tpc.insertTrainersPerCourse(course.getListOfIdCourses().get(num_course-1), trainer.getListOfIdTrainers().get(i-1));
                }
            }
    }
    
}
