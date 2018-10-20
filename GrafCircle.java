import java.awt.Color;
import java.awt.Graphics2D;

//import GrafProg.GrafType;


public class GrafCircle extends GrafObject{
        
        //private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private double cX = 0;
        private double cY = 0;
        private double r = 0;
        private Color fill = Color.WHITE;
        private boolean fillFlag = false;
        //private String yString = "";
    
    

    public GrafCircle(GrafProg sess){
        setGrafType(GrafType.CIRCLE);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
    
     public GrafCircle(GrafProg sess, double x1, double y1, double r1){
        this(sess);
        setCx(x1);
        setCy(y1);
        setR(r1);
        
    }
        
    public GrafCircle(GrafProg sess, double x1, double y1, double r1, Color gColor){
        this(sess, x1, y1, r1);
        setGrafColor(gColor);
                  
    }
    
    
     public void drawGraf(Graphics2D gc){
         //System.out.println(super.getFillFlag());
         if (getFillFlag()) {
               gc.setColor(getFill());
               GrafPrimitives.fillEllipse(gStuff,getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight(), gc );
           }
           gc.setColor(super.getGrafColor());
           GrafPrimitives.grafEllipse(gStuff,getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight(), gc );
           gc.setColor(Color.BLACK);
           gc.setPaint(Color.WHITE);
           
        }
     
   public void setCx(double xval){ cX = xval; }
   public double getCx() { return cX; }
   public void setCy(double xval){ cY = xval; }
   public double getCy() { return cY; }
   public void setR(double rval){ r = rval;}
   public double getR() {return r;}
   public double getUpperLeftX(){ return cX - r; }
   public double getUpperLeftY() { return cY+r; }
   public double getWidth() { return 2*r; }	
   public double getHeight() { return 2*r; }
   public Color getFill(){return fill;}
   public void setFill(Color f){fill = f;}
   public GrafSettings getGrafSettings(){return gStuff;}
   public void setFillFlag(boolean tf){fillFlag = tf;}
   public boolean getFillFlag(){return fillFlag;}   
        
     public String toString(){
           return "Circle("+getCx()+", "+getCy()+"); ("+getR()+", "+ " "+getGrafColor()+")";
       }
}