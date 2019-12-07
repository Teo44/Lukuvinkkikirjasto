package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import database.VinkDAOSqlite;
import io.Network;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;

public class LogicTest {
    
    static Logic logic;
    static VinkDAOSqlite vinkDao;
    static Network network;
    static ArrayList<String> list;

    @BeforeClass
    public static void setUp() {
        vinkDao = new VinkDAOSqlite("logicTestDatabase.db");
        network = new Network();
        logic = new Logic(vinkDao, network);
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
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink", "testAuthor");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
    }
    
    @Test
    public void deleteVinkWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink", "testAuthor");
        logic.deleteVinkByTitle("testTitle");
        
        logic.saveVink("testTitle2", "testType", list, "testComment", "testLink", "testAuthor");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle2");
    }
    
    @Test
    public void getVinkByTitleWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment1", "testLink1", "testAuthor1");        
        logic.saveVink("testTitle2", "testType", list, "testComment2", "testLink2", "testAurhor2");
        logic.saveVink("testTitle3", "testType", list, "testComment3", "testLink3", "testAuthor3");
        
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(1).getComment(), "testComment2");
    }
    
    @Test
    public void getVinkByTitleReturnsNullWithNoMatches()    {
        logic.saveVink("testTitle", "testType", list, "testComment1", "testLink1", "testAuthor1");        
        logic.saveVink("testTitle2", "testType", list, "testComment2", "testLink2", "testAurhor2");
        logic.saveVink("testTitle3", "testType", list, "testComment3", "testLink3", "testAuthor3");
        
        Vink nullVink = logic.getVinkByTitle("testTitle256");
        assertNull(nullVink);
    }
    
    @Test
    public void updateVinkWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment1", "testLink", "testAuthor");
        
        logic.updateVink(1, "testTitle", "testType", list, "testComment2", "testLink", "testAuthor");
        
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals("testComment2", "testComment2");
    }
    
    @Test
    public void getAllVinksWorks() throws SQLException {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink", "testAuthor");        
        logic.saveVink("testTitle2", "testType", list, "testComment", "testLink", "testAuthor");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
        assertEquals(vinks.get(1).getHeadline(), "testTitle2");
    }
    
    @Test
    public void gettingVinksByReadingStatusReturnsCorrectVinks()    {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink", "testAuthor");        
        logic.saveVink("testTitle2", "testType", list, "testComment", "testLink", "testAuthor");
        logic.updateVinkReadingStatus("testTitle", 1);
        logic.updateVinkReadingStatus("testTitle2", 2);
        ArrayList<Vink> vinks = logic.getVinksByReadingStatus(2);
        assertEquals("testTitle2", vinks.get(0).getHeadline());
    }
    
    @Test
    public void updatingVinkReadingStatusWorks()    {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink", "testAuthor");        
        logic.saveVink("testTitle2", "testType", list, "testComment", "testLink", "testAuthor");
        logic.updateVinkReadingStatus("testTitle", 0);
        logic.updateVinkReadingStatus("testTitle", 1);
        Vink vink = logic.getVinkByTitle("testTitle");
        assertEquals(1, vink.getReadingStatus(), 0);
    }
    
    @Test
    public void gettingFilteredListReturnsCorrectVinks()    {
        logic.saveVink("testTitle", "testType", list, "testComment", "testLink", "testAuthor");        
        logic.saveVink("testTitle2", "testType2", list, "testComment2", "testLink2", "testAuthor2");
        ArrayList<Vink> vinks = logic.filterByString("testLink2");
        assertEquals("testTitle2", vinks.get(0).getHeadline());
    }
}
