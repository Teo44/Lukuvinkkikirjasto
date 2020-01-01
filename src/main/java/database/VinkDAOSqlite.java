package database;

import domain.Vink;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author teo
 */
public class VinkDAOSqlite implements VinkDAO {
    
    final private String dbFileName;
    
    public VinkDAOSqlite(String dbFileName)   {
        this.dbFileName = dbFileName;
    }
    
    @Override
    public ArrayList<String> getAllVinksHeadlines() {
        ArrayList<String> headlines = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT headline FROM Vink");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                headlines.add(rs.getString("headline"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return headlines;
    }
    
    @Override
    public ArrayList<Vink> getAllVinks()    {
        ArrayList<Vink> vinks = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vink");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())   {
                Integer databaseID = rs.getInt("id");
                Integer readingStatus = rs.getInt("readingStatus");
                String headline = rs.getString("headline");
                String type = rs.getString("type");
                String tagsAsString = rs.getString("tags");
                String comment = rs.getString("comment");
                String link = rs.getString("link");
                String author = rs.getString("author");
                ArrayList<String> tags = new ArrayList<>();
                String[] splitTag = tagsAsString.split(";");
                for (int i = 0; i < splitTag.length; i++) {
                    tags.add(splitTag[i]);
                }
                vinks.add(new Vink(headline, type, tags, comment, link, readingStatus, author, databaseID));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e)   {
            System.out.println(e);
        } 
        return vinks;
    }
    
    @Override
    public boolean updateVink(Vink updatedVink)   {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("UPDATE Vink "
                    + "SET headline = ?, type = ?, tags = ?, comment = ?, link = ?, readingStatus = ?, author = ?"
                    + "WHERE id = ?");
            stmt.setString(1, updatedVink.getHeadline());
            stmt.setString(2, updatedVink.getType());
            stmt.setString(3, tagListToString(updatedVink.getTags()));
            stmt.setString(4, updatedVink.getComment());
            stmt.setString(5, updatedVink.getLink());
            stmt.setInt(6, updatedVink.getReadingStatus());
            stmt.setString(7, updatedVink.getAuthor());
            stmt.setInt(8, updatedVink.getDatabaseID());
            int modified = stmt.executeUpdate();
            if (modified == 0)  {
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
    
    /**
     * 
     * @param databaseID 
     * @return Returns true if deletion successful
     */
    @Override
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
    
    @Override
    public void addVink(Vink vink) {
        String tags = tagListToString(vink.getTags());
        
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Vink("
                    + "headline, type, tags, comment, link, readingStatus, author) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, vink.getHeadline());
            stmt.setString(2, vink.getType());
            stmt.setString(3, tags);
            stmt.setString(4, vink.getComment());
            stmt.setString(5, vink.getLink());
            stmt.setInt(6, vink.getReadingStatus());
            stmt.setString(7, vink.getAuthor());
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (Exception e)   {
            System.out.println(e);
        }
    }
    
    @Override
    public void createTablesIfNotExist()  {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./" + dbFileName, "sa", "");
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Vink(\n"
                    + "id INTEGER PRIMARY KEY,\n"
                    + "headline varchar(144),\n"
                    + "type varchar(64),\n"
                    + "tags varchar(144),\n"
                    + "comment varchar(144),\n"
                    + "link varchar(144),\n"
                    + "readingStatus INTEGER,\n"
                    + "author varchar(144)"
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
    
    @Override
    public void createOrResetTables()   {
        File dbFile = new File(dbFileName);
        dbFile.delete();
        createTablesIfNotExist();
    }
}
