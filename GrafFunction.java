/********************************* 
*  GrafFunction for GrafProg Project *
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.UIManager;

//Class Header
public class GrafFunction extends GrafObject {
    //Instance Variables
    private String functionString;
    private int segLength = 200;
    private GrafProg myOwner;
    private GrafSettings gStuff;
        
    //Constructor
    public GrafFunction(GrafProg sess){
        setGrafType(GrafType.FUNCTION);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        sess.setMessage1(functionString);
    }
    
    //constructor 
    public GrafFunction(GrafProg sess, String fString){
        this(sess);
        setFunction(fString);
       
    }
    
    public GrafFunction(GrafProg sess, String fString, Color c){
        this(sess, fString);
        setGrafColor(c);
       
   }
   

    //drawGraf overri[]ding method in parent GrafObject
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
        double  dx = (xMax - xMin)/segLength;
        double x = xMin;
        gc.setColor(super.getGrafColor());
        while (x < xMax){
        
            try {
                GrafPrimitives.grafLine(gStuff,x, FunctionString.fValue(functionString, x), x + dx , FunctionString.fValue(functionString, x+dx), gc);
            } catch (DomainViolationException e) {  }
            catch (FunctionFormatException e) {}   
            x = x+dx;
         }
        try {
            GrafPrimitives.grafLine(gStuff,x-dx, FunctionString.fValue(functionString, x), xMax , FunctionString.fValue(functionString, x+dx), gc);
        } catch (DomainViolationException e) { }
        catch (FunctionFormatException e) {}   
        gc.setColor(Color.BLACK);
        
    }
    
   public static void createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs);
        gfd.setTitle("FUNCTION");
        gfd.setPointPanel(gfd.addPointPanel());
        gfd.getPointPanel().addF();
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(false, false))); 
        gfd.addDeleterPanel(GrafType.FUNCTION);   
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveFunction(gs,gfd);
                gfd.getDeleter().resetPlotListModel(gfd.getTempList(), GrafType.FUNCTION);    
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveFunction(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        gfd.setModal(true);
        gfd.pack();
        gfd.setVisible(true);
        //return gft
   }
   
      
   
   private static void saveFunction(GrafProg gSess, GrafInputDialog gfd){
         if (gfd.getFinalSave() == true && gfd.getPtPanel().getF().equals("")) return;
         addFunction(gSess, gfd);
         gfd.getPtPanel().setF("");
   }
   
   
   private static void addFunction(GrafProg gSess, GrafInputDialog gfd){
        
        if (!FunctionString.isValidAtXIgnoreDomainError(gfd.getPtPanel().getF(), (gSess.getGrafSettings().getXMax()+gSess.getGrafSettings().getXMin())/2)) { 
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
       
        GrafFunction gf = new GrafFunction(gSess, gfd.getPtPanel().getF(), gfd.getMarkChooser().getColor());
        gfd.getTempList().add(gf);
    }
    
    //Setters and Getters
    public void setFunction(String s){ functionString = s;}
    public String getFunction(){ return functionString;}
    
    public void setSegLength(int sl){segLength = sl;}
    public int getSegLength(){return segLength;}
    
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
