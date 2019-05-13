
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
import private_sch_try.Course;
import private_sch_try.Student;

public class StudentDao {
    
    private final String URL = "jdbc:mysql://localhost:3306/privateschool?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "a2MERA3minka";
    private Connection conn;
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
            //System.out.println("Connection successfully established.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Integer> getListOfIdStudents() {
        List<Integer> list = new ArrayList();
        String query = "select idstudent from students";
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
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Student> getListOfStudents() {
        List<Student> list = new ArrayList();
        String query = "select * from students";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Student s = new Student(rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getInt(5));
                list.add(s);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Student getStudentById(int id) {
        String query = "select * from students where idstudent = ?";
        Student s = new Student();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            s.setFname(rs.getString("fname"));
            s.setLname(rs.getString("lname"));
            s.setBirthday(rs.getString("birthday"));
            s.setTuitions(rs.getInt("tuitions"));
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }
    
    public void insertStudent(String fname, String lname, String birthday, int tuitions) {
        String query = "INSERT INTO students (fname,lname,birthday,tuitions) VALUES (?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            //pst.setInt(1, idcourse);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, birthday);
            pst.setDouble(4, tuitions);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertStudent(Student student) {
        String query = "INSERT INTO students (fname,lname,birthday,tuitions) VALUES (?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            //pst.setInt(1, idcourse);
            pst.setString(1, student.getFname());
            pst.setString(2, student.getLname());
            pst.setString(3, student.getBirthday());
            pst.setDouble(4, student.getTuitions());
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteStudentById(int id){
        String query = "delete from students where idstudent = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            System.out.println("result value is " + result);
            if(result>0){
                System.out.println("Successfully deleted");
            }else{
                System.out.println("The student with id "+id+" was not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void updateStudentById(int idstudent , String fname, String lname, String birthday, int tuitions){
        String query = "update students set fname = ? , lname = ? , birthday = ? , tuitions = ? where idstudent = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, birthday);
            pst.setInt(4, tuitions);
            pst.setInt(5, idstudent);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("students not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateStudentFnameById(int idstudent , String fname){
        String query = "update students set fname = ? where idstudent = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, fname);
            pst.setInt(2, idstudent);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("students not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void updateStudentLnameById(int idstudent , String lname){
        String query = "update students set lname = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, lname);
            pst.setInt(2, idstudent);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("students not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateStudentBirthdayById(int idstudent , String birthday){
        String query = "update students set birthday = ? where idstudent = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, birthday);
            pst.setInt(2, idstudent);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("students not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateStudentTutionsById(int idstudent , int tuitions){
        String query = "update students set tuitions = ? where idstudent = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, tuitions);
            pst.setInt(2, idstudent);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("students not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
}
