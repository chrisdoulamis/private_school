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

public class AssignmentsPerStudentsDao {

    private final String URL = "jdbc:mysql://localhost:3306/loco?serverTimezone=UTC";
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

    public void truncateAssignmentsPerStudents() {     //svinoume ts engrafes tou pinaka etsi oste kathe fora pou hed master ...
       String query = "TRUNCATE TABLE assignments_per_students"; // px pai kai ksanavazi kai alous student kai alo assignment...
        try {                                                     //na bori meta na ksanagrafi ola idstudent , idassignment...
            Statement st = getConnection().createStatement();     // apo to quiri assigments per courses per students...
            ResultSet rs = st.executeQuery(query);                //!!! tha to kani aftomata me to pou teliosi o hed master..
            rs.close();
            st.close();
            closeConnection();
            System.out.println("truncate ok");
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Integer> getListOfIdStudentForAPS(){  //edo pernoume ta idstudent apo to assignemnts per course per student
        String query = "select  s.idstudent \n" +
                       "from assignment aaa\n" +
                       "inner join assignments_per_course ass\n" +
                       "on aaa.idassignment=ass.idassignment\n" +
                       "inner join courses c\n" +
                       "on c.idcourse=ass.idcourse\n" +
                       "inner join student_per_course st\n" +
                       "on st.idcourse=c.idcourse\n" +
                       "inner join students s\n" +
                        "on s.idstudent=st.idstudent";
        List<Integer> list=new ArrayList();
        
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
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
       public List<Integer> getListOfIdAssignmentForAPS(){  //edo pernoume ta idassignment apo to assignemnts per courrs per student
        String query = "select  aaa.idassignment \n" +      
                       "from assignment aaa\n" +
                       "inner join assignments_per_course ass\n" +
                       "on aaa.idassignment=ass.idassignment\n" +
                       "inner join courses c\n" +
                       "on c.idcourse=ass.idcourse\n" +
                       "inner join student_per_course st\n" +
                       "on st.idcourse=c.idcourse\n" +
                       "inner join students s\n" +
                        "on s.idstudent=st.idstudent";
        List<Integer> list=new ArrayList();
        
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
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
    public void insertAssignmentsPerStudents_Ids_Dao(){     // edo pera pername ta id ton assignments per students...
        AssignmentsPerStudentsDao aps = new AssignmentsPerStudentsDao();   //kai karfotes times mark=-1 kai submit =-1
        List<Integer> list_idstudent = aps.getListOfIdStudentForAPS();     //tha to kani aftomata me to pou teliosi o hed master
        List<Integer> list_idassignment = aps.getListOfIdAssignmentForAPS();
        int size = list_idstudent.size();
        String query = "INSERT INTO assignments_per_students VALUES (?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            for(int i=0 ; i<size ; i++){
            pst.setInt(1, list_idstudent.get(i));     //gt den perni katefthian
            pst.setInt(2, list_idassignment.get(i)); //  px: list_idstudent[i]
            pst.setInt(3, -1);
            pst.setInt(4, -1);
            }
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAssignmentsPerStudents_Mark(int id_a_p_s , int mark){  //update pou kani o trainer kai vathmologi assignment
                String query = "update assignments_per_students set mark = ? where id_a_p_s = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, mark);
            pst.setInt(2, id_a_p_s);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("AssignmentsPerStudents not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
      public void updateAssignmentsPerStudents_Submit(int idstudent , int idassignment){ //to idstudent tha to perni apo to login tou student/to idassignment tha to perni apo ton student eno prota vlepi ta assigment pou exi
                String query = "update assignments_per_students set submit=1 where idstudent = ? and idassignment = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, idstudent);
            pst.setInt(2, idassignment);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("Student this assignment not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
      public void veiwAssignmentsPerStudents(){ // o trainer vlepi ola ta assigments per student masi me to id_a_p_s gia na pezi bala
         String query = "select aps.id_a_p_s , s.fname , s.lname , a.title , a.description , a.subdatetime , a.omark , a.tmark \n" +
                        " from assignments_per_students aps\n" +
                        "inner join assignments_per_course apc\n" +
                        "on aps.idassignment=apc.idassignment\n" +
                        "inner join student_per_course spc\n" +
                        "on aps.idstudent=spc.idstudent\n" +
                        "inner join students s\n" +
                        "on s.idstudent=spc.idstudent\n" +
                        "inner join assignment a\n" +
                        "on a.idassignment=apc.idassignment\n" +
                        "inner join courses c\n" +
                        "on c.idcourse=spc.idcourse";
         try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(1) + " Name: " + rs.getString(2) + " " + rs.getString(3) + " title: " + rs.getString(4) + " description:  " + rs.getString(5) + " subDateTime: " + rs.getDate(6) + "omark: " + rs.getInt(7) + "tmark: " + rs.getInt(8));
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
}
