
/**
 * GrafObject - Abstract parent of all objects to be drawn
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.UIManager;

import java.util.ArrayList;

abstract public class GrafObject implements Serializable 
{
   private GrafType grType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;
   
   
   protected ColumnChooserPanel columnChooser;
   protected ColorRadioMarkPanel mp;
   protected JDialog jd = new JDialog();
   protected PointPanel ptPanel;
   
   public void drawGraf(Graphics2D g2D){};
   
   public static GrafInputDialog createInputDialog(GrafProg gs){return null;};
   
   public static void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){};
   
   public static GrafObject createGrafObject(GrafType gType){
       switch (gType){
               
                case TEXT: return new GrafText(); 
                case COLUMN: return new GrafText(); 
                case BOXPLOT: return new GrafText();
                case SCATTER:return new GrafText();
                case HISTOGRAM: return new GrafText(); 
                case FREQPOLYGON: return new GrafText(); 
                case OGIVE: return new GrafText(); 
                case POINT: return new GrafText();
                case LINESEGMENT: return new GrafText(); 
                case RECTANGLE: return new GrafText(); 
                case ELLIPSE: return new GrafText();
                case   CIRCLE: return new GrafText();
                case FUNCTION: return new GrafText();
                case FVALUE: return new GrafText();
                case TANGENT: return new GrafText();
                case CHORD: return new GrafText(); 
                case INTEGRAL: return new GrafText(); 
                case FZERO: return new GrafText(); 
                default: return null;
                       
        }
    }
   
   public static void createPanel(){}
   
   public boolean axesAreReversible(){
       switch (grType) {
       //case COLUMN : return true;
       case HISTOGRAM: return true;
       case BOXPLOT:  return true;
       //case SCATTER: return true; 
       case FREQPOLYGON: return true;
       case OGIVE: return true;
       }
       return false;
   }
   
   public String[] getColumnsString(){
       return myOwner.getData().getHeaderStringArray(); 
   }
   
   
   public void setGrafType(GrafType gt){grType = gt;}
   public GrafType getType(){return grType; }
   
   public boolean isMoveable(){ return moveable; } 
   public void setMoveable(boolean tf){ moveable = tf;  }
   public boolean getMoveable(){return moveable;}
   
   public void setOwner(GrafProg owner){myOwner = owner;}
   public GrafProg getOwner(){return myOwner;}
   
   public void setGrafColor(Color c){grafColor = c;   }
   public Color getGrafColor() { return grafColor;}
    
   protected void addColumnChooserPanel(String[] str, boolean inputOn, boolean outputOn) {//ColumnChooserPanel addColumnChooserPanel(String[] str, boolean inputOn, boolean outputOn) {
         ColumnChooserPanel columnChooser;
         columnChooser = new ColumnChooserPanel(str, inputOn, outputOn);
         columnChooser.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
         columnChooser.setBackground(UIManager.getColor("Button.background"));
         jd.add(columnChooser, BorderLayout.NORTH);
         //return columnChooser;
    }  
    
   protected void addMarkPanel(MarkPanel mp){ //MarkPanel addMarkPanel(MarkPanel mp){
        mp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mp.setBackground(UIManager.getColor("Button.background"));
        jd.add(mp, BorderLayout.WEST);
        //return mp;
   }
   
   
   
   public static String[] getPlotList(ArrayList<GrafObject> tempList, ArrayList<Integer> plotIndex, GrafType gType){ 
        String con;
        GrafObject currentObj;
        GrafDeletePanel.indexPlots(tempList, gType);   
        String[] plotListArray = new String[plotIndex.size()];
        for (int i = 0; i < plotIndex.size(); i++){
            //GrafObject 
            currentObj = tempList.get(plotIndex.get(i)); 
            plotListArray[i] = currentObj.toString(); 
        }
       return plotListArray;
 }
   
   protected static void closeGFD(GrafInputDialog gfd){
        gfd.setModal(true);
        gfd.pack();
        gfd.setVisible(true); 
   }
      
}
