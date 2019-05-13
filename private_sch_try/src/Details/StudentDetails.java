/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.StudentDao;
import java.util.Scanner;
import private_sch_try.Student;

/**
 *
 * @author CHRIS
 */
public class StudentDetails {

    public Student getStudentDetails(Scanner sc) {
        Student student = new Student();
        System.out.println("Please give the First and Last Name of student: ");
        student.setFname(sc.next());
        student.setLname(sc.next());
        System.out.println("Please give the Date of Birthday of student in this form (yyyy-MM-dd): ");
        student.setBirthday(sc.next());
        System.out.println("Please give the Tuitions of student: ");
        student.setTuitions(sc.nextInt());

        return (student);
    }

    public void CreateStudent(Scanner sc) {
        do {
            StudentDetails cd = new StudentDetails();
            Student stu = new Student();
            stu = cd.getStudentDetails(sc);
            StudentDao assD = new StudentDao();
            assD.insertStudent(stu);
            System.out.println("If you want to put again student pres yes");
            System.out.println("Else pres any button");
        } while ("yes".equals(sc.next()));
    }
}
