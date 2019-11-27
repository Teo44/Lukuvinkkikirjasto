package database;

import domain.Vink;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author teo
 */
public class VinkDAO {
    
    private String dbFileName;
    
    public VinkDAO(String dbFileName)   {
        this.dbFileName = dbFileName;
    }
    
    public ArrayList<Vink> getAllVinks()    {
        ArrayList<Vink> vinks = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vink");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())   {
                String headline = rs.getString("headline");
                String type = rs.getString("type");
                String tagsAsString = rs.getString("tags");
                String comment = rs.getString("comment");
                ArrayList<String> tags = new ArrayList<>();
                String[] splitTag = tagsAsString.split(";");
                for (int i = 0; i < splitTag.length; i++) {
                    tags.add(splitTag[i]);
                }
                vinks.add(new Vink(headline, type, tags, comment));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e)   {
            System.out.println(e);
        } 
        return vinks;
    }
    
    public void addVink(Vink vink) {
        
        // make a string from the tag list for saving to SQLite
        String tags = "";
        for (Object s : vink.getTags()) {
            tags += s;
            tags += ";";
        }
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Vink("
                    + "headline, type, tags, comment) VALUES (?, ?, ?, ?)");
            stmt.setString(1, vink.getHeadline());
            stmt.setString(2, vink.getType());
            stmt.setString(3, tags);
            stmt.setString(4, vink.getComment());

            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (Exception e)   {
            System.out.println(e);
        }
    }
    
    public void createTablesIfNotExist()  {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Vink(\n"
                    + "id INTEGER PRIMARY KEY,\n"
                    + "headline varchar(144),\n"
                    + "type varchar(64),\n"
                    + "tags varchar(144),\n"
                    + "comment varchar(144)\n"
                    + ");");
            statement.execute();

            statement.close();
            connection.close();
            // debug
            System.out.println("Created new SQLite database file");
        } catch (Exception e)  {
            System.out.println(e);
        }
    }
    
}
