package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import java.util.ArrayList;
import static logic.LogicTest.list;
import org.junit.Before;

public class FilterTest {
    
    static Filter filter;
    static ArrayList<String> list;
    ArrayList<Vink> vinkList = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        
        Vink vink1 = new Vink("testTitle", "testType", list, "testComment1", "testLink");
        Vink vink2 = new Vink("testTitle2", "testType2", list, "testComment2", "testLink2");
        Vink vink3 = new Vink("testTitle", "testType", list, "testComment1", "testLink");
        //Vink vink = new Vink("testTitle")
    }
    
    @Test
    public void filteringWorks() {
        ArrayList<Vink> filteredList = filter.filter(vinkList, "test");
        
    }

}
