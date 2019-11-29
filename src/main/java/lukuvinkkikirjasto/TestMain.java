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
        dao.createOrResetTables();
        ArrayList<String> test = new ArrayList<>();
        test.add("123");
        Vink vink = new Vink("test", "testType", test, "a comment");
        dao.addVink(vink);
        ArrayList<Vink> vinks = dao.getAllVinks();
        System.out.println(vinks.get(0).getHeadline());
        vink.setDatabaseID(1);
        vink.setHeadline("edited headline test");
        boolean abc = dao.updateVink(vink);
        System.out.println(abc);
        System.out.println(vinks.get(0).getHeadline());
    }
}
