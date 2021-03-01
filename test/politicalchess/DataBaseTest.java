/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package politicalchess;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author menta
 */
public class DataBaseTest {
    
    public DataBaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connectChessDB method, of class DataBase.
     */
    @Test
    public void testConnectChessDB() throws SQLException
    {
        System.out.println("connectChessDB");
        DataBase instance = new DataBase();
        String actual = instance.connectChessDB();
        
        String expected = instance.url + " connected...";
        
        
        Assert.assertEquals(expected, actual);
    }

}
