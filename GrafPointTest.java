

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
//import java.awt.Graphics;
/**
 * The test class GrafPointTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafPointTest
{
    private GrafProg  gSess;
    private GrafPoint gPoint;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafPointTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
       gSess = new GrafProg();
       gPoint = new GrafPoint(gSess, 2, 3, "x", Color.BLACK);
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gPoint.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        //gSess.getGrafPanel().repaint();
        //assertNotNull(gSess.getGrafPanel().getGrafCanvas()); //null
        //gPoint.drawGraf(gSess.getGrafPanel().getGrafCanvas());
    }
    
    @Test
    public void createInputDialogTest(){
        gPoint.createInputDialog(gSess);
        
    }
    
  
    @Test
    public void setAndGetMarkTest(){
         gPoint.setMark("x");  
         assertEquals(gPoint.getMark(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gPoint.setGrafColor(Color.RED);
        assertEquals(gPoint.getGrafColor(),Color.RED);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
       gSess.dispose();
    }
}
