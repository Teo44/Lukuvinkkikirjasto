package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import java.util.ArrayList;

public class FilterTest {
    
    static Filter filter;
    static ArrayList<String> list;
    static ArrayList<Vink> vinkList;

    @BeforeClass
    public static void setUp() {
        vinkList = new ArrayList<>();
        filter = new Filter();
        list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        
        Vink vink1 = new Vink("testTitle", "testType", list, "testComment1", "testLink");
        Vink vink2 = new Vink("testTitle2", "testType2", list, "testComment2", "testLink2");
        Vink vink3 = new Vink("testTitle", "testType", list, "testComment1", "testLink");
        Vink vink4 = new Vink("testTitle", "testType", list, "testikommentti", "testLink");
        Vink vink5 = new Vink("testTitle", "tyyppi", list, "kommentti", "testLink");
        vinkList.add(vink1);
        vinkList.add(vink2);
        vinkList.add(vink3);
        vinkList.add(vink4);
        vinkList.add(vink5);
        
    }
    
    @Test
    public void filteringWorks() {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "kommentti");
        assertEquals(2, filteredList.size());        
    }

}
