    
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
import private_sch_try.AssignmentsPerCourse;
import private_sch_try.Course;
import private_sch_try.Student;
import private_sch_try.StudentsPerCourse;
import private_sch_try.Trainer;
import private_sch_try.TrainersPerCourse;

public class StudentsPerCourseDao {
    
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
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student getStudent(int id) {
        StudentDao tr = new StudentDao();
        return tr.getStudentById(id);
    }

    public Course getCourse(int id) {
        CourseDao c = new CourseDao();
        return c.getCourseById(id);
    }

    public List<Course> getListOfCoursesByStudent(int idtstudent) {   
        List<Course> list = new ArrayList();
        String query = "select * from students_per_course where idstudent = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, idtstudent);
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
                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public void insertStudentsPerCourse(int idcourse , int idstudent) {
        String query = "INSERT INTO students_per_course(idcourse,idstudent) VALUES (?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idcourse);
            pst.setInt(2 , idstudent);
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
    
//    public void insertListStudentsPerCourse(int idcourse , int [] st) {
//        String query = "INSERT INTO students_per_course (idcourse,idstudent) VALUES (?,?)";
//        try {
//            for(int i : st){
//            Connection con = getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//                pst.setInt(1, idcourse);
//                pst.setInt(2 , i);
//            int noumero = pst.executeUpdate();
//            
//            if (noumero > 0) {
//                System.out.println("success");
//            } else {
//                System.out.println("not success");
//            }
//            pst.close();
//            closeConnection();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void deleteStudentsPerCourseById(int id){
        String query = "delete from students_per_course where id_s_p_c = ?";
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
    
    // i apokato sinartisi ksana
    
    
        public ArrayList<StudentsPerCourse> view_s_p_c() {
        String query = "select * from students_per_course where idcourse=?";
        ArrayList<StudentsPerCourse> list= new ArrayList();
        StudentsPerCourse w=new StudentsPerCourse();
        ArrayList<Student> students = new ArrayList();
        //ArrayList<Course> course=new ArrayList();
        ArrayList<Integer> n=new ArrayList();
        CourseDao cd = new CourseDao();
        ArrayList<Integer> cl = cd.getListOfIdCourses();

        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        //int q=cl.size();
        try {   
            for(Integer i : cl){
                //int a = (int) i;
                pst = conn.prepareStatement(query);
                pst.setInt(1, i);
                Statement st = getConnection().createStatement();
                //ResultSet rs = st.executeQuery(query);
                rs = pst.executeQuery();
                while (rs.next()) {
                    students.add(getStudent(rs.getInt("idstudent")));
                }
                w.setStudents(students);
                w.setCourse(getCourse(i));
            
                list.add(w);
                rs.close();
            st.close();
            closeConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerStudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    //telos
        
    //1methodos pou perni ena idcourse kai epistrefi lista me adikimeni studentsPercourse (olous tous student)
             //ArrayList<StudentsPerCourse>
        public StudentsPerCourse getStudentsPerCourseById(int id) {
        String query = "select * from students_per_course where idcourse = ?";
        StudentsPerCourse c = new StudentsPerCourse();
        ArrayList<Student> s=new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                s.add(getStudent(rs.getInt("idstudent")));
            }
            c.setStudents(s);
            c.setCourse(getCourse(id));
        }catch(SQLException x){
            x.printStackTrace();
        }finally{
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }
        
        public void getListOfidCourse_and_seeStudentsPerCourse(){
            StudentsPerCourseDao spc=new StudentsPerCourseDao();
            ArrayList<StudentsPerCourse> ff=new ArrayList();
            CourseDao cd = new CourseDao();
            ArrayList<Integer> cl = cd.getListOfIdCourses();
            for(Integer i : cl){
                System.out.println(spc.getStudentsPerCourseById(i).getCourse());
                for(int q=0;q<spc.getStudentsPerCourseById(i).getStudents().size();q++){
                    System.out.println(spc.getStudentsPerCourseById(i).getStudents().get(q));
                }
                System.out.println("");
                
            }
        }
        
    //1telos
        
    public ArrayList<StudentsPerCourse> getListOfStudentsPerCourse() {
        ArrayList<StudentsPerCourse> list = new ArrayList(); //lista apothikefsis gia studentspercourse
        ArrayList<Student> students = new ArrayList();       //lista gia na apothikevo students mesa sto while gia ENA course
        StudentsPerCourse spc = new StudentsPerCourse(); //antikimeno spc gia na pernao List<Student> kai to course gia to opio psaxno ekini tin stigmi
        CourseDao cd = new CourseDao();                    //to ftiaxno gia na boro na travao ola ta course pou iparxoun 
        ArrayList<Integer> cl = cd.getListOfIdCourses();         //apothikefsi ton idcourse
        String query = "select * from students_per_course where idcourse= ?";
        PreparedStatement pst = null;
        
        Connection conn = getConnection();
        //PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            for (Integer i : cl) {
                Statement st = getConnection().createStatement();
             rs = st.executeQuery(query);
                
                pst = conn.prepareStatement(query);
                pst.setInt(1, i);
                //Statement st = getConnection().createStatement();
                //ResultSet rs = st.executeQuery(query);
                while (rs.next()) {

                    students.add(getStudent(rs.getInt("idstudent")));
                    //tpc.setCourse(getCourse(rs.getInt("idcourse")));
                    //list.add(tpc);
                    //System.out.println(rs);
                }
                spc.setCourse(getCourse(rs.getInt("idcourse")));
                spc.setStudents(students);
                list.add(spc);
            
                rs.close();
                st.close();
                closeConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
//    public List<StudentsPerCourse> getListOfStudentsPerCourses() {
//        List<StudentsPerCourse> list = new ArrayList();
//        StudentsPerCourse spc = new StudentsPerCourse();
//        String query = "select * from Students_Per_Course ";
//        try {
//            Statement st = getConnection().createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                spc.setId_spc(rs.getInt("id_s_p_c"));
//                spc.setStudent(getStudent(rs.getInt("idstudent")));
//                spc.setCourse(getCourse(rs.getInt("idcourse")));
//                list.add(spc);
//            }
//            rs.close();
//            st.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    
//    public StudentsPerCourse getStudentsPerCourseById(int id) {
//        StudentsPerCourse spc = new StudentsPerCourse();
//        String query = "select * from Students_Per_Course where id_spc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, id);
//            rs = pst.executeQuery();
//            rs.next();
//            spc.setId_spc(rs.getInt("id_tpc"));
//            spc.setStudent(getStudent(rs.getInt("idtrainer")));
//            spc.setCourse(getCourse(rs.getInt("idcourse")));
//        }catch(SQLException x){
//            x.printStackTrace();
//        }finally{
//            try {
//                rs.close();
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return spc;
//    }
//    
//    public void insertStudentsPerCourse(int idcourse , int idstudent) {
//        String query = "INSERT INTO trainers_per_course VALUES (?,?)";
//        try {
//            Connection con = getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2 , idstudent);
//            int noumero = pst.executeUpdate();
//            if (noumero > 0) {
//                System.out.println("success");
//            } else {
//                System.out.println("not success");
//            }
//            pst.close();
//            closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void deleteStudentsPerCourseById(int id){
//        String query = "delete from Students_Per_Course where id_spc = ?";
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
//            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//     public void updateStudentsPerCourseById(int id_spc , int idcourse , int idtrainer){
//        String query = "update Trainers_Per_Course set id_course = ? , id_trainer = ? where id_spc = ?";
//        Connection conn = getConnection();
//        PreparedStatement pst = null;
//        try {
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, idcourse);
//            pst.setInt(2, idtrainer);
//            pst.setInt(3, id_spc);
//            int result = pst.executeUpdate();
//            if(result>0){
//                System.out.println("successfully updated");
//            }else{
//                System.out.println("customer not updated");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            try {
//                pst.close();
//                closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
}
