package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import private_sch_try.Assignment;
import private_sch_try.Assignments_Per_Student_Per_Course;
import private_sch_try.Course;
import private_sch_try.Student;

/**
 *
 * @author CHRIS
 */
public class Assignments_Per_Student_Per_CourseDao {

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

    private Assignment getAssignment(int id) {
        AssignmentDao a = new AssignmentDao();
        return a.getAssignmentById(id);
    }

    private Course getCourse(int id) {
        CourseDao c = new CourseDao();
        return c.getCourseById(id);
    }

    private Student getStudent(int id) {
        StudentDao c = new StudentDao();
        return c.getStudentById(id);
    }

    //edo dokimi
    public ArrayList<Assignments_Per_Student_Per_Course> getqqq() {
        String query = "select * from Assignments_Per_Student_Per_Course where idcourse= ?";
        ArrayList<Assignments_Per_Student_Per_Course> list = new ArrayList();
        ArrayList<Assignment> assignments = new ArrayList();
        ArrayList<Student> students = new ArrayList();
        Assignments_Per_Student_Per_Course spc = new Assignments_Per_Student_Per_Course();
        CourseDao cd = new CourseDao();
        ArrayList<Integer> cl = cd.getListOfIdCourses();

        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            for (Integer i : cl) {
                //rs = null;
                pst = conn.prepareStatement(query);
                pst.setInt(1, i);
                rs = pst.executeQuery();
                while (rs.next()) {
                    assignments.add(getAssignment(rs.getInt("idassignment")));
                    students.add(getStudent(rs.getInt("idstudent")));
                    spc.setCourse(getCourse(rs.getInt("idcourse")));
                }
                spc.setAssignments(assignments);
                list.add(spc);
                //spc.setCourse(getCourse(rs.getInt("idcourse")));
            }
        } catch (SQLException x) {
            x.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Assignments_Per_Student_Per_Course.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    //telos

    public void getListOfAssignments_Per_Student_Per_Course() {
        ArrayList<Assignments_Per_Student_Per_Course> list = new ArrayList(); //lista apothikefsis gia Assignments_Per_Student_Per_Course
        ArrayList<Assignment> assignments = new ArrayList();       //lista gia na apothikevo assignments mesa sto while gia ENA course
        ArrayList<Student> students = new ArrayList();
        Assignments_Per_Student_Per_Course spc = new Assignments_Per_Student_Per_Course(); //antikimeno spc gia na pernao List<Student> kai to course gia to opio psaxno ekini tin stigmi
        CourseDao cd = new CourseDao();                    //to ftiaxno gia na boro na travao ola ta course pou iparxoun 
        ArrayList<Integer> cl = cd.getListOfIdCourses();         //apothikefsi ton idcourse
        String query = "select * from Assignments_Per_Student_Per_Course where idcourse= ?";
        PreparedStatement pst = null;
        Connection conn = getConnection();
        ResultSet rs = null;

        try {
            for (Integer i : cl) {
                pst = conn.prepareStatement(query);
                pst.setInt(1, i);
//                Statement st = getConnection().createStatement();
                //ResultSet rs = st.executeQuery(query);
                //rs=pst.executeQuery();
                rs = pst.executeQuery();
                while (rs.next()) {

                    assignments.add(getAssignment(rs.getInt("idassignment")));
                    students.add(getStudent(rs.getInt("idstudent")));
                    //tpc.setCourse(getCourse(rs.getInt("idcourse")));
                    //list.add(tpc);
                    //System.out.println(rs);
                    //spc.setCourse(getCourse(rs.getInt("idcourse")));
                }
                rs = null;
                spc.setAssignments(assignments);
                spc.setCourse(getCourse(rs.getInt("idcourse")));
                list.add(spc);
//                rs.close();
//               st.close();
                closeConnection();
            }
            rs.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Assignments_Per_Student_Per_CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return list;
        System.out.println(list);
    }

}
