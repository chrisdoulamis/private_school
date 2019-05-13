
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
import private_sch_try.Student;

public class AssignmentDao {
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
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Integer> getListOfIdAssignments() {
        List<Integer> list = new ArrayList();
        String query = "select idassignment from assignments";
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
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Assignment> getListOfAssignments() {
        List<Assignment> list = new ArrayList();
        String query = "select * from assignments";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Assignment a = new Assignment(rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getInt(5) , rs.getInt(6));
                list.add(a);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Assignment getAssignmentById(int id) {
        String query = "select * from assignments where idassignment = ?";
        Assignment a = new Assignment();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            a.setTitle(rs.getString("title"));
            a.setDescription(rs.getString("description"));
            a.setOmark(rs.getInt("omark"));
            a.setTmark(rs.getInt("tmark"));
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return a;
    }
    
    public void insertAssignment(String title, String description, String subDateTime, int omark, int tmark) {
        String query = "INSERT INTO assignments (title,description,subDateTime,omark,tmark) VALUES (?,?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            //pst.setInt(1, idcourse);
            pst.setString(1, title);
            pst.setString(2, description);
            pst.setString(3, subDateTime);
            pst.setInt(4, omark);
            pst.setInt(5, tmark);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertAssignment(Assignment assignment) {
        String query = "INSERT INTO assignments (title,description,subDateTime,omark,tmark) VALUES (?,?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            //pst.setInt(1, idcourse);
            pst.setString(1, assignment.getTitle());
            pst.setString(2, assignment.getDescription());
            pst.setString(3, assignment.getSubDateTime());
            pst.setInt(4, assignment.getOmark());
            pst.setInt(5, assignment.getTmark());
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAssignmentById(int id){
        String query = "delete from assignments where idassignment = ?";
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
                System.out.println("The assignment with id "+id+" was not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void updateAssignmentById(int idassignment , String title, String description, String subDateTime, int omark, int tmark){
        String query = "update assignments set title = ? , description = ? , subDateTime = ? , omark = ? , tmark = ? where idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, description);
            pst.setString(3, subDateTime);
            pst.setInt(4, omark);
            pst.setInt(5, tmark);
            pst.setInt(6, idassignment);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateAssignmentTitleById(int idassignment , String title){
        String query = "update assignments set title = ? where idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, title);
            pst.setInt(2, idassignment);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("assignment not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void updateAssignmentDescriptionById(int idassignment , String description){
        String query = "update assignments set description = ? where idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, description);
            pst.setInt(2, idassignment);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateAssignmentSubDateTimeById(int idassignment , String subDateTime){
        String query = "update assignments set subDateTime = ? where idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, subDateTime);
            pst.setInt(2, idassignment);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("assignment not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateAssignmentOmarkById(int idassignment , double omark){
        String query = "update assignments set omark = ? where idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setDouble(1, omark);
            pst.setInt(2, idassignment);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("assignment not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void updateAssignmentTmarkById(int idassignment , double tmark){
        String query = "update assignments set tmark = ? where idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setDouble(1, tmark);
            pst.setInt(2, idassignment);
            int result = pst.executeUpdate();
            if(result>0){
                System.out.println("successfully updated");
            }else{
                System.out.println("assignments not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
}
