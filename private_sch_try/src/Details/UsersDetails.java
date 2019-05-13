/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import java.util.Scanner;
import users_login.HeadMaster;
import users_login.StudentUsers;
import users_login.TrainerUsers;

/**
 *
 * @author CHRIS
 */
public class UsersDetails {
    
    public StudentUsers getStudentDetails(Scanner sc) {
        StudentUsers su = new StudentUsers();
        System.out.println("Student Please give your User Name : ");
        su.setUsername(sc.next()); 
        System.out.println("Student Please give your Password : ");
        su.setPass(sc.next());
        
        return(su);
    }
    
    public TrainerUsers getTrainerDetails(Scanner sc) {
        TrainerUsers tu = new TrainerUsers();
        System.out.println("Trainer Please give your User Name : ");
        tu.setUsername(sc.next()); 
        System.out.println("Trainer Please give your Password : ");
        tu.setPass(sc.next());
        
        return(tu);
    }
    
    public HeadMaster getHeadMasterDetails(Scanner sc) {
        HeadMaster hm = new HeadMaster();
        System.out.println("HeadMaster Please give your User Name : ");
        hm.setUsername(sc.next()); 
        System.out.println("HeadMaster Please give your Password : ");
        hm.setPass(sc.next());
        
        return(hm);
    }
    
    
    
}
