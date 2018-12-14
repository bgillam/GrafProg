


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * The test class GrafCircleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafCircleTest
{
    private GrafProg  gSess;
    private GrafCircle gCircle;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafCircleTest()
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
       gCircle = new GrafCircle(gSess, 2, 3, 4);
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gCircle.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gCircle.drawGraf((Graphics2D)g);
    }
    
    @Test
    public void createInputDialogTest(){
       gCircle.createInputDialog(gSess);
        
    }
    
      
    @Test
    public void setAndGetColorTest(){
        gCircle.setGrafColor(Color.RED);
        assertEquals(gCircle.getGrafColor(),Color.RED);
        gCircle.setFill(Color.BLUE);
        assertEquals(gCircle.getFill(),Color.BLUE);
    }
    
    @Test 
    public void setAndGetCoords(){
        gCircle.setCx(1);
        gCircle.setCy(2);
        gCircle.setR(3);
        
        double d = .000001;
        assertEquals(gCircle.getCx(),1.0,d);
        assertEquals(gCircle.getCy(),2.0,d);
        assertEquals(gCircle.getR(),3.0,d);
        
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
