package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import database.VinkDAOSqlite;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Logic;
import org.junit.Before;

public class LogicTest {
    
    static Logic logic;
    static VinkDAOSqlite vinkDao;
    static ArrayList<String> list;

    @BeforeClass
    public static void setUp() {
        vinkDao = new VinkDAOSqlite("logicTestDatabase.db");
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
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
    }
    
    @Test
    public void deleteVinkWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink");
        logic.deleteVinkByTitle("testTitle");
        
        logic.saveVink("testTitle2", "testType", list, "testComment", "testLink");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle2");
    }
    
    @Test
    public void getVinkByTitleWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment1", "testLink1");        
        logic.saveVink("testTitle2", "testType", list, "testComment2", "testLink2");
        logic.saveVink("testTitle3", "testType", list, "testComment3", "testLink3");
        
        Vink vink = logic.getVinkByTitle("testTitle2");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(1).getComment(), "testComment2");
    }
    
    @Test
    public void updateVinkWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment1", "testLink");
        
        logic.updateVink(1, "testTitle", "testType", list, "testComment2", "testLink");
        
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals("testComment2", "testComment2");
    }
    
    @Test
    public void getAllVinksWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink");        
        logic.saveVink("testTitle2", "testType", list, "testComment", "testLink");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
        assertEquals(vinks.get(1).getHeadline(), "testTitle2");
    }

}
