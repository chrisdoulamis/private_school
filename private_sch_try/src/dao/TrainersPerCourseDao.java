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
import private_sch_try.AssignmentsPerCourse;
import private_sch_try.Course;
import private_sch_try.Trainer;
import private_sch_try.TrainersPerCourse;

public class TrainersPerCourseDao {

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
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Trainer getTrainer(int id) {
        TrainerDao tr = new TrainerDao();
        return tr.getTrainerById(id);
    }

    public Course getCourse(int id) {
        CourseDao c = new CourseDao();
        return c.getCourseById(id);
    }

    public ArrayList<Course> getListOfCoursesByTrainer(int idtrainer) { //gia ton trainer me id=idtrainer exoume ola ta course pou tou analogoun
        ArrayList<Course> list = new ArrayList();
        String query = "select * from trainers_per_course where idtrainer = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, idtrainer);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(getCourse(rs.getInt("idcourse")));
            }
        } catch (SQLException x) {
            x.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public void insertTrainersPerCourse(int idcourse, int idtrainer) {
        String query = "INSERT INTO trainers_per_course (idcourse , idtrainer) VALUES (?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idcourse);
            pst.setInt(2, idtrainer);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void insertListTrainersPerCourse(int idcourse, List<Integer> tr) {
//        String query = "INSERT INTO trainers_per_course VALUES (?,?)";
//        try {
//            Connection con = getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//            for(Integer i : tr){
//            pst.setInt(1, idcourse);
//            pst.setInt(2, i);
//            }
//            int noumero = pst.executeUpdate();
//            if (noumero > 0) {
//                System.out.println("success");
//            } else {
//                System.out.println("not success");
//            }
//            pst.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void deleteTrainersPerCourseById(int id) {
        String query = "delete from trainers_per_course where id_t_p_c = ?";
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
                System.out.println("The AssignmentsPerCourse with id " + id + " was not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<TrainersPerCourse> getListOfTrainersPerCourse() {
        ArrayList<TrainersPerCourse> list = new ArrayList(); //lista apothikefsis gia trainerspercourse
        ArrayList<Trainer> trainers = new ArrayList();       //lista gia na apothikevo trainers mesa sto while gia ENA course
        TrainersPerCourse tpc = new TrainersPerCourse(); //antikimeno tpc gia na pernao List<Trainer> kai to course gia to opio psano ekini tin stigmi
        CourseDao cd = new CourseDao();                    //to ftiaxno gia na boro na travao ola ta course pou iparxoun 
        ArrayList<Integer> cl = cd.getListOfIdCourses();         //apothikefsi ton idcourse
        String query = "select * from trainers_per_course where idcourse= ?";
        PreparedStatement pst = null;

        try {
            for (Integer i : cl) {
                pst = conn.prepareStatement(query);
                pst.setInt(1, i);
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {

                    trainers.add(getTrainer(rs.getInt("idtrainer")));
                    //tpc.setCourse(getCourse(rs.getInt("idcourse")));
                    //list.add(tpc);
                    //System.out.println(rs);
                }
                tpc.setCourse(getCourse(rs.getInt("idcourse")));
                tpc.setTrainers(trainers);
                list.add(tpc);
                rs.close();
                st.close();
                closeConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    public List<TrainersPerCourse> getListOfTrainersPerCourse() {
//        List<TrainersPerCourse> list = new ArrayList();
//        TrainersPerCourse tpc = new TrainersPerCourse();
//        String query = "select * from Trainers_Per_Courses ";
//        try {
//            Statement st = getConnection().createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                
//                tpc.setTrainers(getTrainer(rs.getInt("idtrainer")));
//                tpc.setCourse(getCourse(rs.getInt("idcourse")));
//                list.add(tpc);
//                //System.out.println(rs);
//            }
//            rs.close();
//            st.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//
//    public TrainersPerCourse getTrainersPerCourseById(int id) {
//        TrainersPerCourse tpc = new TrainersPerCourse();
//        String query = "select * from Trainers_Per_Course where id_tpc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, id);
//            rs = pst.executeQuery();
//            rs.next();
//            tpc.setId_tpc(rs.getInt("id_tpc"));
//            tpc.setTrainer(getTrainer1(rs.getInt("idtrainer")));
//            tpc.setCourse(getCourse(rs.getInt("idcourse")));
//        } catch (SQLException x) {
//            x.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return tpc;
//    }
//
//    public void insertTrainersPerCourse(int idcourse, int idtrainer) {
//        String query = "INSERT INTO trainers_per_course VALUES (?,?)";
//        try {
//            Connection con = getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2, idtrainer);
//            int noumero = pst.executeUpdate();
//            if (noumero > 0) {
//                System.out.println("success");
//            } else {
//                System.out.println("not success");
//            }
//            pst.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void deleteTrainersPerCourseById(int id) {
//        String query = "delete from Trainers_Per_Course where id_apc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, id);
//            int result = pst.executeUpdate();
//            System.out.println("result value is " + result);
//            if (result > 0) {
//                System.out.println("Successfully deleted");
//            } else {
//                System.out.println("The AssignmentsPerCourse with id " + id + " was not found");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    public void updateTrainersPerCourseById(int id_tpc, int idcourse, int idtrainer) {
//        String query = "update Trainers_Per_Course set id_course = ? , id_trainer = ? where id_tpc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2, idtrainer);
//            pst.setInt(3, id_tpc);
//            int result = pst.executeUpdate();
//            if (result > 0) {
//                System.out.println("successfully updated");
//            } else {
//                System.out.println("customer not updated");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    public void getTrainerPerCoursesById(int idtrainer) {
//        TrainersPerCourse tpc = new TrainersPerCourse();
//        List<TrainersPerCourse> list = new ArrayList();
//        String query = "select * from Trainers_Per_Course where idtrainer = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, idtrainer);
//            rs = pst.executeQuery();
//            //System.out.println(rs);
//            while (rs.next()) {
//                tpc.setId_tpc(rs.getInt("id_t_p_c"));
//                tpc.setTrainer(getTrainer1(idtrainer));
//                tpc.setCourse(getCourse(rs.getInt("idcourse")));
//                list.add(tpc);
//                //System.out.println(rs);
//            }
//        } catch (SQLException x) {
//            x.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        //return tpc;
//        System.out.println(tpc);
//    }
}
