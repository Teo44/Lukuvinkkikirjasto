package io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class NetworkTest {
    
    static Network network;
    
    @BeforeClass
    public static void setUp()  {
        network = new Network();
    }
    
    @Test
    public void gettingHarryPotterByISBN()   {
        String[] results = network.fetchBookDetailsByISBN("9780545010221");
        assertEquals("Harry Potter and the Deathly Hallows", results[0]);
        assertArrayEquals(results, new String[] {"Harry Potter and the Deathly Hallows", "J. K. Rowling"});
    }
    
    @Test
    public void gettingBookByValidISBNDoesNotReturnNull() {
        assertNotNull(network.fetchBookDetailsByISBN("9780545010221"));
    }
    
    @Test
    public void invalidISBNReturnsNull()   {
        assertNull(network.fetchBookDetailsByISBN("111111111111"));
    }

}
