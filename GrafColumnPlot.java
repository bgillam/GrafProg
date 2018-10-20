
/*  GrafFunction for GrafProg Project *
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;

//Class Header
public class GrafColumnPlot extends GrafObject {
    //Instance Variables
    private int columnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    private String mark =".";
    private boolean connected = false;
        
    //Constructor
    public GrafColumnPlot(GrafProg sess, int column){
        setGrafType(GrafType.COLUMN);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        setColumnNumber(column);
        table = myOwner.getData();
        sess.setMessage1("Plotting Column "+columnNumber);
    }
    
    //constructor 
    //public GrafColumnPlot(GrafProg sess, int column){
    //    this(sess);
    //  setColumnNumber(column);
        
    //}
    
    //drawGraf overriding method in parent GrafObject
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
      
        double x = xMin;
        gc.setColor(super.getGrafColor());
       
        for (int i = 1; i < table.getNumRows(); i++){
                Double val = table.getCellValue(i, columnNumber);
                if (val != null) {
                    if (myOwner.getGrafSettings().getReverseXY()) GrafPrimitives.grafString(gStuff,val, i , mark, gc);
                    else GrafPrimitives.grafString(gStuff,i,val, mark, gc);
                        
                }
        }
        gc.setColor(Color.BLACK);
    }
    
    //Setters and Getters
    public void setColumnNumber(int c){ columnNumber = c;}
    public int getColumnNumber(){ return columnNumber;}
    public void setMark(String m){mark = m;}
    public String getMark(){return mark;}
    public void setConnected(boolean tf){
        connected = tf;
    }
    public boolean getConnected(){
        return connected;
    }
    
    
    /* Setters and Getters from Parent GrafObject
     *  public void drawGraf(Graphics2D g2D){};
   
        public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
        public GrafProg.GrafType getType(){return grafType; }
   
        public boolean isMoveable(){ return moveable; } 
        public void setMoveable(boolean tf){ moveable = tf;  }
        public boolean getMoveable(){return moveable;}
   
        public void setOwner(GrafProg owner){myOwner = owner;}
        public GrafProg getOwner(){return myOwner;}
   
        public void setGrafColor(Color c){grafColor = Color.BLACK;   }
        public Color getGrafColor() { return grafColor;}
     */
    
}
