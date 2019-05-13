
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
import private_sch_try.AssignmentsPerStudentPerCourse;
import private_sch_try.Course;
import private_sch_try.Student;

/**
 *
 * @author CHRIS
 */
public class AssignmentsPerStudentPerCourseDao {
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
    
    public ArrayList<AssignmentsPerStudentPerCourse> view_a_p_s_p_c() {
        String query = "select * from Assignments_Per_Student_Per_Course";
        ArrayList<AssignmentsPerStudentPerCourse> list= new ArrayList();
        AssignmentsPerStudentPerCourse w=new AssignmentsPerStudentPerCourse();
        ArrayList<Assignment> assignments = new ArrayList();
        ArrayList<Student> students = new ArrayList();
        ArrayList<Course> course=new ArrayList();
        ArrayList<Integer> n=new ArrayList();
        //CourseDao cd = new CourseDao();
        //ArrayList<Integer> cl = cd.getListOfIdCourses();

        Connection conn = getConnection();
        PreparedStatement pst = null;
        
        //int q=cl.size();
        try {
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    assignments.add(getAssignment(rs.getInt("idassignment")));
                    students.add(getStudent(rs.getInt("idstudent")));
                    course.add(getCourse(rs.getInt("idcourse")));
                    n.add(rs.getInt("id_apspc"));
                }
                w.setAss(assignments);
                w.setC(course);
                w.setSt(students);
                w.setId(n);
                list.add(w);
                rs.close();
            st.close();
            closeConnection();
         
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void insertMarkTrainer(int mark , int idapspc){
        String query = "INSERT INTO mark (mark,id_apspc) VALUES (?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, mark);
            pst.setInt(2, idapspc);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success insert mark");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertSubStudent(int idstudent , int idassignemnt){
        String query = "INSERT INTO submit_student (idstudent,idassignment) VALUES (?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idstudent);
            pst.setInt(2, idassignemnt);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success insert mark");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
