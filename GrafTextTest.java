

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * The test class GrafTextTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafTextTest
{
    private GrafProg  gSess;
    private GrafText gText;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafTextTest()
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
       gText = new GrafText(gSess, 2, 3, "x");
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gText.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gText.drawGraf((Graphics2D)g);
    }
    
    @Test
    public void createInputDialogTest(){
        gText.createInputDialog(gSess);
        
    }
    
  
    @Test
    public void setAndGetMarkTest(){
         gText.setText("x");  
         assertEquals(gText.getText(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gText.setGrafColor(Color.RED);
        assertEquals(gText.getGrafColor(),Color.RED);
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
