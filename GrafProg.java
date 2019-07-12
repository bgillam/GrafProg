/**************************************** 
*  GrafProg  for GrafProg Project       *
*  @author Bill Gillam                  *
*  2/25/15             
*  Main Class for Project
*****************************************/

/* Creates frame, graphing panel and menus. 
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.File;
import javax.swing.border.BevelBorder;

//Class Header
public class GrafProg extends JFrame implements ActionListener, Serializable {
    private static final long serialVersionUID = 1L;
    //instance variables
    private File grafFile = new File("");  //File associated with the current Graf object
    private boolean grafSaved = false;     //has the current graf been saved?
    private GrafPanel grafPanel = new GrafPanel(this); //Graphics Panel
    private int width = 600;
    private int height = 600;
    private GrafSettings grafSet = new GrafSettings(this);  //Stores window settings
    //private GrafPrimitives grafPrim = new GrafPrimitives(this);  //draw line, point or character
    private GrafTable data = new GrafTable(this, 100,10);  //table for data 
    private ArrayList<GrafObject> grafObjectList = new ArrayList<GrafObject>(); //list of objects to be graphed
    private GrafAxes axes = new GrafAxes(this);   //axes object
    private String copiedText = "";    
    private JPanel messagePanel;  
    private int boxPlotsPlotted = 0;              //for formatting multiple boxplots
    private static GrafCalc calc;                 //calculator for enteriung expressions
    //bottom status bar messages
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;
       
   //constructor
    public GrafProg(){
        calc = new GrafCalc();
        calc.setVisible(false);  
        SetupFrame(); 
        grafObjectList.add(axes);
        setTitle("GrafProg");
        data.setTitle("Data:");
    }
           
    //Sets up Frame values
    private void SetupFrame(){
       setTitle("GrafProg");
       setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       //WindowListener ensures "Save" prompt on unsaved session
       addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent event) {
              closeGraf();
          }
       });
       //panel for graphing
       getContentPane().add(grafPanel);
       grafPanel.setPreferredSize(new Dimension(width, height));
       //panel and labels for messages at bottom of frame
       messagePanel = new JPanel();
       messagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
       getContentPane().add(messagePanel, BorderLayout.SOUTH);
       messagePanel.setLayout(new BorderLayout(0, 0));
       message1 = new JLabel("Message Panel");
       messagePanel.add(message1, BorderLayout.WEST);
       message3 = new JLabel("");
       messagePanel.add(message3, BorderLayout.EAST);
       JPanel panel = new JPanel();
       messagePanel.add(panel, BorderLayout.CENTER);
       message2 = new JLabel("");
       panel.add(message2);
       setJMenuBar(GrafMenu.createMenu(this));
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
    }
        
  
   
      //Set Titles and saved status after saving file
    private void setAsSaved(){
          setTitle(grafFile.toString());
          data.setTitle("Data: "+grafFile.toString());
          grafSaved=true;
          repaint();
    }
  
    //Close an open file
    public void closeGraf(){
        if (!grafSaved) 
            switch (JOptionPane.showConfirmDialog(null, "Save File?", "File"+grafFile.toString()+"not saved.", JOptionPane.YES_NO_CANCEL_OPTION)){
            case JOptionPane.YES_OPTION : { GrafFiles.saveFile(this); setAsSaved(); }    
            case JOptionPane.CANCEL_OPTION : { repaint(); return;}
        }    
        data.dispose(); dispose();
    }
  
  
 
    /**
    * This handles the action for the menu choices
    * @param ActionEvent event
    */
    public void actionPerformed(ActionEvent event)
    {  
         About about;
         switch (event.getActionCommand()){
            case "New"                          : { new GrafProg(); break;}
            case "Open"                         : { GrafProg newg = (GrafProg)GrafFiles.openGrafFromFile(); break;}
            case "Import Data"                  : { GrafFiles.importFile();}
            case "Save"                         : { grafFile = GrafFiles.saveFile(this); break;}
            case "Save As"                      : { grafFile = GrafFiles.saveFileAs(this); break; } 
            case "Print"                        : { GrafPrint.printBit(grafPanel); break;}
            case "Printer Setup"                : { GrafPrint.printDiag(); break; }
            case "Exit"                         : { closeGraf(); break; }
            case "Data"                         : { data.setVisible(true); repaint(); }
            case "Left"                         : { grafSet.toggleLeftFlag(); break; }
            case "Hide y-axis"                  : { grafSet.toggleShowYAxis(); break; }
            case "Hide x-axis"                  : { grafSet.toggleShowXAxis();  break;}
            case "Hide X Scale"                 : { grafSet.toggleShowXScale(); break; }
            case "Hide Y Scale"                 : { grafSet.toggleShowYScale();  break; }
            case "Standard"                     : { grafSet.setStandardAxes();  break;}
            case "Graphs On Y-Axis"             : { grafSet.toggleReverseXY(); break;}
            case "Auto"                         : { break;}// not implemented at this time
            case "Calculator"                   : { calc.setVisible(true); break; }
            
            case "About"                        : { About.createInputDialog(this);      break;}
            case "Set"                          : { WindowSizeDialog.createInputDialog(this);   break; } 
            case "Frequency Distribution Table" : { FrequencyChartDialog.createInputDialog(this); break;}
            case "Single Variable Statistics"   : { GrafStatsDialog.createInputDialog(this);    break;}
            case "Regression"                   : { RegressionDialog.createInputDialog(this);   break; }
            
            case "Input"                        : { GrafObject.createGrafObject(GrafType.FUNCTION).createInputDialog(this);    break;}
            case "Value"                        : { GrafObject.createGrafObject(GrafType.FVALUE).createInputDialog(this);      break;}
            case "Point"                        : { GrafObject.createGrafObject(GrafType.POINT).createInputDialog(this);        break;}
            case "Tangent"                      : { GrafObject.createGrafObject(GrafType.TANGENT).createInputDialog(this);  break;}
            case "Chord"                        : { GrafObject.createGrafObject(GrafType.CHORD).createInputDialog(this); break;}
            case "Integrate"                    : { GrafObject.createGrafObject(GrafType.INTEGRAL).createInputDialog(this);     break;}
            case "Roots"                        : { GrafObject.createGrafObject(GrafType.FZERO).createInputDialog(this);  break;}
            case "Line Segment"                 : { GrafObject.createGrafObject(GrafType.LINESEGMENT).createInputDialog(this);   break;}
            case "Rectangle"                    : { GrafObject.createGrafObject(GrafType.RECTANGLE).createInputDialog(this);     break;}
            case "Ellipse"                      : { GrafObject.createGrafObject(GrafType.ELLIPSE).createInputDialog(this); break;}
            case "Circle"                       : { GrafObject.createGrafObject(GrafType.CIRCLE).createInputDialog(this);break;}
            case "Text"                         : { GrafObject.createGrafObject(GrafType.TEXT).createInputDialog(this);  break;}
            case "Scatterplot"                  : { GrafObject.createGrafObject(GrafType.SCATTER).createInputDialog(this);  break;} 
            case "Column Plot"                  : { GrafObject.createGrafObject(GrafType.COLUMN).createInputDialog(this);     break;}
            case "Boxplot"                      : { GrafObject.createGrafObject(GrafType.BOXPLOT).createInputDialog(this);   break;}
            case "Histogram"                    : { GrafObject.createGrafObject(GrafType.HISTOGRAM).createInputDialog(this);  break;}
            case "Distribution Polygon"         : { GrafObject.createGrafObject(GrafType.FREQPOLYGON).createInputDialog(this);   break;}
            case "Ogive"                        : { GrafObject.createGrafObject(GrafType.OGIVE).createInputDialog(this);  break;}
            
            
         }
     
      grafPanel.repaint();
      repaint();
      //printObjectTypes
    }
 
 
   
   //Setters and Getters
   public int getTheWidth(){return width;}
   public void setTheWidth(int w) {width = w;}
   
   public int getTheHeight(){return height;}
   public void setTheHeight(int h) {height = h;}
   
   public File getGrafFile(){return grafFile;}
   public void setGrafFile(File f) {grafFile = f;}
   
   public boolean getgrafSaved(){return grafSaved;}
   public void setgrafSaved(boolean tf){grafSaved = tf;}
   
   public GrafPanel getGrafPanel(){return grafPanel;}
   public void setPanel(GrafPanel gp) {grafPanel = gp;} 
   
   public GrafAxes getAxes(){return axes;}
   public void setAxes(GrafAxes ga){axes = ga;}
   
   public GrafSettings getGrafSettings() {return grafSet;}
   public void setGrafSettings(GrafSettings gs) { grafSet = gs; }
   
   //public GrafPrimitives getGrafPrimitives(){  return grafPrim; }
   //public void setGrafPrim(GrafPrimitives gp){}
      
   public String getCopiedText(){return copiedText;}
   public void setCopiedText(String s){ copiedText = s;}
  
   public void setData(GrafTable dt) { data = dt; }
   public GrafTable getData(){return data;}
   
   public void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;} 
   public ArrayList<GrafObject> getGrafList(){return grafObjectList;}
   
   public void setMessage1(String message){ message1.setText(message); }
   public void setMessage2(String message){ message2.setText(message); }
   public void setMessage3(String message){ message3.setText(message); }
   
   public  String getMessage1(){return message1.getText();}
   public  String getMessage2(){return message2.getText();}
   public  String getMessage3(){return message3.getText();}
   
   public int getBoxPlotsPlotted(){
       return boxPlotsPlotted;
   }
   
   public void incrementBoxPlotsPlotted(){
       boxPlotsPlotted++;
   }
   
   public void decrementBoxPlotsPlotted(){
       boxPlotsPlotted--;
   }
   
   public void zeroBoxPlotsPlotted(){
       boxPlotsPlotted = 0;
   }
   
   public int getNumType(GrafType gType){
       int count = 0;
       for (GrafObject o: grafObjectList)
           if (o.getType().equals(gType)) count++;
       return count;
   }
   
   public static void main(String[] args){
        //GrafProg g = 
        new GrafProg();           
    }
  
   
}