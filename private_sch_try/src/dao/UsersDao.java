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
import java.util.logging.Level;
import java.util.logging.Logger;
import users_login.HeadMaster;
//import private_sch_try.Student;
import users_login.StudentUsers;
import users_login.TrainerUsers;

/**
 *
 * @author CHRIS
 */
public class UsersDao {
    
    private final String URL = "jdbc:mysql://localhost:3306/privateschool?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "*********";
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
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //vriskis an iparxoun username kai pass<<<kai epistrefi id
    public int getIdStudentByLogin(StudentUsers su) {
        String query = "select idstudent from students_user where username = ? and pass= ? ";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id=0;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, su.getUsername());
            pst.setString(2, su.getPass());
            rs = pst.executeQuery();
            rs.next();
            id=rs.getInt(1);
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    //vriskis an iparxoun username kai pass<<<kai epistrefi id
    public int getIdTrainerByLogin(TrainerUsers tu) {
        String query = "select idtrainer from trainers_user where username = ? and pass= ? ";
        //StudentUsers user = new StudentUsers();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id=0;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, tu.getUsername());
            pst.setString(2, tu.getPass());
            rs = pst.executeQuery();
            rs.next();
            id=rs.getInt(1);
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public Boolean getHeadMasterLogin(HeadMaster hd) {
        Boolean bool=false;
        String query = "select iduser from prive_user where username = ? and pass= ? ";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        //int id=0;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, hd.getUsername());
            pst.setString(2, hd.getPass());
            rs = pst.executeQuery();
            rs.next();
            //id=rs.getInt(1);
            if(rs.getInt(1)==1){
                bool=true;
            }
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bool;
    }
    
}
