package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import private_sch_try.Assignment;
import private_sch_try.Trainer;

public class TrainerDao {

    private final String URL = "jdbc:mysql://localhost:3306/privateschool?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "a2MERA3minka";
    private Connection conn;

    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
            System.out.println("Connection successfully established.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Integer> getListOfIdTrainers() {
        List<Integer> list = new ArrayList();
        String query = "select idtrainer from trainers";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Trainer> getListOfTrainers() {
        List<Trainer> list = new ArrayList();
        String query = "select * from trainers";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Trainer t = new Trainer(rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(t);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Trainer getTrainerById(int id) {
        String query = "select * from trainers where idtrainer = ?";
        Trainer t = new Trainer();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            t.setFname(rs.getString("fname"));
            t.setLname(rs.getString("lname"));
            t.setSubject(rs.getString("subject"));
        } catch (SQLException x) {
            x.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return t;
    }

    public void insertTrainer(String fname, String lname, String subject) {
        String query = "INSERT INTO trainers (fname,lname,subject) VALUES (?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, subject);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertTrainer(Trainer trainer) {
        String query = "INSERT INTO trainers (fname,lname,subject) VALUES (?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, trainer.getFname());
            pst.setString(2, trainer.getLname());
            pst.setString(3, trainer.getSubject());
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTrainerById(int id) {
        String query = "delete from trainers where idtrainer = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            System.out.println("result value is " + result);
            if (result > 0) {
                System.out.println("Successfully deleted");
            } else {
                System.out.println("The trainer with id " + id + " was not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateTrainerById(int idtrainer, String fname, String lname, String subject) {
        String query = "update trainers set fname = ? , lname = ? , subject = ? where idtrainer = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, subject);
            pst.setInt(4, idtrainer);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("trainer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateTrainerFnameById(int idtrainer, String fname) {
        String query = "update trainers set fname = ? where idtrainer = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fname);
            pst.setInt(2, idtrainer);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("trainer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateTrainerLnameById(int idtrainer, String lname) {
        String query = "update trainers set lname = ? where idtrainer = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, lname);
            pst.setInt(2, idtrainer);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("trainer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateTrainerSubjectById(int idtrainer, String subject) {
        String query = "update trainers set subject = ? where idtrainer = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, subject);
            pst.setInt(2, idtrainer);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("trainer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
