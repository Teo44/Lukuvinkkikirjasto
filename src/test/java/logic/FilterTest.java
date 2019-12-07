package logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import java.util.ArrayList;

public class FilterTest {
    
    static Filter filter;
    static ArrayList<String> list;
    static ArrayList<String> list2;
    static ArrayList<Vink> vinkList;

    @BeforeClass
    public static void setUp() {
        vinkList = new ArrayList<>();
        filter = new Filter();
        list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list2 = new ArrayList<>();
        list2.add("tag1");
        list2.add("tag2");
        
        Vink vink1 = new Vink("testTitle", "testType", list, "testComment1", "testLink", "testAuthor");
        Vink vink2 = new Vink("testTitle2", "testType2", list, "testComment2", "testLink2", "testAuthor2");
        Vink vink3 = new Vink("testTitle3", "testType", list, "testComment1", "testLink", "testAuthor3");
        Vink vink4 = new Vink("testTitle4", "testType", list, "testikommentti", "testLink", "testAuthor4");
        Vink vink5 = new Vink("testTitle5", "tyyppi", list2, "kommentti", "testLink", "testAuthor5");
        vinkList.add(vink1);
        vinkList.add(vink2);
        vinkList.add(vink3);
        vinkList.add(vink4);
        vinkList.add(vink5);
        
    }
    
    @Test
    public void filteringByCommentWorks() {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "kommentti");
        assertEquals(2, filteredList.size());        
    }
    
    @Test
    public void filteringByTagsReturnsCorrectAmountOfVinks()  {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "tag2");
        assertEquals(1, filteredList.size());
    }
    
    @Test
    public void filteringByTagsReturnsCorrectVink()  {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "tag2");
        assertEquals("testTitle5", filteredList.get(0).getHeadline());
    }
    
    @Test
    public void filteringIsCaseSensitive() {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "testtype2");
        assertEquals(0, filteredList.size());
    }
    
    @Test
    public void filteringByTypeReturnsCorrectVink()  {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "testType2");
        assertEquals("testTitle2", filteredList.get(0).getHeadline());

    }
    
    @Test
    public void filteringByCommentReturnsCorrectVink()  {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "testComment2");
        assertEquals("testTitle2", filteredList.get(0).getHeadline());
    }
    
    @Test
    public void filteringByLinkReturnsCorrectVink() {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "testLink2");
        assertEquals("testTitle2", filteredList.get(0).getHeadline());
    }
    
    @Test
    public void filteringByAuthorReturnsCorrectVink()   {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "testAuthor5");
        assertEquals("testTitle5", filteredList.get(0).getHeadline());
    }
    
    @Test
    public void filteringAddsColoringToMatches() {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "testTitle3");
        assertEquals("\u001b[32;1mtestTitle3\u001B[0m", filteredList.get(0).getHeadline());
    }
    
    @Test
    public void filteringReturnsEmptyListWithNoMatches()    {
        ArrayList<Vink> filteredList = filter.getFilteredColorisedList(vinkList, "404");
        assertEquals(0, filteredList.size());
    }

}
