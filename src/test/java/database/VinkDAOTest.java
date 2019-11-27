package database;

import domain.Vink;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VinkDAOTest {
    
    static VinkDAO dao;
    
    @BeforeClass
    public static void setUp()  {
        dao = new VinkDAO("test.db");
        dao.createOrResetTables();
    }
    
    @Before
    public void resetTables()    {
        dao.createOrResetTables();
    }
    
    @Test
    public void aVinkCanBeAdded()  {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag 1");
        tags.add("tag 2");
        Vink vink = new Vink("headline", "youtube_video", tags, "a comment");
        dao.addVink(vink);
        ArrayList<Vink> vinks = dao.getAllVinks();
        assertEquals("headline", vinks.get(0).getHeadline());
    }
    
    @Test
    public void aVinkCanBeDeleted() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag 1");
        tags.add("tag 2");
        Vink vink = new Vink("headline", "youtube_video", tags, "a comment");
        dao.addVink(vink);
        dao.deleteVink(1);
        ArrayList<Vink> vinks = dao.getAllVinks();
        assertEquals(0, vinks.size());
    }
}
