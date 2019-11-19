package domain;

import java.util.ArrayList;

public class Vink {

    private String headline;
    private String type;
    private ArrayList<String> tags;
    private String comment;

    public Vink(String headline, String type, ArrayList<String> tags, String comment) {
        this.headline = headline;
        this.type = type;
        this.tags = tags;
        this.comment = comment;   
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList getTags() {
        return tags;
    }

    public void setTags(ArrayList tags) {
        this.tags = tags;
    }

    public String getConmment() {
        return comment;
    }

    public void setConmment(String conmment) {
        this.comment = conmment;
    }
    
    
}
