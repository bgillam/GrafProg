
/*  GrafFunction for GrafProg Project *
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




//Class Header
public class GrafBoxPlot extends GrafObject {
    //Instance Variables
    private int columnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    private String mark =".";
    private boolean showFNS = true;
        
    //Constructor
    public GrafBoxPlot(GrafProg sess){
        setGrafType(GrafType.BOXPLOT);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        table = myOwner.getData();
        sess.setMessage1("BoxPlot for Column "+columnNumber);
    }
    
    //constructor 
    public GrafBoxPlot(GrafProg sess, int column){
        this(sess);
        setColumnNumber(column);
        
    }
    
    public GrafBoxPlot(GrafProg sess, int column, Color c, boolean fns){
        this(sess, column);
        setGrafColor(c);
        setShowFNS(fns);
        
        
    }
    
    //drawGraf overriding method in parent GrafObject
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
      
        double x = xMin;
        gc.setColor(super.getGrafColor());
        double gHeight = myOwner.getGrafSettings().getGrafHeight();
        double numPlots = myOwner.getNumType(GrafType.BOXPLOT);
        double plotted = myOwner.getBoxPlotsPlotted();
        double boxHeight = (gHeight/2)/(numPlots+1);
        double boxCenter = boxHeight + boxHeight*(plotted);
        double[] fns = GrafStats.getFiveNumberSummary(table.getColumnValues(columnNumber));
        if (!myOwner.getGrafSettings().getReverseXY()){
            GrafPrimitives.grafLine(gStuff,fns[0], boxCenter-boxHeight/10 , fns[0] ,boxCenter+boxHeight/10 , gc);  // Min tic
            GrafPrimitives.grafLine(gStuff,fns[0], boxCenter , fns[1] , boxCenter , gc);                           //left whisker
            GrafPrimitives.grafLine(gStuff,fns[1], boxCenter-boxHeight/5 , fns[1] ,boxCenter+boxHeight/5 , gc);    // Q1
            GrafPrimitives.grafLine(gStuff,fns[1], boxCenter-boxHeight/5 , fns[3] ,boxCenter-boxHeight/5 , gc);    //box bottom
            GrafPrimitives.grafLine(gStuff,fns[1], boxCenter+boxHeight/5 , fns[3] ,boxCenter+boxHeight/5 , gc);    //box top
            GrafPrimitives.grafLine(gStuff,fns[2], boxCenter-boxHeight/5 , fns[2] ,boxCenter+boxHeight/5 , gc);    //median
            GrafPrimitives.grafLine(gStuff,fns[3], boxCenter-boxHeight/5 , fns[3] ,boxCenter+boxHeight/5 , gc);    //Q3
            GrafPrimitives.grafLine(gStuff,fns[3], boxCenter , fns[4] , boxCenter , gc);                           //right whisker
            GrafPrimitives.grafLine(gStuff,fns[4], boxCenter-boxHeight/10 , fns[4] ,boxCenter+boxHeight/10 , gc);  // max tic
            if (showFNS)  {
                GrafPrimitives.grafString(gStuff,fns[0], boxCenter-boxHeight/5, ""+fns[0], gc);
                GrafPrimitives.grafString(gStuff,fns[1], boxCenter-boxHeight/5, ""+fns[1], gc);
                GrafPrimitives.grafString(gStuff,fns[2], boxCenter-boxHeight/5, ""+fns[2], gc);
                GrafPrimitives.grafString(gStuff,fns[3], boxCenter-boxHeight/5, ""+fns[3], gc);
                GrafPrimitives.grafString(gStuff,fns[4], boxCenter-boxHeight/5, ""+fns[4], gc);
            }
      
        } 
       //GrafPrimitives.grafString(table.getCellValue(i, columnNumber), i , mark, gc);
        else{
            GrafPrimitives.grafLine(gStuff,boxCenter-boxHeight/10 , fns[0], boxCenter+boxHeight/10 , fns[0], gc);   //Min tic
            GrafPrimitives.grafLine(gStuff,boxCenter, fns[0], boxCenter , fns[1]  , gc);                             // left whisker
            GrafPrimitives.grafLine(gStuff, boxCenter-boxHeight/5 , fns[1] , boxCenter+boxHeight/5 , fns[1], gc);   //Q1
            GrafPrimitives.grafLine(gStuff,boxCenter-boxHeight/5 , fns[1] ,boxCenter-boxHeight/5 , fns[3] , gc);    //box bottom
            GrafPrimitives.grafLine(gStuff,boxCenter+boxHeight/5 , fns[1] ,boxCenter+boxHeight/5 , fns[3] , gc);    //box top
            GrafPrimitives.grafLine(gStuff,boxCenter+boxHeight/5 , fns[2] ,boxCenter-boxHeight/5 , fns[2] , gc);    //Median    
            GrafPrimitives.grafLine(gStuff,boxCenter+boxHeight/5 , fns[3] ,boxCenter-boxHeight/5 , fns[3] , gc);    // Q3
            GrafPrimitives.grafLine(gStuff,boxCenter, fns[3], boxCenter , fns[4]  , gc);                             //right whisker
            GrafPrimitives.grafLine(gStuff,boxCenter-boxHeight/10 , fns[4], boxCenter+boxHeight/10 , fns[4], gc);   //Max tic
            if (showFNS)  {
                GrafPrimitives.grafString(gStuff,boxCenter-boxHeight/5, fns[0], ""+fns[0], gc);
                GrafPrimitives.grafString( gStuff,boxCenter-boxHeight/5, fns[1], ""+fns[1], gc);
                GrafPrimitives.grafString( gStuff,boxCenter-boxHeight/5, fns[2], ""+fns[2], gc);
                GrafPrimitives.grafString(gStuff, boxCenter-boxHeight/5, fns[3], ""+fns[3], gc);
                GrafPrimitives.grafString( gStuff,boxCenter-boxHeight/5, fns[4], ""+fns[4], gc);
            }
        }
            //GrafPrimitives.grafLine(gStuff,GrafStats.getMin(table.getColumnValues(columnNumber)), , x2, y2, gc);
        myOwner.setMessage2("FNS: "+fns[0]+", "+fns[1]+", "+fns[2]+", "+fns[3]+", "+fns[4]);
        myOwner.incrementBoxPlotsPlotted();     
        gc.setColor(Color.BLACK);
    }
    
    public static void createInputDialog(GrafProg gs){
            GrafInputDialog gfd = new GrafInputDialog(gs); 
            gfd.setTitle("BoxPlot"); 
            gfd.setColumnChooser(gfd.addColumnChooserPanel(gfd.getColumnsString(),true, true));
            gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(true, true)));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
            gfd.addDeleterPanel(GrafType.BOXPLOT); 
            gfd.getCreateButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0    ) {
                    saveBoxPlot(gs,gfd);
                    gfd.getDeleter().resetPlotListModel(gfd.getTempList(), GrafType.BOXPLOT);    
                }
            });
            gfd.getSaveChanges().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    gfd.setFinalSave(true);
                    saveBoxPlot(gs,gfd);
                    gs.setGrafList(gfd.getTempList());
                    gfd.dispose();
                }
            });
            gfd.setModal(true);
            gfd.pack();
            gfd.setVisible(true); 
       
    }
    
    private static void saveBoxPlot(GrafProg gs, GrafInputDialog gfd){
            int col = gfd.getColumnChooser().getInputColumn();
            if (gfd.getFinalSave() == true && col == 0) return; 
            addBoxPlot(gs,gfd);
            gfd.getColumnChooser().setInputIndex(0);

    }
    
    private static void addBoxPlot(GrafProg gs, GrafInputDialog gfd){
        int input = gfd.getInput();
        if (input == 0) return;
        GrafBoxPlot gPlot = new GrafBoxPlot(gs, input, gfd.getMarkChooser().getGrafColor(), gfd.getMarkChooser().FNSChecked());
        //gPlot.setGrafColor(markChooser.getColor());
        //if (markChooser.FNSChecked()) gPlot.setShowFNS(true); else gPlot.setShowFNS(false);
        //if (markChooser.verticalChecked()) gPlot.myOwner
        gfd.getTempList().add(gPlot);
    
    
    }
    
    
    
    //Setters and Getters
    public void setColumnNumber(int c){ columnNumber = c;}
    public int getColumnNumber(){ return columnNumber;}
    public void setMark(String m){mark = m;}
    public String getMark(){return mark;}
    public void setShowFNS(boolean tf){showFNS = tf;}
    public boolean getShowFNS(){
        return showFNS;
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
