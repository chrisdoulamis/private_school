package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import private_sch_try.Course;
import private_sch_try.SchedulePerCourse;

/**
 *
 * @author CHRIS
 */
public class SchedulePerCourseDao {
    
    private final String URL = "jdbc:mysql://localhost:3306/privateschool?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "*********";
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
    
    public Course getCourse(int id){
        CourseDao c = new CourseDao();
        return c.getCourseById(id);
    }
    
    public ArrayList<Integer> getListIdOfCoursesByIdStudent(int idstudent) {
        String query = "select idcourse from students_per_course where idstudent = ?";
        ArrayList<Integer> list_id=new ArrayList();
        //Product p = new Product();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, idstudent);
            rs = pst.executeQuery();
            while(rs.next()){
                list_id.add(rs.getInt(1));
            }
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list_id;
    }
    
    public SchedulePerCourse getScheduleByIdCourse(int idcourse) {
        String query = "select * from schedule_per_course where idcourse = ? ";
        SchedulePerCourse spc = new SchedulePerCourse();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, idcourse);
            rs = pst.executeQuery();
            rs.next();
            spc.setMonday(rs.getString("monday"));
            spc.setTuesday(rs.getString("tuesday"));
            spc.setWednesday(rs.getString("wednesday"));
            spc.setThursday(rs.getString("thursday"));
            spc.setFriday(rs.getString("friday"));
            spc.setSaturday(rs.getString("saturday"));
            spc.setSunday(rs.getString("sunday"));
            spc.setIdcourse(rs.getInt("idcourse"));
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return spc;
    }
    
    public void erotima_i_student(int idstudent){
        
        SchedulePerCourseDao spcd=new SchedulePerCourseDao();
        ArrayList<Integer> num=getListIdOfCoursesByIdStudent(idstudent);
        for(Integer i : num){
            System.out.println(getCourse(i));
            System.out.println(getScheduleByIdCourse(i));
        }
        
    
    }
    
    public void insertSchedulePerCourse(String monday , String tuesday , String wednesday , String thursday , String friday , String saturday , String sunday , int idcourse) {
        String query = "INSERT INTO schedule_per_course (monday,tuesday,wednesday,thursday,friday,saturday,sunday,idcourse) VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, monday);
            pst.setString(2, tuesday);
            pst.setString(3, wednesday);
            pst.setString(4, thursday);
            pst.setString(5, friday);
            pst.setString(6, saturday);
            pst.setString(7, sunday);
            pst.setInt(8 , idcourse);
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertSchedulePerCourse( SchedulePerCourse spc) {
        String query = "INSERT INTO schedule_per_course (monday,tuesday,wednesday,thursday,friday,saturday,sunday,idcourse) VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, spc.getMonday());
            pst.setString(2, spc.getTuesday());
            pst.setString(3, spc.getWednesday());
            pst.setString(4, spc.getThursday());
            pst.setString(5, spc.getFriday());
            pst.setString(6, spc.getSaturday());
            pst.setString(7, spc.getSunday());
            pst.setInt(8 , spc.getIdcourse());
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteSchedulePerCourseById(int id){
        String query = "delete from schedule_per_course where id_sc_p_c = ?";
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
                System.out.println("The StudentsPerCourse with id "+id+" was not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateSchedulePerCourseById(String monday , String tuesday , String wednesday , String thursday , String friday , String saturday , String sunday , int idcourse){
        String query = "update schedule_per_course set monday = ? , tuesday = ? , wednesday = ? , thursday = ? , friday = ? , saturday = ? , sunday = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, monday);
            pst.setString(2, tuesday);
            pst.setString(3, wednesday);
            pst.setString(4, thursday);
            pst.setString(5, friday);
            pst.setString(6, saturday);
            pst.setString(7, sunday);
            pst.setInt(8, idcourse);
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
