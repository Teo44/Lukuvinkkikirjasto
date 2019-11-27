package domain;

import java.util.ArrayList;

public class Vink {

    private String headline;
    private String type;
    private ArrayList<String> tags;
    private String comment;
    private Integer databaseID;

    public Vink(String headline, String type, ArrayList<String> tags, String comment) {
        this.headline = headline;
        this.type = type;
        this.tags = tags;
        this.comment = comment; 
        this.databaseID = -1;
    }
    
    public Vink(String headline, String type, ArrayList<String> tags, String comment, Integer ID) {
        this.headline = headline;
        this.type = type;
        this.tags = tags;
        this.comment = comment;   
        this.databaseID = ID;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(Integer databaseID) {
        this.databaseID = databaseID;
    }
}