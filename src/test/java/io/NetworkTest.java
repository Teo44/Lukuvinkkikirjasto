package io;

import database.VinkDAO;
import database.VinkDAOSqlite;
import domain.Vink;
import java.util.ArrayList;
import logic.Logic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NetworkTest {
    
    static Network network;
    static Logic logic;
    static VinkDAO dao;
    
    @BeforeClass
    public static void setUp()  {
        dao = new VinkDAOSqlite("test.db");
        logic = new Logic(dao);
        network = new Network(logic);
    }
    
    @Before
    public void resetDatabase()    {
        dao.createOrResetTables();
    }
    
    @Test
    public void gettingHarryPotterByISBN()   {
        network.fetchBookDetailsByISBN("9780545010221");
        ArrayList<Vink> vinks = logic.getAllVinks();
        assertEquals("Harry Potter and the Deathly Hallows", vinks.get(0).getHeadline());
        
    }
    
    @Test
    public void gettingBookByValidISBNReturnsTrue() {
        boolean success = network.fetchBookDetailsByISBN("9780545010221");
        assertTrue(success);
    }
    
    @Test
    public void invalidISBNReturnsFalse()   {
        boolean success = network.fetchBookDetailsByISBN("111111111111");
        assertFalse(success);
    }
    
    @Test
    public void invalidISBNDoesntModifyDatabase()   {
        network.fetchBookDetailsByISBN("111111111111");
        ArrayList<Vink> vinks = logic.getAllVinks();
        assertEquals(0, vinks.size());
    }

}
