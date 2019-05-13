/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.CourseDao;
import dao.StudentDao;
import dao.StudentsPerCourseDao;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CHRIS
 */
public class StudentsPerCourseDetails {
    
    //perni ena course kai mia lista apo students kai tous apothikevi
    public void getStudentsPerCourseDetails(Scanner sc) {
        StudentDao student = new StudentDao();
        CourseDao course = new CourseDao();
        int num_course;
        //int num_student;
        int [] students=new int[student.getListOfIdStudents().size()];
        int sum=1;
        System.out.println("The list student is : ");
        for( int i=0 ; i<student.getListOfIdStudents().size() ; i++ ){
            System.out.println("Student " + (i+1) + ") :");
            System.out.println(student.getListOfStudents().get(i));
        }
        for( int i=0 ; i<course.getListOfIdCourses().size() ; i++){
            System.out.println("Course " + (i+1) + ") :");
            System.out.println(course.getListOfCourses().get(i));
        }
        System.out.println("Please give the number of Course : ");
        num_course=sc.nextInt();
        do{
            System.out.println("Please give the " +sum+") student to put in the Course");
            students[sum-1]=sc.nextInt();
            sum=sum+1;
            System.out.println("If you want to put another student pres : yes ");
            System.out.println("Else press any button to logOff");
        }while("yes".equals(sc.next()));
        StudentsPerCourseDao spc = new StudentsPerCourseDao();
            for(int i: students){
                if(i>0){
                    spc.insertStudentsPerCourse(course.getListOfIdCourses().get(num_course-1),student.getListOfIdStudents().get(i-1) );
                }
            }
    }
    
}
