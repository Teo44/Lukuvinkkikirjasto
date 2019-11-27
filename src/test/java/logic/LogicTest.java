package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import database.VinkDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Logic;
import org.junit.Before;

public class LogicTest {
    
    static Logic logic;
    static VinkDAO vinkDao;
    static ArrayList<String> list;

    @BeforeClass
    public static void setUp() {
        vinkDao = new VinkDAO("logicTestDatabase.db");
        logic = new Logic(vinkDao);
        list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
    }
    
    @Before
    public void resetTables()    {
        vinkDao.createOrResetTables();
    }

    
    @Test
    public void saveVinkWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
    }
    
    @Test
    public void deleteVinkWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment");
        logic.deleteVinkByTitle("testTitle");
        
        logic.saveVink("testTitle2", "testType", list, "testComment");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle2");
    }
    
    @Test
    public void getAllVinksWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment");        
        logic.saveVink("testTitle2", "testType", list, "testComment");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
        assertEquals(vinks.get(1).getHeadline(), "testTitle2");
    }

}
