/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
//import private_sch_try.Assignment;
import private_sch_try.Course;

/**
 *
 * @author CHRIS
 */
public class AssignmentsPerCourseDao {

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
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Assignment getAssignment(int id) {
        AssignmentDao a = new AssignmentDao();
        return a.getAssignmentById(id);
    }

    public Course getCourse(int id) {
        CourseDao c = new CourseDao();
        return c.getCourseById(id);
    }

    public void insertAssignmentsPerCourse(int idcourse, int idassignment) {
        String query = "INSERT INTO assignments_per_course (idcourse,idassignment) VALUES (?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idcourse);
            pst.setInt(2, idassignment);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void insertListAssignmentsPerCourse(int idcourse, List<Integer> ass) {   //edo gia to ena course pai kai vzi lista apo asignments
//        String query = "INSERT INTO Assignments_Per_Courses VALUES (?,?)";
//        try {
//            Connection con = getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//            for (Integer i : ass) {
//                pst.setInt(1, idcourse);
//                pst.setInt(2, i);
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
//            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void deleteAssignmentsPerCourseById(int id) {
        String query = "delete from Assignments_Per_Course where id_a_p_c = ?";
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
            Logger.getLogger(AssignmentsPerCourse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<AssignmentsPerCourse> getListOfAssignmentsPerCourse() {
        ArrayList<AssignmentsPerCourse> list = new ArrayList(); //lista apothikefsis gia AssignmentsPerCourse
        ArrayList<Assignment> assignments = new ArrayList();       //lista gia na apothikevo assignments mesa sto while gia ENA course
        AssignmentsPerCourse spc = new AssignmentsPerCourse(); //antikimeno spc gia na pernao List<Student> kai to course gia to opio psaxno ekini tin stigmi
        CourseDao cd = new CourseDao();                    //to ftiaxno gia na boro na travao ola ta course pou iparxoun 
        List<Integer> cl = cd.getListOfIdCourses();         //apothikefsi ton idcourse
        String query = "select * from Assignments_Per_Courses where idcourse= ?";
        PreparedStatement pst = null;

        try {
            for (Integer i : cl) {
                pst = conn.prepareStatement(query);
                pst.setInt(1, i);
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {

                    assignments.add(getAssignment(rs.getInt("idassignment")));
                    //tpc.setCourse(getCourse(rs.getInt("idcourse")));
                    //list.add(tpc);
                    //System.out.println(rs);
                }
                spc.setCourse(getCourse(rs.getInt("idcourse")));
                spc.setAssignments(assignments);
                list.add(spc);
                rs.close();
                st.close();
                closeConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void erotima_ii_student() {
        String query = "SELECT  a.description , a.subDateTime , c.stream  , c.type \n"
                + "FROM courses c \n"
                + "INNER JOIN assignments_per_course ass\n"
                + "ON c.idcourse = ass.idcourse\n"
                + "inner join assignments a\n"
                + "on a.idassignment=ass.idassignment";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("Imerominia paradosis: " + rs.getDate(2) + " / gia to " + rs.getString(1) + " / gia to course: " + rs.getString(3) + " " + rs.getString(4));
                System.out.println("");
            }
            rs.close();
            st.close();
            //conn.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public List<AssignmentsPerCourse>  getListOfAssignmentsPerCourses() {
//        List<AssignmentsPerCourse> list = new ArrayList();
//        AssignmentsPerCourse apc = new AssignmentsPerCourse();
//        String query = "select * from assignments_per_courses ";
//        try {
//            Statement st = getConnection().createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                apc.setId_apc(rs.getInt("id_a_p_c"));
//                apc.setAssignments(getAssignment(rs.getInt("idassignment")));
//                apc.setCourses(getCourse(rs.getInt("idcourse")));
//                list.add(apc);
//            }
//            rs.close();
//            st.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    
//    public AssignmentsPerCourse getAssignmentsPerCourseById(int id) {
//        //List<Integer> list = new ArrayList();
//        AssignmentsPerCourse apc = new AssignmentsPerCourse();
//        String query = "select * from AssignmentsPerCourse where id_apc = ?";
//        //AssignmentsPerCourse apc = new AssignmentsPerCourse();
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, id);
//            rs = pst.executeQuery();
//            rs.next();
//            apc.setId_apc(rs.getInt("id_apc"));
//            apc.setAssignments(getAssignment(rs.getInt("idassignment")));
//            apc.setCourses(getCourse(rs.getInt("idcourse")));
//        }catch(SQLException x){
//            x.printStackTrace();
//        }finally{
//            try {
//                rs.close();
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return apc;
//    }
//    
//    public void insertAssignmentsPerCourse(int idcourse , int idassignment) {
//        String query = "INSERT INTO assignments_per_course VALUES (?,?)";
//        try {
//            Connection con = getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2 , idassignment);
//            int noumero = pst.executeUpdate();
//            if (noumero > 0) {
//                System.out.println("success");
//            } else {
//                System.out.println("not success");
//            }
//            pst.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void deleteAssignmentsPerCourseById(int id){
//        String query = "delete from AssignmentsPerCourse where id_a_p_c = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, id);
//            int result = pst.executeUpdate();
//            System.out.println("result value is " + result);
//            if(result>0){
//                System.out.println("Successfully deleted");
//            }else{
//                System.out.println("The AssignmentsPerCourse with id "+id+" was not found");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AssignmentsPerCourse.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//     public void updateAssignmentsPerCourseById(int id_apc , int idcourse , int idassignment){
//        String query = "update Assignments_Per_Course set id_course = ? , id_assignment = ? where id_apc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2, idassignment);
//            pst.setInt(3, id_apc);
//            int result = pst.executeUpdate();
//            if(result>0){
//                System.out.println("successfully updated");
//            }else{
//                System.out.println("customer not updated");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//     
//     public void updateAssignmentsPerCourse_idCourse_ById(int id_apc , int idcourse){
//        String query = "update customers set idcourse = ? where id_apc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2, id_apc);
//            int result = pst.executeUpdate();
//            if(result>0){
//                System.out.println("successfully updated");
//            }else{
//                System.out.println("customer not updated");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//     public void updateAssignmentsPerCourse_idassignment_ById(int id_apc , int idassignment){
//        String query = "update customers set idassignments = ? where id_apc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, idassignment);
//            pst.setInt(2, id_apc);
//            int result = pst.executeUpdate();
//            if(result>0){
//                System.out.println("successfully updated");
//            }else{
//                System.out.println("customer not updated");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
}
