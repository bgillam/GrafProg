
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
   
    
    public GrafDeletePanel(GrafInputDialog c, 
                        GrafType gType, 
                       // GrafObject gObject,
                    ArrayList<GrafObject> tempList)
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
       //set up delete button
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
       //set up edit button
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
       
       //set up clear button
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
          
    //reseets the list of objects for the delete combo box
    public void resetPlotListModel(ArrayList<GrafObject> tempList, GrafType gType){
               switch (gType){
                 case TEXT: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafText.getPlotList(tempList, plotIndex))); break;
                 case COLUMN: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafColumnPlot.getPlotList(tempList, plotIndex))); break;
                 case BOXPLOT: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafText.getPlotList(tempList, plotIndex))); break;
                 case SCATTER: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafBoxPlot.getPlotList(tempList, plotIndex))); break;
                 case HISTOGRAM: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafText.getPlotList(tempList, plotIndex))); break;
                 case FREQPOLYGON: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafHistogram.getPlotList(tempList, plotIndex))); break;
                 case OGIVE: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafOgive.getPlotList(tempList, plotIndex))); break;
                 case POINT:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafPoint.getPlotList(tempList, plotIndex))); break;
                 case LINESEGMENT:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafSegment.getPlotList(tempList, plotIndex))); break;
                 case RECTANGLE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafRectangle.getPlotList(tempList, plotIndex))); break;
                 case ELLIPSE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafEllipse.getPlotList(tempList, plotIndex))); break;
                 case CIRCLE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafCircle.getPlotList(tempList, plotIndex))); break;
                 case FUNCTION:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafFunction.getPlotList(tempList, plotIndex))); break;
                 case FVALUE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafValue.getPlotList(tempList, plotIndex))); break;
                 case TANGENT:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafTangent.getPlotList(tempList, plotIndex))); break;
                 case CHORD:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafChord.getPlotList(tempList, plotIndex))); break;
                 case INTEGRAL:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafIntegral.getPlotList(tempList, plotIndex))); break;
                 case FZERO:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafZeros.getPlotList(tempList, plotIndex))); break;
             }
             
             
             
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
    public static ArrayList<Integer> indexPlots(ArrayList<GrafObject> tempList, GrafType gType){
            int i = 0;
            plotIndex.clear();
            if (tempList != null)
                for (GrafObject gObject: tempList){
                    if (gObject.getType() == gType) plotIndex.add(i);
                    i++;
                }
                return plotIndex;
    }

     public JComboBox getDeleteComboBox(){
        return deleteComboBox;
        }
        
   public ArrayList<Integer> getPlotIndex(){
       return plotIndex;
    }
    
}


    