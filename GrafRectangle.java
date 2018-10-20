import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrafRectangle extends GrafObject 
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private double x = 0;
        private double y = 0;
        private double width = 0;
        private double height = 0;
        private Color fill = Color.WHITE;
        private boolean fillFlag = false;
        //private String yString = "";
        
        
        
   public GrafRectangle(GrafProg sess){
        setGrafType(GrafType.RECTANGLE);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
   
   public GrafRectangle(GrafProg sess, double x1, double y1, double width, double height){
        this(sess);
        setX(x1);
        setY(y1);
        setWidth(width);
        setHeight(height);
    }
   
     public GrafRectangle(GrafProg sess, double x1, double y1, double width, double height, Color gColor){
        this(sess, x1, y1, width, height);
        setGrafColor(gColor);
    }
   
   
   public void drawGraf(Graphics2D gc){
       if (fillFlag) {
           gc.setColor(fill);
           GrafPrimitives.fillRect(gStuff,x, y, width, height, gc );
       }
      
       gc.setColor(super.getGrafColor());
       GrafPrimitives.grafRect(gStuff,x, y, width, height ,gc);
       gc.setColor(Color.BLACK);
       gc.setPaint(Color.WHITE);
       
    }
    
   public static void createInputDialog(GrafProg gs){
           GrafInputDialog gfd = new GrafInputDialog(gs);          
           gfd.setTitle("RECTANGLE"); 
           gfd.setPointPanel(gfd.addPointPanel());
           gfd.getPointPanel().addX1Y1();
           gfd.getPointPanel().addWH();
           gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(true, false)));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
             
           gfd.addDeleterPanel(GrafType.RECTANGLE); 
           gfd.getCreateButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0    ) {
                    saveRectangle(gs,gfd);
                    gfd.getDeleter().resetPlotListModel(gfd.getTempList(), GrafType.RECTANGLE);    
                }
            });
            gfd.getSaveChanges().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    gfd.setFinalSave(true);
                    saveRectangle(gs,gfd);
                    gs.setGrafList(gfd.getTempList());
                    gfd.dispose();
                }
            });
            gfd.setModal(true);
            gfd.pack();
            gfd.setVisible(true);  
    
   }
   
   public static void saveRectangle(GrafProg gs, GrafInputDialog gfd){
        if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
        addRect(gs, gfd);
        gfd.getPointPanel().blankX1();
        gfd.getPointPanel().blankY1();
        gfd.getPointPanel().blankW();
        gfd.getPointPanel().blankH();
    
    }
    
    public static void addRect(GrafProg gs, GrafInputDialog gfd){
           if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
           if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
           if (Double.isNaN(gfd.getPointPanel().getW())){gfd.NumErrorMessage("Width", "valid number"); return;}
           if (Double.isNaN(gfd.getPointPanel().getH())){gfd.NumErrorMessage("Height", "valid number"); return;}    
           GrafRectangle gPlot = new GrafRectangle(gs, gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getPointPanel().getW(), gfd.getPointPanel().getH(), gfd.getMarkChooser().getColor());
           if (gfd.getMarkChooser().fillChecked()) {
               gPlot.setFillFlag(true);
               gPlot.setFill(gfd.getMarkChooser().getFillColor());
            }
           gfd.getTempList().add(gPlot);  
    
    }
   
   public void setX(double xval){ x = xval; }
   public double getX() { return x; }
   public void setWidth(double val){ width = val; }
   public double getWidth() { return width; }   
   public void setY(double val){ y = val; }
   public double getY() { return y; }
   public void setHeight(double val){ height = val; }
   public double getHeight() { return height; }
   public Color getFill(){return fill;}
   public void setFill(Color f){fill = f;}
   public GrafSettings getGrafSettings(){return gStuff;}
   public void setFillFlag(boolean tf){fillFlag = tf;}
   public boolean getFillFlag(){return fillFlag;}
  
   
   public String toString(){
       return "Rectangle("+getX()+", "+getY()+"); ("+getWidth()+", "+getHeight()+ " "+getGrafColor()+" "+getFill()+")";
   }
   
 }
   


/* Inherited from GrafObject
   private GrafProg.GrafType grafType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;
     
   
   public void drawGraf(Graphics2D g2D){};
   
   public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
   public GrafProg.GrafType getType(){return grafType; }
   
   public boolean isMoveable(){ return moveable; } 
   public void setMoveable(boolean tf){ moveable = tf;  }
   public boolean getMoveable(){return moveable;}
   
   public void setOwner(GrafProg owner){myOwner = owner;}
   public GrafProg getOwner(){return myOwner;}
   
   public void setGrafColor(Color c){grafColor = c;   }
   public Color getGrafColor() { return grafColor;}
  */
   