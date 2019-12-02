package database;

import domain.Vink;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VinkDAOTest {
    
    static VinkDAO dao;
    static Vink vink;
    
    @BeforeClass
    public static void setUp()  {
        dao = new VinkDAO("test.db");
        dao.createOrResetTables();
    }
    
    @Before
    public void resetTables()    {
        dao.createOrResetTables();
    }
    
    private void basicSetup()   {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag 1");
        tags.add("tag 2");
        vink = new Vink("headline", "youtube_video", tags, "a comment", "");
        dao.addVink(vink);
    }
    
    @Test
    public void aVinkCanBeAdded()  {
        basicSetup();
        ArrayList<Vink> vinks = dao.getAllVinks();
        assertEquals("headline", vinks.get(0).getHeadline());
    }
    
    @Test
    public void aVinkCanBeDeleted() {
        basicSetup();
        dao.deleteVink(1);
        ArrayList<Vink> vinks = dao.getAllVinks();
        assertEquals(0, vinks.size());
    }
    
    @Test
    public void tryingToDeleteNonExistingVinkReturnsFalse() {
        basicSetup();
        boolean deleted = dao.deleteVink(2);
        assertFalse(deleted);
    }
    
    @Test
    public void tryingToDeleteNonExistingVinkDoesntAffectDatabase() {
        basicSetup();
        ArrayList<Vink> vinks = dao.getAllVinks();
        assertEquals(1, vinks.size());
    }
    
    @Test
    public void aVinkCanBeUpdated()  {
        basicSetup();
        vink.setDatabaseID(1);
        vink.setHeadline("a new headline");
        dao.updateVink(vink);
        ArrayList<Vink> vinks = dao.getAllVinks();
        assertEquals("a new headline", vinks.get(0).getHeadline());
    }
    
    @Test
    public void editingNonExistingVinkReturnsFalse()    {
        basicSetup();
        Vink vink2 = new Vink("headline", "youtube_video", new ArrayList<String>(), "a comment", "", 2);
        boolean edited = dao.updateVink(vink2);
        assertFalse(edited);
    }
}
