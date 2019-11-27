package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Vink;
import database.VinkDAO;

public class LogicTest {
    
    static VinkDAO vinkDao;

    @BeforeClass
    public static void setUp() {
        vinkDao = new VinkDAO("fakeDb");
    }

    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
