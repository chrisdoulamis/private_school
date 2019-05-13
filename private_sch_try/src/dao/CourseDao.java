package dao;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import private_sch_try.Course;

public class CourseDao {

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
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Integer> getListOfIdCourses() {
        ArrayList<Integer> list = new ArrayList();
        String query = "select idcourse from courses";
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
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Course> getListOfCourses() {
        ArrayList<Course> list = new ArrayList();
        String query = "select * from courses";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Course c = new Course(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(c);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Course getCourseById(int id) {
        String query = "select * from courses where idcourse = ?";
        Course c = new Course();
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            c.setTitle(rs.getString("title"));
            c.setStream(rs.getString("stream"));
            c.setType(rs.getString("type"));
            c.setStartDate(rs.getString("startDate"));
            c.setEndDate(rs.getString("endDate"));
            //c.setStartDate(makeDate(rs.getDate("startDate")));
            //c.setEndDate(makeDate(rs.getDate("endDate")));

        } catch (SQLException x) {
            x.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    public String makeDate(Date date) {
        //Date dateee = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }
    
    //afto mou ine axristo.to adikatestisa me to apokati.
    public void insertCourse(String title, String stream, String type, String startDate, String endDate){
        //Date d_one = makeDateqq(startDate);
        //Date d_two = makeDateqq(endDate);
        String query = "INSERT INTO courses (title,stream,type,startDate,endDate) VALUES (?,?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, stream);
            pst.setString(3, type);
            pst.setString(4, startDate); //d_one
            pst.setString(5, endDate); //d_two
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertCourse(Course course){
        String query = "INSERT INTO courses (title,stream,type,startDate,endDate) VALUES (?,?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, course.getTitle());
            pst.setString(2, course.getStream());
            pst.setString(3, course.getType());
            pst.setString(4, course.getStartDate()); //d_one
            pst.setString(5, course.getEndDate()); //d_two
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("success");
            } else {
                System.out.println("not success");
            }
            pst.close();
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCourseById(int id) {
        String query = "delete from courses where idcourse = ?";
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
                System.out.println("The course with id " + id + " was not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCourseById(int idcourse, String title, String stream, String type, String startDate, String endDate) {
        String query = "update courses set title = ? , stream = ? , type = ? , startDate = ? , endDate = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, stream);
            pst.setString(3, type);
            pst.setString(4, startDate);
            pst.setString(5, endDate);
            pst.setInt(6, idcourse);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("courses not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCourseTitleById(int idcourse, String title) {
        String query = "update courses set title = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, title);
            pst.setInt(2, idcourse);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCourseStreamById(int idcourse, String stream) {
        String query = "update courses set stream = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, stream);
            pst.setInt(2, idcourse);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCourseTypeById(int idcourse, String type) {
        String query = "update courses set type = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, type);
            pst.setInt(2, idcourse);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCourseStartDateById(int idcourse, String startDate) {
        String query = "update courses set startDate = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, startDate);
            pst.setInt(2, idcourse);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateCourseEndDateById(int idcourse, String endDate) {
        String query = "update courses set endDate = ? where idcourse = ?";
        Connection conn = getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, endDate);
            pst.setInt(2, idcourse);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("customer not updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
