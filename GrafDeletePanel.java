
/**
 * Loads list of current objects and lets user select items for deletion.
 * 
 * @author (Bill Gillam) 
 * @version (1/1/2017)
 */
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

import java.util.ArrayList;
import java.awt.Color;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GrafDeletePanel extends JPanel
{
    private JLabel deleteMessage;
    private JComboBox<String> deleteComboBox;
    private ArrayList<GrafObject> tempList;
    private GrafType gType;
    private static ArrayList<Integer> plotIndex = new ArrayList<Integer>();
    private GrafInputDialog caller;
   
    
    public GrafDeletePanel(GrafInputDialog c, GrafType gType, ArrayList<GrafObject> tempList)
    {
       //gType = grType;
       //tempList = gList;
       caller = c;  
       setBackground(new Color(220, 220, 220));
       setLayout(new BorderLayout());
       deleteMessage= new JLabel("Choose "+gType+" to delete/edit:");
       add(deleteMessage, BorderLayout.NORTH);
       deleteComboBox = new JComboBox();
       add(deleteComboBox, BorderLayout.CENTER);
       JPanel deleteButtonPanel = new JPanel();
       deleteButtonPanel.setLayout(new BorderLayout());
       JButton deleteButton = new JButton("Delete");
       deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
               if (JOptionPane.showConfirmDialog(null, "Delete "+gType+" "+deleteComboBox.getSelectedItem(), "Delete Plot?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION){ 
                       //setDeleteValues(deleteComboBox.getSelectedIndex(), tempList, gType);
                       deleteIndexedPlot(deleteComboBox.getSelectedIndex(), tempList, gType);  
                       resetPlotListModel(tempList, gType);
               }
            }
        });
       deleteButtonPanel.add(deleteButton, BorderLayout.NORTH);
       
       
       
       JButton editButton = new JButton("Edit");
       editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
               if (JOptionPane.showConfirmDialog(null, "Edit "+gType+" "+deleteComboBox.getSelectedItem(), "Edit Plot?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION){ 
                       setDeleteValues(deleteComboBox.getSelectedIndex(), tempList, gType);
                       deleteIndexedPlot(deleteComboBox.getSelectedIndex(), tempList, gType);  
                       resetPlotListModel(tempList, gType);
               }
            }
        });
        deleteButtonPanel.add(editButton, BorderLayout.CENTER);
       
       
       JButton btnClearAll = new JButton("Clear All");
       btnClearAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    while (plotIndex.size() > 0)
                        deleteIndexedPlot(0, tempList, gType);
                        resetPlotListModel(tempList, gType);
                        
                }
       });
      resetPlotListModel(tempList, gType);
      deleteButtonPanel.add(btnClearAll, BorderLayout.SOUTH);  
      add(deleteButtonPanel, BorderLayout.SOUTH);
    }
          
    public void resetPlotListModel(ArrayList<GrafObject> tempList, GrafType gType){
             deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(getPlotList(tempList, gType)));  
             //caller.getColumnChooser().setInputIndex(0);
    }
    
