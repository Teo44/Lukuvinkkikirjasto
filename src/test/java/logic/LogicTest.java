package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import database.VinkDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Logic;

public class LogicTest {
    
    static Logic logic;
    static VinkDAO vinkDao;

    @BeforeClass
    public static void setUp() {
        vinkDao = new VinkDAO("fakeDb.db");
        logic = new Logic(vinkDao);
    }

    
    @Test
    public void saveVinkWorks() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        logic.saveVink("testTitle", "testType", list, "testComment");
        ArrayList<Vink> vinks = vinkDao.getAllVinks();
        assertEquals(vinks.get(0).getHeadline(), "testTitle");
    }

}
