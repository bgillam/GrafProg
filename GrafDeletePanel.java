
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
                       //resetPlotListModel(tempList, gType);
                       deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
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
                       //resetPlotListModel(tempList, gType);
                       deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
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
                        //resetPlotListModel(tempList, gType);
                        deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
                        
                }
       });
      //resetPlotListModel(tempList, gType);
      deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
      deleteButtonPanel.add(btnClearAll, BorderLayout.SOUTH);  
      add(deleteButtonPanel, BorderLayout.SOUTH);
    }
          
    //resets the list of objects for the delete combo box
    // public void resetPlotListModel(ArrayList<GrafObject> tempList, GrafType gType){
                // deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
               // // switch (gType){
                 // // case TEXT: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafText.getPlotList(tempList, plotIndex, GrafType.TEXT))); break;
                 // // case COLUMN: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafColumnPlot.getPlotList(tempList, plotIndex, GrafType.COLUMN))); break;
                 // // case BOXPLOT: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafBoxPlot.getPlotList(tempList, plotIndex, GrafType.BOXPLOT))); break;
                 // // case SCATTER: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafScatterPlot.getPlotList(tempList, plotIndex, GrafType.SCATTER))); break;
                 // // case HISTOGRAM: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafHistogram.getPlotList(tempList, plotIndex, GrafType.HISTOGRAM))); break;
                 // // case FREQPOLYGON: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafFreqPolygon.getPlotList(tempList, plotIndex, GrafType.FREQPOLYGON))); break;
                 // // case OGIVE: deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafOgive.getPlotList(tempList, plotIndex, GrafType.OGIVE))); break;
                 // // case POINT:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafPoint.getPlotList(tempList, plotIndex, GrafType.POINT))); break;
                 // // case LINESEGMENT:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafSegment.getPlotList(tempList, plotIndex, GrafType.LINESEGMENT))); break;
                 // // case RECTANGLE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafRectangle.getPlotList(tempList, plotIndex, GrafType.RECTANGLE))); break;
                 // // case ELLIPSE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafEllipse.getPlotList(tempList, plotIndex, GrafType.ELLIPSE))); break;
                 // // case CIRCLE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafCircle.getPlotList(tempList, plotIndex, GrafType.CIRCLE))); break;
                 // // case FUNCTION:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafFunction.getPlotList(tempList, plotIndex, GrafType.FUNCTION))); break;
                 // // case FVALUE:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafValue.getPlotList(tempList, plotIndex, GrafType.FVALUE))); break;
                 // // case TANGENT:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafTangent.getPlotList(tempList, plotIndex, GrafType.TANGENT))); break;
                 // // case CHORD:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafChord.getPlotList(tempList, plotIndex, GrafType.CHORD))); break;
                 // // case INTEGRAL:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafIntegral.getPlotList(tempList, plotIndex, GrafType.INTEGRAL))); break;
                 // // case FZERO:deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafZeros.getPlotList(tempList, plotIndex, GrafType.FZERO))); break;
             // // }
    // }
    
    

    
    public void setDeleteValues(int index, ArrayList<GrafObject> tempList, GrafType gType){
        int toBeRemoved = plotIndex.get(index);
        switch (gType){
               
                case TEXT: GrafText.setDeleteValues(index, caller, tempList); break;
                case COLUMN: GrafColumnPlot.setDeleteValues(index, caller, tempList); break;
                case BOXPLOT: GrafBoxPlot.setDeleteValues(index, caller, tempList); break;
                case SCATTER:GrafScatterPlot.setDeleteValues(index, caller, tempList); break;
                case HISTOGRAM: GrafHistogram.setDeleteValues(index, caller, tempList); break;
                case FREQPOLYGON: GrafFreqPolygon.setDeleteValues(index, caller, tempList); break;
                case OGIVE: GrafOgive.setDeleteValues(index, caller, tempList); break;
                case POINT: GrafPoint.setDeleteValues(index, caller, tempList); break;
                case LINESEGMENT: GrafSegment.setDeleteValues(index, caller, tempList); break;
                case RECTANGLE: GrafRectangle.setDeleteValues(index, caller, tempList); break;
                case ELLIPSE: GrafEllipse.setDeleteValues(index, caller, tempList); break;
                case   CIRCLE: GrafCircle.setDeleteValues(index, caller, tempList); break;
                case FUNCTION: GrafFunction.setDeleteValues(index, caller, tempList); break;
                case FVALUE: GrafValue.setDeleteValues(index, caller, tempList); break;
                case TANGENT: GrafTangent.setDeleteValues(index, caller, tempList); break;
                case CHORD: GrafChord.setDeleteValues(index, caller, tempList); break;
                case INTEGRAL: GrafIntegral.setDeleteValues(index, caller, tempList); break;
                case FZERO: GrafZeros.setDeleteValues(index, caller, tempList); break;
                default: System.out.println("bad GrafType in GrafDeletePanel!"+gType);   break;
                       
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
    
    public void setPlotIndex(ArrayList<Integer> index){
        plotIndex = index;
    }
    
}


    