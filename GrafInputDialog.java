
/**
 * GrafInputDialog creates GrafObjects. 
 * 
 * @author (Bill Gillam) 
 * @version (1/1/2017)
 */

import javax.swing.JDialog;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class GrafInputDialog extends JDialog
{
    //class variables
    private JPanel optionPanel= new JPanel();
    private JPanel exitPanel = new JPanel();
    private PointPanel ptPanel;
    private ColumnChooserPanel columnChooser;  
    private HistoPanel histoPanel;
    private MarkPanel markChooser;
    private GrafDeletePanel deleter;
    private JButton createButton = new JButton("Create");
    private JButton cancelChanges = new JButton("Exit/No Changes"); 
    private JButton saveChanges =  new JButton("Exit/Save All");
    private ArrayList<GrafObject> tempList = new ArrayList<GrafObject>();
    private GrafProg gSess;
    private boolean finalSave = false;
       
    //tester
    public static void main(String[] args){
        GrafProg gSess = new GrafProg();
        GrafInputDialog gid = new GrafInputDialog(gSess, GrafType.SCATTER);
       
    }
    
    
    
    /**
     * Constructor for objects of class GrafInputDialog
     * 
     */
    
    
    public GrafInputDialog(GrafProg gs){
        gSess = gs;
        tempList = (ArrayList<GrafObject>)(gSess.getGrafList().clone()); //make a temporary copy of list to modify. This provides opportunity to cancel or save changes when ending dialog
        getContentPane().setLayout(new BorderLayout());
        addSeparatorPanel();  
                  
        //Create-Save-Exit Options on bottom panel
        optionPanel.setLayout(new BorderLayout());
        optionPanel.add(createButton, BorderLayout.WEST);
        cancelChanges.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                dispose(); 
             }
        });
        exitPanel.add(cancelChanges, BorderLayout.WEST);
        exitPanel.add(saveChanges,BorderLayout.CENTER);
        optionPanel.add(exitPanel, BorderLayout.EAST);
        getContentPane().add(optionPanel, BorderLayout.SOUTH);
    }
    
    
    public GrafInputDialog(GrafProg gs, GrafType gType)
    {
        gSess = gs;
        tempList = (ArrayList<GrafObject>)(gSess.getGrafList().clone()); //make a temporary copy of list to modify. This provides opportunity to cancel or save changes when ending dialog
        getContentPane().setLayout(new BorderLayout());
        
        //Place Panels in Dialog according to needs of graf type
        //Lookup table would save some code repetition here. May refactor later
        switch (gType){
          case  SCATTER: 
                setTitle("ScatterPlot"); 
                columnChooser = addColumnChooserPanel(getColumnsString(),true, true);
                markChooser = addMarkPanel(new ColorRadioMarkPanel(true)); //addMarkPanel(gSess.getGraphics().getFont(), true, false, false, true, false, false, false);
                break;
           case COLUMN:
                setTitle("Column Plot");  
                columnChooser = addColumnChooserPanel(getColumnsString(),true, false);
                markChooser = addMarkPanel(new ColorRadioMarkPanel(true)); //addMarkPanel(gSess.getGraphics().getFont(), true, false, false, true, false, false, false);
                addSeparatorPanel();
                break;
           case BOXPLOT:
                setTitle("BoxPlot"); 
                columnChooser = addColumnChooserPanel(getColumnsString(),true, true);
                markChooser = addMarkPanel(new FillColorMarkPanel(true, true));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
                break;
          case  HISTOGRAM:
                setTitle("Histogram"); 
                histoPanel = addHistoPanel();
                columnChooser = addColumnChooserPanel(getColumnsString(),true, true);
                markChooser = addMarkPanel(new FillColorMarkPanel(true, false));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
                 break;
          case  OGIVE:
                setTitle("Ogive"); 
                histoPanel = addHistoPanel();
                columnChooser = addColumnChooserPanel(getColumnsString(),true, true);
                markChooser = addMarkPanel(new FillColorMarkPanel(false, false));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
                break;
          case  FREQPOLYGON:
                setTitle("Histogram Polygon"); 
                histoPanel = addHistoPanel();
                columnChooser = addColumnChooserPanel(getColumnsString(),true, true);
                markChooser = addMarkPanel(new FillColorMarkPanel(true, false));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
                break;
        
         /*case  LINESEGMENT:
                setTitle("LINESEGMENT"); 
                ptPanel = addPointPanel();
                ptPanel.addX1Y1();
                ptPanel.addX2Y2();
                markChooser = addMarkPanel(new FillColorMarkPanel(false, false));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
                break;
         case RECTANGLE:
                setTitle("RECTANGLE"); 
                
                ptPanel = addPointPanel();
                ptPanel.addX1Y1();
                ptPanel.addWH();
                markChooser = addMarkPanel(new FillColorMarkPanel(true, false));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
                break;*/
         case ELLIPSE:
                setTitle("ELLIPSE"); 
                
                ptPanel = addPointPanel();
                ptPanel.addX1Y1();
                ptPanel.addWH();
                
                markChooser = addMarkPanel(new FillColorMarkPanel(true, false)); 
                break;
        case CIRCLE:
                setTitle("CIRCLE"); 
                
                ptPanel = addPointPanel();
                ptPanel.addX1Y1();
                ptPanel.addR();
                
                markChooser = addMarkPanel(new FillColorMarkPanel(true, false)); 
                break;
        /*case TEXT:
                setTitle("TEXT"); 
                
                ptPanel = addPointPanel();
                ptPanel.addX1Y1();
                
                markChooser = addMarkPanel(new TextMarkPanel());//gSess.getGraphics().getFont(), true, false, false, false, false, false, true);
                break;
        case FUNCTION:
                setTitle("FUNCTION");
                ptPanel = addPointPanel();
                ptPanel.addF();
                
                markChooser = addMarkPanel(new FillColorMarkPanel(false, false)); 
                break;
        case FVALUE:
                setTitle("FVALUE");
                
                ptPanel = addPointPanel();
                ptPanel.setupTan();
                ptPanel.initFx();
                
                markChooser = addMarkPanel(new ColorRadioMarkPanel(false));//gSess.getGraphics().getFont(), true, false, false, true, false, false, false);
                break;
                
        case TANGENT:
                setTitle("TANGENT");
                
                ptPanel = addPointPanel();
                ptPanel.setupTan();
                ptPanel.initFx();
                
                markChooser = addMarkPanel(new ColorRadioMarkPanel(false));
                break;
                
        case CHORD:
                setTitle("CHORD");
                
                ptPanel = addPointPanel();
                ptPanel.setupChord();
                ptPanel.initFx();
                
                markChooser = addMarkPanel(new ColorRadioMarkPanel(false));
                
                break;
                
        case INTEGRAL:
                setTitle("INTEGRAL");
                
                ptPanel = addPointPanel();
                ptPanel.setupIntegral();
                ptPanel.initFx();
                
                markChooser = addMarkPanel(new FillColorMarkPanel(true, false));//gSess.getGraphics().getFont(), true, false, false, false, false, false, false);
                
                break;
                
        case FZERO:
                setTitle("ZEROS");
                
                ptPanel = addPointPanel();
                ptPanel.setupZeros();
                ptPanel.setX1(gs.getGrafSettings().getXMin());
                ptPanel.setX2(gs.getGrafSettings().getXMax());
                ptPanel.initFx();
                
                markChooser = addMarkPanel(new ColorRadioMarkPanel(false));
               
                break;*/  
        case ONEVARSTATS: break;
        case ABOUT: break;
        default: System.out.println("bad GrafType in GrafInputPanel!"+gType);
                break;
        }
        addSeparatorPanel();  
        addDeleterPanel(gType);//Every Graf needs a deleter        
            
        //Create-Save-Exit Options on bottom panel
        optionPanel.setLayout(new BorderLayout());
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                savePlot(gType);
            }
        });
        optionPanel.add(createButton, BorderLayout.WEST);
        cancelChanges.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                dispose(); 
             }
        });
        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                finalSave = true;
                savePlot(gType);
                gSess.setGrafList(tempList);
                dispose(); 
            }
        });
        exitPanel.add(cancelChanges, BorderLayout.WEST);
        exitPanel.add(saveChanges,BorderLayout.CENTER);
        optionPanel.add(exitPanel, BorderLayout.EAST);
        getContentPane().add(optionPanel, BorderLayout.SOUTH);
        //JPanel separatorPanel = new JPanel();
        //separatorPanel.add(new JSeparator(SwingConstants.VERTICAL));
        //getContentPane().add(separatorPanel, BorderLayout.CENTER);
        setModal(true);
        pack();
        setVisible(true);
              
    }
    
    private ColumnChooserPanel addColumnChooserPanel(String[] str, boolean inputOn, boolean outputOn) {
         columnChooser = new ColumnChooserPanel(str, inputOn, outputOn);
         columnChooser.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
         columnChooser.setBackground(UIManager.getColor("Button.background"));
         getContentPane().add(columnChooser, BorderLayout.NORTH);
         return columnChooser;
    }    
    
    public ColumnChooserPanel getColumnChooser(){
        return columnChooser;
    }
    public PointPanel getPointChooser(){
        return ptPanel;
    }
    public MarkPanel getMarkChooser(){
        return markChooser;
   }
    public HistoPanel getHisto(){
        return histoPanel;
    }
    //methods to create and add the various panels
    
   
     
    //Create panel to input points and place NORTH. Used for Shapes
    public PointPanel addPointPanel(){
        ptPanel = new PointPanel(tempList);
        ptPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        ptPanel.setBackground(UIManager.getColor("Button.background"));
        getContentPane().add(ptPanel, BorderLayout.NORTH);
        return ptPanel;
    }
    //create deleter panel and place EAST
    public GrafDeletePanel addDeleterPanel(GrafType gType){
        deleter = new GrafDeletePanel(this, gType, tempList);
        deleter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        deleter.setBackground(UIManager.getColor("Button.background"));
        getContentPane().add(deleter, BorderLayout.EAST);
        return deleter;
    }
   
    //Create mark and color chooser and place WEST
    public MarkPanel addMarkPanel(MarkPanel mp){
        mp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mp.setBackground(UIManager.getColor("Button.background"));
        getContentPane().add(mp, BorderLayout.WEST);
        return mp;
    }
    
    
    private void addSeparatorPanel(){
        JPanel separatorPanel = new JPanel();
        separatorPanel.add(new JSeparator(SwingConstants.VERTICAL));
        getContentPane().add(separatorPanel, BorderLayout.CENTER);
    }
    
    
    private HistoPanel addHistoPanel(){
        histoPanel = new HistoPanel();
        histoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        histoPanel.setBackground(UIManager.getColor("Button.background"));
        Double[] col1 = gSess.getData().getColumnValues(1);
        histoPanel.setBegin(GrafStats.getMin(col1));
        histoPanel.setEnd(GrafStats.getMax(col1));
        histoPanel.setNumClasses(10);
        getContentPane().add(histoPanel, BorderLayout.CENTER);
        return histoPanel;
    }
    
    //Save the plot dependent on gType
    public void savePlot(GrafType gType){
            int col;  
                   
            switch (gType){
               
                case SCATTER: 
                    col = columnChooser.getInputColumn();
                    if (finalSave == true && col == 0) break; 
                    addScatter(gSess);
                    columnChooser.setInputIndex(0);
                    columnChooser.setOutputIndex(0);
                    break;
                case COLUMN: 
                    col = columnChooser.getInputColumn();
                    if (finalSave == true && col == 0) break; 
                    addColumn(gSess); 
                    columnChooser.setInputIndex(0);
                    break;
                case BOXPLOT:
                    col = columnChooser.getInputColumn();
                    if (finalSave == true && col == 0) break; 
                    addBoxPlot(gSess);
                    columnChooser.setInputIndex(0);
                    break;
                case HISTOGRAM:
                    col = columnChooser.getInputColumn();
                    if (finalSave == true && col == 0) break; 
                    addHisto(gSess);
                    columnChooser.setInputIndex(0);
                    break;
                case OGIVE:
                    col = columnChooser.getInputColumn();
                    if (finalSave == true && col == 0) break; 
                    addOgive(gSess);
                    columnChooser.setInputIndex(0);
                    break;
                case FREQPOLYGON:
                    col = columnChooser.getInputColumn();
                    if (finalSave == true && col == 0) break; 
                    addFreqP(gSess);
                    columnChooser.setInputIndex(0);
                    break;

              
                /*case LINESEGMENT:
                    if (finalSave == true && Double.isNaN(ptPanel.getX1())) break; 
                    addSegment(gSess);
                    ptPanel.blankX1();
                    ptPanel.blankY1();
                    ptPanel.blankX2();
                    ptPanel.blankY2();
                    break; 
                case RECTANGLE:
                    if (finalSave == true && Double.isNaN(ptPanel.getX1())) break; 
                    addRect(gSess);
                    ptPanel.blankX1();
                    ptPanel.blankY1();
                    ptPanel.blankW();
                    ptPanel.blankH();
                    break; */
                case ELLIPSE:
                    if (finalSave == true && Double.isNaN(ptPanel.getX1())) break; 
                    addEll(gSess);
                    ptPanel.blankX1();
                    ptPanel.blankY1();
                    ptPanel.blankW();
                    ptPanel.blankH();
                    break; 
                case CIRCLE:
                    if (finalSave == true && Double.isNaN(ptPanel.getX1())) break; 
                    addCirc(gSess);
                    ptPanel.blankX1();
                    ptPanel.blankY1();
                    ptPanel.blankR();
                    break; 
                
                    
            default: System.out.println("bad GrafType in GrafInputPanel!"+gType);
                    break;
            }
            deleter.resetPlotListModel(tempList, gType);   
    }
    
    //Save methods for each type
    private void addScatter(GrafProg gSess){
        int input = getInput();
        int output = getOutput();
        if (input == 0 || output == 0) return;
        GrafScatterPlot gPlot = new GrafScatterPlot(gSess, input, output);
        gPlot.setGrafColor(markChooser.getGrafColor());
        //set correct mark for points
        //markChooser = (ColorRadioMarkPanel)markChooser;
        if (markChooser.xMark()) gPlot.setMark("x"); 
        else if (markChooser.oMark()) gPlot.setMark("o"); 
        else if (markChooser.periodMark()) gPlot.setMark("."); 
        else { String text = markChooser.getTextMark(); 
        gPlot.setMark(text);} 
        if (markChooser.isLineGraph()) gPlot.setConnected(true); else gPlot.setConnected(false);
        tempList.add(gPlot);
    }
    private void addColumn(GrafProg gSess){
        int input = getInput();
         if (input == 0)  return;
        GrafColumnPlot gPlot = new GrafColumnPlot(gSess, input);
        gPlot.setGrafColor(markChooser.getGrafColor());
        //set correct mark for points
        if (markChooser.xMark()) gPlot.setMark("x"); 
        else if (markChooser.oMark()) gPlot.setMark("o"); 
        else if (markChooser.periodMark()) gPlot.setMark("."); 
        else { String text = markChooser.getTextMark(); 
        gPlot.setMark(text);} 
        if (markChooser.isLineGraph()) gPlot.setConnected(true); else gPlot.setConnected(false);
        tempList.add(gPlot);
        
    }
            
    private void addBoxPlot(GrafProg gSess){
        int input = getInput();
        if (input == 0) return;
        GrafBoxPlot gPlot = new GrafBoxPlot(gSess, input, markChooser.getGrafColor(), markChooser.FNSChecked());
        //gPlot.setGrafColor(markChooser.getColor());
        //if (markChooser.FNSChecked()) gPlot.setShowFNS(true); else gPlot.setShowFNS(false);
        //if (markChooser.verticalChecked()) gPlot.myOwner
        tempList.add(gPlot);
    }
    private void addHisto(GrafProg gSess){
      int input = getInput();
      if (input == 0) return;
      GrafHistogram hPlot;
      if (histoPanel.numClassesChecked()){
          hPlot = new GrafHistogram(gSess, input, histoPanel.getBegin(), histoPanel.getEnd(), histoPanel.getNumClasses(), markChooser.getGrafColor(), histoPanel.relativeHisto());
      }else
      {
          hPlot = new GrafHistogram(gSess, input, histoPanel.getBegin(), histoPanel.getEnd(), histoPanel.getClassSize(), markChooser.getGrafColor(), histoPanel.relativeHisto());
      }
             
      if (markChooser.fillChecked()) { hPlot.setFillFlag(true); hPlot.setFill(markChooser.getFillColor()); }
      tempList.add(hPlot);
    }
    private void addFreqP(GrafProg gSess){
    int input = getInput();
      if (input == 0) return;
      GrafFreqPolygon gfpPlot;
      if (histoPanel.numClassesChecked()){
          gfpPlot = new GrafFreqPolygon(gSess, input, histoPanel.getBegin(), histoPanel.getEnd(), histoPanel.getNumClasses(), markChooser.getGrafColor(), histoPanel.relativeHisto());
      }else
      {
          gfpPlot = new GrafFreqPolygon(gSess, input, histoPanel.getBegin(), histoPanel.getEnd(), histoPanel.getClassSize(), markChooser.getGrafColor(), histoPanel.relativeHisto());
      }
      // if (markChooser.verticalChecked()) gPlot.myOwner;
      tempList.add(gfpPlot);
    }
    
    private void addOgive(GrafProg gSess){
    int input = getInput();
      if (input == 0) return;
      GrafOgive oPlot;
      if (histoPanel.numClassesChecked()){
          oPlot = new GrafOgive(gSess, input, histoPanel.getBegin(), histoPanel.getEnd(), histoPanel.getNumClasses(), markChooser.getColor(), histoPanel.relativeHisto());
      }else
      {
          oPlot = new GrafOgive(gSess, input, histoPanel.getBegin(), histoPanel.getEnd(), histoPanel.getClassSize(), markChooser.getColor(), histoPanel.relativeHisto());
      }
      // if (markChooser.verticalChecked()) gPlot.myOwner;
      tempList.add(oPlot);
    }
    
