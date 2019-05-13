/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.AssignmentDao;
import dao.AssignmentsPerCourseDao;
import dao.CourseDao;
import java.util.Scanner;

/**
 *
 * @author CHRIS
 */
public class AssignmentsPerCourseDetails {
    
    public void getAssignmentsPerCourseDetails(Scanner sc) {
        AssignmentDao assignment = new AssignmentDao();
        CourseDao course = new CourseDao();
        int num_course;
        int [] assignments=new int[assignment.getListOfIdAssignments().size()];
        int sum=1;
        System.out.println("The list of assignments is : ");
        for( int i=0 ; i<assignment.getListOfIdAssignments().size() ; i++ ){
            System.out.println("Assignment " + (i+1) + ") :");
            System.out.println(assignment.getListOfAssignments().get(i));
        }
        for( int i=0 ; i<course.getListOfIdCourses().size() ; i++){
            System.out.println("Course " + (i+1) + ") :");
            System.out.println(course.getListOfCourses().get(i));
        }
        System.out.println("Please give the number of Course : ");
        num_course=sc.nextInt();
        do{
            System.out.println("Please give the " +sum+") assignment to put in the Course");
            assignments[sum-1]=sc.nextInt();
            sum=sum+1;
            System.out.println("If you want to put another assignment pres : yes ");
            System.out.println("Else press any button to logOff");
        }while("yes".equals(sc.next()));
        AssignmentsPerCourseDao apc = new AssignmentsPerCourseDao();
            for(int i: assignments){
                if(i>0){
                    apc.insertAssignmentsPerCourse(course.getListOfIdCourses().get(num_course-1),assignment.getListOfIdAssignments().get(i-1) );
                }
            }
    }
    
}
