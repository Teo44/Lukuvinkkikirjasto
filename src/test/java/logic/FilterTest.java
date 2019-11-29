package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import java.util.ArrayList;
import org.junit.Before;

public class FilterTest {
    
    static Filter filter;
    static Logic logic;
    static ArrayList<String> list;

    @BeforeClass
    public static void setUp() {
        ArrayList<Vink> vinkList = new ArrayList<>();
        //Vink vink = new Vink("testTitle")
    }
    
    @Before
    public void resetTables()    {
        //vinkDao.createOrResetTables();
    }

    
    @Test
    public void filteringWorks() {
        //logic.saveVink("testTitle", "testType", list, "testComment");
        //ArrayList<Vink> vinks = vinkDao.getAllVinks();
        //assertEquals(vinks.get(0).getHeadline(), "testTitle");
    }

}
