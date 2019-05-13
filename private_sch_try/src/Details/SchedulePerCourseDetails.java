/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.CourseDao;
import dao.SchedulePerCourseDao;
import java.util.Scanner;
import private_sch_try.SchedulePerCourse;

/**
 *
 * @author CHRIS
 */
public class SchedulePerCourseDetails {

    public SchedulePerCourse getSchedulePerCourseDetails(Scanner sc) {
        SchedulePerCourse spc = new SchedulePerCourse();
        CourseDao course = new CourseDao();
        int num_course;
        for (int i = 0; i < course.getListOfIdCourses().size(); i++) {
            System.out.println("Course " + (i + 1) + ") :");
            System.out.println(course.getListOfCourses().get(i));
        }
        System.out.println("Please give a number of course: ");
        spc.setIdcourse(course.getListOfIdCourses().get(sc.nextInt() - 1));
        System.out.println("Schedule of Monday: ");
        spc.setMonday(sc.next());
        System.out.println("Schedule of Tuesday: ");
        spc.setTuesday(sc.next());
        System.out.println("Schedule of Wednesday: ");
        spc.setWednesday(sc.next());
        System.out.println("Schedule of Thursday: ");
        spc.setThursday(sc.next());
        System.out.println("Schedule of Friday: ");
        spc.setFriday(sc.next());
        System.out.println("Schedule of Saturday: ");
        spc.setSaturday(sc.next());
        System.out.println("Schedule of Sunday: ");
        spc.setSunday(sc.next());

        return (spc);
    }

    public void CreateSchedulePerCourse(Scanner sc) {
        do {
            SchedulePerCourseDao socdao = new SchedulePerCourseDao();
            SchedulePerCourseDetails scpd = new SchedulePerCourseDetails();
            SchedulePerCourse spcc = new SchedulePerCourse();
            spcc = scpd.getSchedulePerCourseDetails(sc);
            socdao.insertSchedulePerCourse(spcc);
            System.out.println("If you want to put again Schedule Per Course pres yes");
            System.out.println("Else pres any button");
        } while ("yes".equals(sc.next()));
    }

}
