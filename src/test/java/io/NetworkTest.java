package io;

import database.VinkDAO;
import database.VinkDAOSqlite;
import domain.Vink;
import java.util.ArrayList;
import logic.Logic;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
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
        logic = new Logic(dao, network);
        network = new Network();
    }
    
    @Before
    public void resetDatabase()    {
        dao.createOrResetTables();
    }
    
    @Test
    public void gettingHarryPotterByISBN()   {
        String[] results = network.fetchBookDetailsByISBN("9780545010221");
        assertEquals("Harry Potter and the Deathly Hallows", results[0]);
        assertArrayEquals(results, new String[] {"Harry Potter and the Deathly Hallows", ""});
    }
    
    @Test
    public void gettingBookByValidISBNDoesNotReturnNull() {
        assertNotNull(network.fetchBookDetailsByISBN("9780545010221"));
    }
    
    @Test
    public void invalidISBNReturnsNull()   {
        assertNotNull(network.fetchBookDetailsByISBN("111111111111"));
    }

}
