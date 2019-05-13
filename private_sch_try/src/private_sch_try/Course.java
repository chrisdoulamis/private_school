
package private_sch_try;

import java.sql.Date;
import java.time.LocalDate;
//import java.util.Date;
import java.util.Objects;

public class Course {
    
    private String title;
    private String stream;
    private String type;
    private String startDate;
    private String endDate;

    
    public Course(){
    
    }
    
    public Course(String title, String stream, String type, String startDate, String endDate) { 
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Course { " + "title=" + title + ", stream=" + stream + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
 }
