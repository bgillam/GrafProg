

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
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
    private ArrayList<GrafObject> aList;
    ArrayList<Integer> indexList;
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
       aList = new ArrayList<GrafObject>();
       for (int i=0; i<10; i++){
           aList.add(new GrafText(gSess));
           aList.add(new GrafPoint(gSess));
       }
       indexList = new ArrayList<Integer>();
       for (int i = 0; i<aList.size(); i++){
           System.out.println("item "+i+": "+aList.get(i).getType());
           if (aList.get(i).getType() == GrafType.POINT) 
               indexList.add(i);
           
       }
             
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gPoint.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gPoint.drawGraf((Graphics2D)g);
    }
    
    @Test
    public void createInputDialogTest(){
        gPoint.createInputDialog(gSess);
        
    }
    
    public String[] getPlotListTest(){
        return gPoint.getPlotList(aList, indexList);
        
    }
    
    @Test
    public void setDeleteValuesTest(){
        GrafInputDialog gid = gPoint.createInputDialog(gSess);
        gid.getDeleter().setPlotIndex(indexList); 
        gPoint.setDeleteValues(3, gid, aList );
    
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
