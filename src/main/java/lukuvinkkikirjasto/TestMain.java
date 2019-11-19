package lukuvinkkikirjasto;

import database.VinkDAO;
import domain.Vink;
import java.util.ArrayList;

/**
 * A class for testing the database
 * @author teo
 */
public class TestMain {

    public static void main(String[] args) throws Exception{
        System.out.println("Hello database");
        
        VinkDAO dao = new VinkDAO("test.db");
        dao.createTablesIfNotExist();
        ArrayList<String> test = new ArrayList<>();
        test.add("123");
        Vink vink = new Vink("test", "testType", test, "a comment");
        dao.addVink(vink);
    }
}
