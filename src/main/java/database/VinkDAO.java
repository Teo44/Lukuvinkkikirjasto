package database;

import domain.Vink;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author teo
 */
public class VinkDAO {
    
    final private String dbFileName;
    
    public VinkDAO(String dbFileName)   {
        this.dbFileName = dbFileName;
    }
    
    public ArrayList<Vink> getAllVinks()    {
        ArrayList<Vink> vinks = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vink");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())   {
                Integer databaseID = rs.getInt("id");
                String headline = rs.getString("headline");
                String type = rs.getString("type");
                String tagsAsString = rs.getString("tags");
                String comment = rs.getString("comment");
                ArrayList<String> tags = new ArrayList<>();
                String[] splitTag = tagsAsString.split(";");
                for (int i = 0; i < splitTag.length; i++) {
                    tags.add(splitTag[i]);
                }
                vinks.add(new Vink(headline, type, tags, comment, databaseID));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e)   {
            System.out.println(e);
        } 
        return vinks;
    }
    
    public boolean updateVink(Vink updatedVink)   {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("UPDATE Vink "
                    + "SET headline = ?, type = ?, tags = ?, comment = ?"
                    + "WHERE id = ?");
            stmt.setString(1, updatedVink.getHeadline());
            stmt.setString(2, updatedVink.getType());
            stmt.setString(3, tagListToString(updatedVink.getTags()));
            stmt.setString(4, updatedVink.getComment());
            stmt.setInt(5, updatedVink.getDatabaseID());
            int modified = stmt.executeUpdate();
            if (modified == 0)  {
                return false;
            }
            System.out.println("dao: " + updatedVink.getHeadline());
            stmt.close();
            connection.close();
            return true;
        } catch (Exception e)   {
            System.out.println(e);
        }
        return false;
    }
    
    /**
     * 
     * @param databaseID 
     * @return Returns true if deletion successful
     */
    public boolean deleteVink(Integer databaseID)   {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Vink "
                    + "WHERE id = ?");
            stmt.setInt(1, databaseID);
            int deleted = stmt.executeUpdate();
            if (deleted == 0)   {
                return false;
            }
            stmt.close();
            connection.close();
            return true;
        } catch (Exception e)   {
            System.out.println(e);
        }
        return false;
    }
    
    public void addVink(Vink vink) {
        String tags = tagListToString(vink.getTags());
        
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
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
    
    private String tagListToString(ArrayList<String> tagList)    {
        String tags = "";
        for (Object s : tagList) {
            tags += s;
            tags += ";";
        }
        return tags;
    }
    
    public void createOrResetTables()   {
        File dbFile = new File(dbFileName);
        dbFile.delete();
        createTablesIfNotExist();
    }
}
