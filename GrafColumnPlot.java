
/*  GrafFunction for GrafProg Project *
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
    
      public static void createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs); 
        gfd.setTitle("Column Plot");  
        gfd.setColumnChooser(gfd.addColumnChooserPanel(gfd.getColumnsString(),true, false));
        gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(true))); //addMarkPanel(gSess.getGraphics().getFont(), true, false, false, true, false, false, false);
        gfd.addSeparatorPanel();
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.COLUMN)); 
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex())));  
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveColumn(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex())));  
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveColumn(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        gfd.setModal(true);
        gfd.pack();
        gfd.setVisible(true); 
    }
    
    private static void saveColumn(GrafProg gs, GrafInputDialog gfd){
        int col = gfd.getColumnChooser().getInputColumn();
        if (gfd.getFinalSave() == true && col == 0) return; 
        addColumn(gs, gfd); 
        gfd.getColumnChooser().setInputIndex(0);
    
    }
    
    private static void addColumn(GrafProg gs, GrafInputDialog gfd){
        int input = gfd.getInput();
        if (input == 0)  return;
        GrafColumnPlot gPlot = new GrafColumnPlot(gs, input);
        gPlot.setGrafColor(gfd.getMarkChooser().getGrafColor());
        //set correct mark for points
        if (gfd.getMarkChooser().xMark()) gPlot.setMark("x"); 
        else if (gfd.getMarkChooser().oMark()) gPlot.setMark("o"); 
        else if (gfd.getMarkChooser().periodMark()) gPlot.setMark("."); 
        else { String text = gfd.getMarkChooser().getTextMark(); 
        gPlot.setMark(text);} 
        if (gfd.getMarkChooser().isLineGraph()) gPlot.setConnected(true); else gPlot.setConnected(false);
        gfd.getTempList().add(gPlot);
    
    }
    
    public static String[] getPlotList(ArrayList<GrafObject> tempList, ArrayList<Integer> plotIndex){ 
        String con;
        GrafDeletePanel.indexPlots(tempList, GrafType.COLUMN);   
        String[] plotListArray = new String[plotIndex.size()];
        for (int i = 0; i < plotIndex.size(); i++){
             GrafColumnPlot currentColumnPlot = (GrafColumnPlot)tempList.get(plotIndex.get(i)); 
            if (currentColumnPlot.getConnected()) con = "connected"; else con = "discrete";
            plotListArray[i] = "input: "+currentColumnPlot.getColumnNumber()+" "+con;    
        }
       return plotListArray;
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
