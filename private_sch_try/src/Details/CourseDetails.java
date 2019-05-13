/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.CourseDao;
import java.util.Scanner;
import private_sch_try.Course;

/**
 *
 * @author CHRIS
 */
public class CourseDetails {

    public Course getCourseDetails(Scanner sc) {
        Course course = new Course();
        System.out.println("Please give the Title of course: ");
        course.setTitle(sc.next());
        System.out.println("Please give the Stream of course: ");
        course.setStream(sc.next());
        System.out.println("Please give the Type of course: ");
        course.setType(sc.next());
        System.out.println("Please give the Start Date of course: ");
        course.setStartDate(sc.next());
        System.out.println("Please give the End Date of course: ");
        course.setEndDate(sc.next());

        return (course);
    }

    public void CreateCourse(Scanner sc) {
        do {
            CourseDetails cd = new CourseDetails();
            Course cou = new Course();
            cou = cd.getCourseDetails(sc);
            CourseDao couD = new CourseDao();
            couD.insertCourse(cou);
            System.out.println("If you want to put again Course pres yes");
            System.out.println("Else pres any button");
        } while ("yes".equals(sc.next()));
    }
}