/*    private void addPoint(GrafProg gSess){
       if (Double.isNaN(ptPanel.getX1())){NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY1())){NumErrorMessage("Y1", "valid number"); return;}    
       GrafPoint gPlot = new GrafPoint(gSess, ptPanel.getX1(), ptPanel.getY1(), getMark(), markChooser.getColor());
       tempList.add(gPlot);
    }
    
    
    private void addText(GrafProg gSess){
       if (Double.isNaN(ptPanel.getX1())){NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY1())){NumErrorMessage("Y1", "valid number"); return;}    
       GrafText gPlot = new GrafText(gSess, ptPanel.getX1(), ptPanel.getY1(), markChooser.getTextMark() , markChooser.getFont() , markChooser.getColor());
       tempList.add(gPlot);
    }
    
    
    private void addSegment(GrafProg gSess){
       if (Double.isNaN(ptPanel.getX1())){NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY1())){NumErrorMessage("Y1", "valid number"); return;}    
       if (Double.isNaN(ptPanel.getX2())){NumErrorMessage("x2", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY2())){NumErrorMessage("y2", "valid number"); return;}    
       GrafSegment gPlot = new GrafSegment(gSess, ptPanel.getX1(), ptPanel.getY1(), ptPanel.getX2(), ptPanel.getY2(), markChooser.getColor());
       tempList.add(gPlot);
    }
    
    private void addRect(GrafProg gSess){
     
       if (Double.isNaN(ptPanel.getX1())){NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY1())){NumErrorMessage("Y1", "valid number"); return;}    
       if (Double.isNaN(ptPanel.getW())){NumErrorMessage("Width", "valid number"); return;}
       if (Double.isNaN(ptPanel.getH())){NumErrorMessage("Height", "valid number"); return;}    
       GrafRectangle gPlot = new GrafRectangle(gSess, ptPanel.getX1(), ptPanel.getY1(), ptPanel.getW(), ptPanel.getH(), markChooser.getColor());
       if (markChooser.fillChecked()) {
           gPlot.setFillFlag(true);
           gPlot.setFill(markChooser.getFillColor());
        }
       tempList.add(gPlot);
         }
    */
    private void addEll(GrafProg gSess){
       if (Double.isNaN(ptPanel.getX1())){NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY1())){NumErrorMessage("Y1", "valid number"); return;}    
       if (Double.isNaN(ptPanel.getW())){NumErrorMessage("width", "valid number"); return;}
       if (Double.isNaN(ptPanel.getH())){NumErrorMessage("Height", "valid number"); return;}    
       GrafEllipse gPlot = new GrafEllipse(gSess, ptPanel.getX1(), ptPanel.getY1(), ptPanel.getW(), ptPanel.getH(), markChooser.getColor());
       if (markChooser.fillChecked()) {
           gPlot.setFillFlag(true);
           gPlot.setFill(markChooser.getFillColor());
        }
       tempList.add(gPlot);
    }
    
    private void addCirc(GrafProg gSess){
       if (Double.isNaN(ptPanel.getX1())){NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(ptPanel.getY1())){NumErrorMessage("Y1", "valid number"); return;}    
       if (Double.isNaN(ptPanel.getR())){NumErrorMessage("R", "valid number"); return;}
       GrafCircle gPlot = new GrafCircle(gSess, ptPanel.getX1(), ptPanel.getY1(), ptPanel.getR(), markChooser.getColor());
       if (markChooser.fillChecked()) {
           gPlot.setFillFlag(true);
           gPlot.setFill(markChooser.getFillColor());
       }
       tempList.add(gPlot);
    }
    
    //private helpers
    public void NumErrorMessage(String str, String str2){
        JOptionPane.showMessageDialog(null,
                   "The value entered for "+str+" is not a "+str2 ,
                   "Number Format Error",
                   JOptionPane.ERROR_MESSAGE);    }
    
    private int getInput(){
            int input = columnChooser.getInputColumn();
            if (input == 0) { JOptionPane.showMessageDialog(null,
                   "You must choose an input column for your plot.",
                   "Choose an input column",
                   JOptionPane.ERROR_MESSAGE);
            }
          
            return input;
         
    }
    
    private int getOutput(){
            int output = columnChooser.getOutputColumn();
             //output++;
             
             if (output == 0) { JOptionPane.showMessageDialog(null,
                    "You must choose an output column for your plot.",
                    "Choose an output column",
                   JOptionPane.ERROR_MESSAGE);
             }
             return output;
    }
    
    private String[] getColumnsString(){
         String[] colArray = {"","col1","col2","col3","col4","col5"};
        //need procedure to create this string from existing data spreadsheet numCols.
        //switch statement for different types setup 
        return colArray;
    }
    
    public String getMark(){
        if (markChooser.xMark())return "x"; 
        if (markChooser.oMark()) return "o"; 
        if (markChooser.periodMark()) return "."; 
        else return markChooser.getTextMark(); 
    }
    
    public void setMarkChooser(MarkPanel mp){
        markChooser = mp;
    }
    
    public void setPointPanel(PointPanel p){
    ptPanel = p;
    
    }
    
    public PointPanel getPointPanel(){
        return ptPanel;
    
    }

    public JButton getCreateButton(){
        return createButton;
    
    }
    
    public JButton getSaveChanges(){
        return saveChanges;
    }
    
    public void setFinalSave(boolean tf){
        finalSave = tf;
    }
    
    public boolean getFinalSave(){
        return finalSave;
    }
    
    public GrafProg getSess(){
        return gSess;
    }
    
    public ArrayList<GrafObject> getTempList(){
            return tempList;
    }
    
    public void setTemplist(ArrayList<GrafObject> tl){
        tempList = tl;
    }
    
    public GrafDeletePanel getDeleter(){
        return deleter;
    
    }
    
    public PointPanel getPtPanel(){
        return ptPanel;
    }
    
}