//    Create list of plots of requested type
    public static String[] getPlotList(ArrayList<GrafObject> tempList,GrafType gType){
        String con;
        indexPlots(tempList, gType);   
        String[] plotListArray = new String[plotIndex.size()];
        for (int i = 0; i < plotIndex.size(); i++){
             
            //Switch statements here!
            switch (gType){
               case TEXT:
                        GrafText currentT = (GrafText)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentT.getX()+", "+currentT.getY()+"); ("+currentT.getText()+")"; 
                        break;
               case COLUMN:
                        GrafColumnPlot currentColumnPlot = (GrafColumnPlot)tempList.get(plotIndex.get(i)); 
                        if (currentColumnPlot.getConnected()) con = "connected"; else con = "discrete";
                        plotListArray[i] = "input: "+currentColumnPlot.getColumnNumber()+" "+con;    
                        break;
               case BOXPLOT:
                        GrafBoxPlot currentBoxPlot = (GrafBoxPlot)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "input: "+currentBoxPlot.getColumnNumber();    
                        break;
               case SCATTER: 
                        GrafScatterPlot currentScatterPlot = (GrafScatterPlot)tempList.get(plotIndex.get(i)); 
                        if (currentScatterPlot.getConnected()) con = "connected"; else con = "discrete";
                        plotListArray[i] = "input: "+currentScatterPlot.getInputColumnNumber()+", output: "+currentScatterPlot.getOutputColumnNumber()+" "+con;    
                        break;
               case HISTOGRAM:
                        GrafHistogram currentHistoPlot = (GrafHistogram)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "input: "+currentHistoPlot.getColumnNumber()+" "+currentHistoPlot.getGrafColor();    
                        break;
              case FREQPOLYGON: 
                        GrafFreqPolygon currentFreqPolygon = (GrafFreqPolygon)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "input: "+currentFreqPolygon.getColumnNumber();  
                        break;
              case OGIVE: 
                       GrafOgive currentOgive = (GrafOgive)tempList.get(plotIndex.get(i)); 
                       plotListArray[i] = "input: "+currentOgive.getColumnNumber();  
                       break;
               
               
               case POINT:
                        GrafPoint currentP = (GrafPoint)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentP.getX()+", "+currentP.getY()+")";   
                        break;
               case LINESEGMENT:
                        GrafSegment currentS = (GrafSegment)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentS.getX1()+", "+currentS.getY1()+"); ("+currentS.getX2()+", "+currentS.getY2()+")";    
                        break;
               case RECTANGLE:
                        GrafRectangle currentR = (GrafRectangle)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentR.getX()+", "+currentR.getY()+"); ("+currentR.getWidth()+", "+currentR.getHeight()+")";                    
                        //plotListArray[i] = currentR.toString();
                        break;
               case ELLIPSE:
                        GrafEllipse currentE = (GrafEllipse)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentE.getX()+", "+currentE.getY()+"); ("+currentE.getWidth()+", "+currentE.getHeight()+")";                    
                        break;
               case CIRCLE:
                        GrafCircle currentCirc = (GrafCircle)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentCirc.getCx()+", "+currentCirc.getCy()+"); ("+currentCirc.getR()+")";                    
                        break;
               case POLYGON:
                        //GrafPolygon currentPolygon = (GrafPolygon)tempList.get(plotIndex.get(i)); 
                        //plotListArray[i] = "input: "+currentPolygon.getColumnNumber();  
                        break;
               case FUNCTION:
                        GrafFunction currentF = (GrafFunction)tempList.get(plotIndex.get(i)); 
                        //plotListArray[i+1] = "Y"+(i)+" ="+currentF.getFunction();        
                        plotListArray[i] = "Y"+(i)+" ="+currentF.getFunction();        
                        break;
                        
               case FVALUE:
                        GrafValue currentV = (GrafValue)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentV.getFunctionString()+", "+currentV.getX()+")";   
                        break;
               case TANGENT:
                        GrafTangent currentTan = (GrafTangent)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "("+currentTan.getFunctionString()+", "+currentTan.getX()+")";   
                        break;
               case CHORD:
                        GrafChord currentC = (GrafChord)tempList.get(plotIndex.get(i)); 
                        plotListArray[i] = "function: "+currentC.getFunctionString()+", X1: "+currentC.getX1()+" , X2: "+currentC.getX2();                 
                        break;
               case INTEGRAL:
                        GrafIntegral currentI = (GrafIntegral)tempList.get(plotIndex.get(i));
                        plotListArray[i] = "function: "+currentI.getFunctionString()+", X1: "+currentI.getX1()+" X2: "+currentI.getX2();                    
                        break;
               case FZERO:
                        GrafZeros currentZ = (GrafZeros)tempList.get(plotIndex.get(i));
                        String str = "function: "+currentZ.getFunctionString()+" roots: ";
                        for (double root: currentZ.getZeroList()) str = str+root+" ";
                        plotListArray[i] = str;                    
                        break;
                 
                default: System.out.println("bad GrafType in GrafDeletePanel!"+gType);
                         break;
        
            }        
            
                          
              
              
        }
                    
        return plotListArray;
        
    }
    
    public void setDeleteValues(int index, ArrayList<GrafObject> tempList, GrafType gType){
        int toBeRemoved = plotIndex.get(index);
        switch (gType){
               
                case TEXT:
                     GrafText tEdit = (GrafText)tempList.get(toBeRemoved);
                     caller.getPointChooser().setX1(tEdit.getX());
                     caller.getPointChooser().setY1(tEdit.getY());
                     caller.getMarkChooser().setTextString(tEdit.getText());
                     caller.getMarkChooser().setGrafFont(tEdit.getFont());
                     caller.getMarkChooser().setColor(tEdit.getGrafColor());
                     break;
                case COLUMN:
                     GrafColumnPlot cEdit = (GrafColumnPlot)tempList.get(toBeRemoved);
                     caller.getColumnChooser().setInputIndex(cEdit.getColumnNumber());
                     caller.getMarkChooser().setMark(cEdit.getMark());
                     caller.getMarkChooser().setConnectedChecked(cEdit.getConnected());
                     caller.getMarkChooser().setColor(cEdit.getGrafColor()); 
                     break;
                case BOXPLOT:
                     GrafBoxPlot bpEdit = (GrafBoxPlot)tempList.get(toBeRemoved);
                     caller.getColumnChooser().setInputIndex(bpEdit.getColumnNumber());
                     caller.getMarkChooser().setColor(bpEdit.getGrafColor()); 
                     break;
                case SCATTER:
                     GrafScatterPlot scEdit = (GrafScatterPlot)tempList.get(toBeRemoved);
                     caller.getColumnChooser().setInputIndex(scEdit.getInputColumnNumber());
                     caller.getColumnChooser().setOutputIndex(scEdit.getOutputColumnNumber());
                     caller.getMarkChooser().setMark(scEdit.getMark());
                     caller.getMarkChooser().setConnectedChecked(scEdit.getConnected());
                     caller.getMarkChooser().setColor(scEdit.getGrafColor()); 
                     break;
               
               case HISTOGRAM:
                    GrafHistogram histEdit = (GrafHistogram)tempList.get(toBeRemoved);
                    caller.getColumnChooser().setInputIndex(histEdit.getColumnNumber());
                    caller.getMarkChooser().setFillChecked(histEdit.getFillFlag());
                    caller.getMarkChooser().setColor(histEdit.getGrafColor());  
                    caller.getMarkChooser().setFillColor(histEdit.getFill());  
                    caller.getHisto().setBegin(histEdit.getBegin());
                    caller.getHisto().setEnd(histEdit.getEnd());
                    caller.getHisto().setnumClassesChecked(histEdit.getByNumClassChecked());
                    caller.getHisto().setNumClasses(histEdit.getNumClasses());
                    caller.getHisto().setClassSize(histEdit.getClassWidth());
                    //caller.getHisto().setRelativeHisto(histEdit.relativeHisto());
                    break;  
               case FREQPOLYGON: 
                    GrafFreqPolygon gfpEdit = (GrafFreqPolygon)tempList.get(toBeRemoved);
                    caller.getColumnChooser().setInputIndex(gfpEdit.getColumnNumber());
                    caller.getMarkChooser().setFillChecked(gfpEdit.getFillFlag());
                    caller.getMarkChooser().setColor(gfpEdit.getGrafColor());  
                    caller.getMarkChooser().setFillColor(gfpEdit.getFill());  
                    caller.getHisto().setBegin(gfpEdit.getBegin());
                    caller.getHisto().setEnd(gfpEdit.getEnd());
                    caller.getHisto().setnumClassesChecked(gfpEdit.getByNumClassChecked());
                    caller.getHisto().setNumClasses(gfpEdit.getNumClasses());
                    caller.getHisto().setClassSize(gfpEdit.getClassWidth());
                    //caller.getHisto().setRelativeHisto(histEdit.relativeHisto());
                    break;   
                        
               case OGIVE: 
                    GrafOgive oEdit = (GrafOgive)tempList.get(toBeRemoved);
                    caller.getColumnChooser().setInputIndex(oEdit.getColumnNumber());
                    caller.getMarkChooser().setFillChecked(oEdit.getFillFlag());
                    caller.getMarkChooser().setColor(oEdit.getGrafColor());  
                    caller.getMarkChooser().setFillColor(oEdit.getFill());  
                    caller.getHisto().setBegin(oEdit.getBegin());
                    caller.getHisto().setEnd(oEdit.getEnd());
                    caller.getHisto().setnumClassesChecked(oEdit.getByNumClassChecked());
                    caller.getHisto().setNumClasses(oEdit.getNumClasses());
                    caller.getHisto().setClassSize(oEdit.getClassWidth());   
                    break;
               case POINT:
                     GrafPoint ptEdit = (GrafPoint)tempList.get(toBeRemoved);
                     caller.getPointChooser().setX1(ptEdit.getX());
                     caller.getPointChooser().setY1(ptEdit.getY());
                     caller.getMarkChooser().setTextString(ptEdit.getMark());
                     //caller.getMarkChooser().setCurrentFont(ptEdit.getFont());
                     caller.getMarkChooser().setColor(ptEdit.getGrafColor());   
                     break;
               case LINESEGMENT:
                     GrafSegment lEdit = (GrafSegment)tempList.get(toBeRemoved);
                     caller.getPointChooser().setX1(lEdit.getX1());
                     caller.getPointChooser().setY1(lEdit.getY1());
                     caller.getPointChooser().setX2(lEdit.getX2());
                     caller.getPointChooser().setY2(lEdit.getY2());
                     //caller.getMarkChooser().setTextString(ptEdit.getText());
                     //caller.getMarkChooser().setCurrentFont(ptEdit.getFont());
                     caller.getMarkChooser().setColor(lEdit.getGrafColor());   
                     break;
               case RECTANGLE:
                     GrafRectangle rEdit = (GrafRectangle)tempList.get(toBeRemoved);
                     caller.getPointChooser().setX1(rEdit.getX());
                     caller.getPointChooser().setY1(rEdit.getY());
                     caller.getPointChooser().setW(rEdit.getWidth());
                     caller.getPointChooser().setH(rEdit.getHeight());
                     caller.getMarkChooser().setFillChecked(rEdit.getFillFlag());
                     caller.getMarkChooser().setColor(rEdit.getGrafColor());  
                     caller.getMarkChooser().setFillColor(rEdit.getFill());  
                     break;
               case ELLIPSE:
                     GrafEllipse ellEdit = (GrafEllipse)tempList.get(toBeRemoved);
                     caller.getPointChooser().setX1(ellEdit.getX());
                     caller.getPointChooser().setY1(ellEdit.getY());
                     caller.getPointChooser().setW(ellEdit.getWidth());
                     caller.getPointChooser().setH(ellEdit.getHeight());
                     caller.getMarkChooser().setFillChecked(ellEdit.getFillFlag());
                     caller.getMarkChooser().setColor(ellEdit.getGrafColor());  
                     caller.getMarkChooser().setFillColor(ellEdit.getFill());  
                     break;
              case   CIRCLE:
                     GrafEllipse circEdit = (GrafEllipse)tempList.get(toBeRemoved);
                     caller.getPointChooser().setX1(circEdit.getX());
                     caller.getPointChooser().setY1(circEdit.getY());
                     caller.getPointChooser().setW(circEdit.getWidth());
                     caller.getPointChooser().setH(circEdit.getHeight());
                     caller.getMarkChooser().setFillChecked(circEdit.getFillFlag());
                     caller.getMarkChooser().setColor(circEdit.getGrafColor());  
                     caller.getMarkChooser().setFillColor(circEdit.getFill());  
                     break;
                     
                     case POLYGON:
                     break;
               case FUNCTION:
                     GrafFunction gEdit = (GrafFunction)tempList.get(toBeRemoved);
                     caller.getPointChooser().setF(gEdit.getFunction());
                     caller.getMarkChooser().setColor(gEdit.getGrafColor());
                     break;
                     
              case FVALUE:
                    GrafTangent fvEdit = (GrafTangent)tempList.get(toBeRemoved);
                     caller.getPointChooser().setF(fvEdit.getFunctionString());
                     caller.getPointChooser().setX1(fvEdit.getX());
                     caller.getMarkChooser().setColor(fvEdit.getGrafColor());
                     break;
                        
              case TANGENT:
                     GrafTangent vEdit = (GrafTangent)tempList.get(toBeRemoved);
                     caller.getPointChooser().setF(vEdit.getFunctionString());
                     caller.getPointChooser().setX1(vEdit.getX());
                     caller.getMarkChooser().setColor(vEdit.getGrafColor());
                     break;
                
              case CHORD:
                     GrafChord chEdit = (GrafChord)tempList.get(toBeRemoved);
                     caller.getPointChooser().setF(chEdit.getFunctionString());
                     caller.getPointChooser().setX1(chEdit.getX1());
                     caller.getPointChooser().setX2(chEdit.getX2());
                     caller.getMarkChooser().setColor(chEdit.getGrafColor());
                     break;  
              case INTEGRAL:
                     GrafIntegral intEdit = (GrafIntegral)tempList.get(toBeRemoved);
                     caller.getPointChooser().setF(intEdit.getFunctionString());
                     caller.getPointChooser().setX1(intEdit.getX1());
                     caller.getPointChooser().setX2(intEdit.getX2());
                     caller.getPointChooser().setN(intEdit.getN());
                     caller.getMarkChooser().setColor(intEdit.getGrafColor());
                     break;
              
               case FZERO:
                     GrafZeros zEdit = (GrafZeros)tempList.get(toBeRemoved);
                     caller.getPointChooser().setF(zEdit.getFunctionString());
                     caller.getPointChooser().setX1(zEdit.getStartX());
                     caller.getPointChooser().setX2(zEdit.getEndX());
                     caller.getPointChooser().setDx(zEdit.getDx());
                     caller.getMarkChooser().setColor(zEdit.getGrafColor());
                     break;       
              default: System.out.println("bad GrafType in GrafDeletePanel!"+gType);
              
                       break;
                       
        }
    }
    
    // deletes plot given it's index. 
    private void deleteIndexedPlot(int index, ArrayList<GrafObject> tempList, GrafType gType ){
                int toBeRemoved = plotIndex.get(index); //get's master index from whole list - not just grafType
                tempList.remove(toBeRemoved);
                plotIndex.remove(index);
                indexPlots(tempList, gType);
                if (caller.getColumnChooser() != null) 
                    caller.getColumnChooser().setInputIndex(0);
                //System.out.println("After Removal: "+tempList);
                //return tempList;
    }
    
    //indexPlots creates an index of positions of GrafObjects of type gType
    private static void indexPlots(ArrayList<GrafObject> tempList, GrafType gType){
            int i = 0;
            plotIndex.clear();
            if (tempList != null)
                for (GrafObject gObject: tempList){
                    if (gObject.getType() == gType) plotIndex.add(i);
                    i++;
                }
    }

    
}


    