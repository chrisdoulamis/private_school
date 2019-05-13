/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.AssignmentDao;
import java.util.Scanner;
import private_sch_try.Assignment;

/**
 *
 * @author CHRIS
 */
public class AssignmentDetails {

    public Assignment getAssignmentDetails(Scanner sc) {
        Assignment assignment = new Assignment();
        System.out.println("Please give Title of assignment: ");
        assignment.setTitle(sc.next());
        System.out.println("Please give the Description of assignment: ");
        assignment.setDescription(sc.next());
        System.out.println("Please give the Date and time of submission of assignment in this form (yyyy-MM-dd/hh:mm:ss): ");
        assignment.setSubDateTime(sc.next());
        System.out.println("Please give the Oral Mark of assignment: ");
        assignment.setOmark(sc.nextInt());
        System.out.println("Please give the Total Mark of assignment: ");
        assignment.setTmark(sc.nextInt());

        return (assignment);
    }

    public void CreateAssignment(Scanner sc) {
        do {
            AssignmentDetails cd = new AssignmentDetails();
            Assignment ass = new Assignment();
            ass = cd.getAssignmentDetails(sc);
            AssignmentDao assD = new AssignmentDao();
            assD.insertAssignment(ass);
            System.out.println("If you want to put again assignment pres yes");
            System.out.println("Else pres any button");
        } while ("yes".equals(sc.next()));
    }

}
