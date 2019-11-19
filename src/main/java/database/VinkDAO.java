package database;

import domain.Vink;
import java.sql.*;

/**
 *
 * @author teo
 */
public class VinkDAO {
    
    public void addVink(Vink vink) {
        
        // make a string from the tag list for saving to SQLite
        String tags = "";
        for (Object s : vink.getTags()) {
            tags += s;
            tags += ";";
        }
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./test.db", "sa", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO vink("
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
        String db = "test.db";
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./" + db, "sa", "");
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS vink(\n"
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
