

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * The test class GrafSegmentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafSegmentTest
{
    private GrafProg  gSess;
    private GrafSegment gSeg;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafSegmentTest()
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
       gSeg = new GrafSegment(gSess, 2, 3, 4, 5);
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gSeg.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gSeg.drawGraf((Graphics2D)g);
    }
    
    @Test
    public void createInputDialogTest(){
        gSeg.createInputDialog(gSess);
        
    }
    
      
    @Test
    public void setAndGetColorRedTest(){
        gSeg.setGrafColor(Color.RED);
        assertEquals(gSeg.getGrafColor(),Color.RED);
    }
    
    @Test 
    public void setAndGetCoords(){
        gSeg.setX1(1);
        gSeg.setY1(2);
        gSeg.setX2(3);
        gSeg.setY2(4);
        double d = .000001;
        assertEquals(gSeg.getX1(),1.0,d);
        assertEquals(gSeg.getY1(),2.0,d);
        assertEquals(gSeg.getX2(),3.0,d);
        assertEquals(gSeg.getY2(),4.0,d);
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
