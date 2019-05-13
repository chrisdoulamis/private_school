/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Details;

import dao.TrainerDao;
import java.util.Scanner;
import private_sch_try.Trainer;

/**
 *
 * @author CHRIS
 */
public class TrainerDetails {

    public Trainer getTrainerDetails(Scanner sc) {
        Trainer trainer = new Trainer();
        System.out.println("Please give the First and Last Name of trainer: ");
        trainer.setFname(sc.next());
        trainer.setLname(sc.next());
        System.out.println("Please give the Subject of trainer: ");
        trainer.setSubject(sc.next());

        return (trainer);
    }

    public void CreateTrainer(Scanner sc) {
        do {
            TrainerDetails cd = new TrainerDetails();
            Trainer tr = new Trainer();
            tr = cd.getTrainerDetails(sc);
            TrainerDao trD = new TrainerDao();
            trD.insertTrainer(tr);
            System.out.println("If you want to put again trainer pres yes");
            System.out.println("Else pres any button");
        } while ("yes".equals(sc.next()));
    }

}
