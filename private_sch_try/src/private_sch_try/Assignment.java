        
package private_sch_try;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment {
    
    private String title;
    private String description;
    private String subDateTime;
    private int omark;
    private int tmark;

    public Assignment(){
    
    }

    public Assignment(String title, String description, String subDateTime, int omark, int tmark) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.omark = omark;
        this.tmark = tmark;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(String subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getOmark() {
        return omark;
    }

    public void setOmark(int omark) {
        this.omark = omark;
    }

    public int getTmark() {
        return tmark;
    }

    public void setTmark(int tmark) {
        this.tmark = tmark;
    }

    @Override
    public String toString() {
        return "Assignment { " + "title=" + title + ", description=" + description + ", subDateTime=" + subDateTime + ", omark=" + omark + ", tmark=" + tmark + '}';
    }
    
    
    
}
