/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users_login;

import Details.UsersDetails;
import dao.StudentDao;
import dao.TrainerDao;
import dao.UsersDao;
import java.util.Scanner;

/**
 *
 * @author CHRIS
 */
public class LogIn {

    public void HeadMasterLogIn(Scanner sc) {
        UsersDetails sud = new UsersDetails();
        HeadMaster hm = new HeadMaster();
        hm = sud.getHeadMasterDetails(sc);
        UsersDao gre = new UsersDao();
        //System.out.println(gre.getHeadMasterLogin(hm));
        Boolean bool = gre.getHeadMasterLogin(hm);
        if (bool == true) {
            System.out.println("Welcom: HeadMaster");
        } else {
            System.out.println("den ise o headmaster");
        }
    }

    public int TrainerLogIn(Scanner sc) {
        UsersDetails sud = new UsersDetails();
        TrainerUsers tu = new TrainerUsers();
        tu = sud.getTrainerDetails(sc);
        UsersDao gre = new UsersDao();
        //System.out.println(gre.getIdTrainerByLogin(tu));
        int id = gre.getIdTrainerByLogin(tu);
        TrainerDao tr = new TrainerDao();
        System.out.println("Welcom: " + tr.getTrainerById(id));
        return id;
    }

    public int StudentLogIn(Scanner sc) {
        UsersDetails sud = new UsersDetails();
        StudentUsers su = new StudentUsers();
        su = sud.getStudentDetails(sc);
        UsersDao qre = new UsersDao();
        System.out.println(qre.getIdStudentByLogin(su));
        int id = qre.getIdStudentByLogin(su);
        StudentDao st = new StudentDao();
        System.out.println("Welcome: " + st.getStudentById(id));
        return id;
    }

}
