
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.UIManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class RegressionDialog extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JComboBox<String> curveComboBox;
    private GrafProg gSess;
    private JComboBox outputComboBox;
    private JComboBox inputComboBox;
    private JTextField textFieldEquation;
    private JTextField textFieldR;
    private JTable tableResiduals;
    private JTable tableCoef;
    private JPanel panel = new JPanel();
    private DefaultTableModel model;
    private DefaultTableModel coModel;
    
    
        
    /**
     * Create the dialog.
     */
    public RegressionDialog(GrafProg sess) {
        gSess=sess;
        setBounds(100, 100, 525, 417);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        setModal(true);
        setTitle("Regression");
    
        {
            JPanel panel = new JPanel();
            panel.setBackground(UIManager.getColor("Button.background"));
            contentPanel.add(panel, BorderLayout.NORTH);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                panel_1.setBackground(new Color(220, 220, 220));
                panel.add(panel_1, BorderLayout.WEST);
                panel_1.setLayout(new BorderLayout(0, 0));
                {
                    JPanel panel_1_1 = new JPanel();
                    panel_1_1.setBackground(new Color(220, 220, 220));
                    panel_1.add(panel_1_1, BorderLayout.WEST);
                    panel_1_1.setLayout(new BorderLayout(0, 0));
                }
                {
                    JPanel panel_3 = new JPanel();
                    panel_3.setBackground(UIManager.getColor("Button.background"));
                    panel_1.add(panel_3, BorderLayout.SOUTH);
                    panel_3.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_2 = new JPanel();
                        panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                        panel_2.setBackground(UIManager.getColor("Button.background"));
                        panel_3.add(panel_2, BorderLayout.SOUTH);
                        panel_2.setLayout(new BorderLayout(0, 0));
                    }
                }
                {
                    JPanel panel_2 = new JPanel();
                    panel_1.add(panel_2, BorderLayout.NORTH);
                    panel_2.setBackground(new Color(220, 220, 220));
                    panel_2.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_4 = new JPanel();
                        panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                        panel_2.add(panel_4);
                        panel_4.setLayout(new BorderLayout(0, 0));
                        {
                            JPanel panel_3 = new JPanel();
                            panel_4.add(panel_3, BorderLayout.SOUTH);
                            {
                                JLabel lblYCol = new JLabel("Choose Column for Output (y) Values");
                                panel_3.add(lblYCol);
                            }
                            {
                                outputComboBox = new JComboBox();
                                panel_3.add(outputComboBox);
                                outputComboBox.setModel(new javax.swing.DefaultComboBoxModel(gSess.getData().getHeaderArray()));
                                //outputComboBox.addItemListener(new ItemListener() {
                                //    public void itemStateChanged(ItemEvent event) {
                                //        if ((event.getStateChange() == ItemEvent.SELECTED) && !(outputComboBox.getSelectedIndex() == 0)) {
                                             //selectedOutputColumn = outputComboBox.getSelectedIndex();
                                        
                                             
                                //        }
                                //    }
                               // });
                            }
                        }
                        {
                            JPanel panel_3 = new JPanel();
                            panel_4.add(panel_3, BorderLayout.NORTH);
                            {
                                JLabel lblXCol = new JLabel("Choose Column for Input (x) Values");
                                panel_3.add(lblXCol);
                            }
                            {
                                inputComboBox = new JComboBox();
                                panel_3.add(inputComboBox);
                                inputComboBox.setModel(new javax.swing.DefaultComboBoxModel(gSess.getData().getHeaderArray()));
                                
                                //inputComboBox.addItemListener(new ItemListener() {
                                //    public void itemStateChanged(ItemEvent event) {
                                //        if (event.getStateChange() == ItemEvent.SELECTED)  {
                                //           selectedInputColumn = inputComboBox.getSelectedIndex();
                                        //   allowEdits();
                                             
                                //        }
                                //    }
                                //});
                            }
                        }
                        {
                            JPanel panel_3 = new JPanel();
                            panel_3.setBackground(UIManager.getColor("Button.background"));
                            panel_2.add(panel_3, BorderLayout.SOUTH);
                            panel_3.setLayout(new BorderLayout(0, 0));
                        }
                        {
                        }
                    }
                }
            }
            {
                JPanel panel_2 = new JPanel();
                panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                panel.add(panel_2, BorderLayout.EAST);
                panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                {
                    JPanel panel_1 = new JPanel();
                    panel_2.add(panel_1);
                    JPanel panel_1_1 = new JPanel();
                    panel_1.add(panel_1_1);
                    panel_1_1.setLayout(new BorderLayout(0, 0));
                    {
                        JLabel lblDel = new JLabel("Choose curve");
                        panel_1_1.add(lblDel, BorderLayout.NORTH);
                    }
                    curveComboBox = new JComboBox();
                    panel_1_1.add(curveComboBox, BorderLayout.WEST);
                    curveComboBox.setModel(new DefaultComboBoxModel(new String[] {"linear", "quadratic", "cubic", "quartic", "poly5", "poly6", "poly7", "poly8", "exponential"}));
                    {
                        JButton CalcBtn = new JButton("Calc");
                        panel_1.add(CalcBtn);
                        CalcBtn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                doRegression();
                            }
                        });
                                                
                    }
                }
                {
                }
            }
        }
        {
            JPanel panel_2 = new JPanel();
            panel_2.setBackground(new Color(220, 220, 220));
            contentPanel.add(panel_2, BorderLayout.WEST);
            panel_2.setLayout(new BorderLayout(0, 0));
        }
        {
            //JPanel panel = new JPanel();
            panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel.setBackground(UIManager.getColor("Button.background"));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                {
                    {
                        {
                            JPanel panel_2 = new JPanel();
                            panel_2.setBackground(UIManager.getColor("Button.background"));
                            panel.add(panel_2, BorderLayout.WEST);
                            panel_2.setLayout(new BorderLayout(0, 0));
                            {
                                JPanel panel_3 = new JPanel();
                                panel_3.setBackground(new Color(220, 220, 220));
                                panel_2.add(panel_3, BorderLayout.NORTH);
                                panel_3.setLayout(new BorderLayout(0, 0));
                                JPanel panel_1_1 = new JPanel();
                                panel_3.add(panel_1_1, BorderLayout.WEST);
                                panel_1_1.setBackground(new Color(220, 220, 220));
                                panel_1_1.setLayout(new BorderLayout(0, 0));
                                {
                                    JPanel panel_2_1 = new JPanel();
                                    panel_2_1.setBackground(new Color(220, 220, 220));
                                    panel_1_1.add(panel_2_1, BorderLayout.NORTH);
                                    {
                                        panel_2_1.setLayout(new BorderLayout(0, 0));
                                    }
                                }
                            }
                            {
                                JPanel panel_3 = new JPanel();
                                panel.add(panel_3, BorderLayout.NORTH);
                                panel_3.setLayout(new BorderLayout(0, 0));
                                JPanel panel_1 = new JPanel();
                                panel_3.add(panel_1, BorderLayout.SOUTH);
                                panel_1.setBackground(UIManager.getColor("Button.background"));
                                {
                                    JLabel lblY = new JLabel("Y=");
                                    panel_1.add(lblY);
                                }
                                {
                                    textFieldEquation = new JTextField();
                                    textFieldEquation.setEditable(false);
                                    panel_1.add(textFieldEquation);
                                    textFieldEquation.setColumns(25);
                                }
                                {
                                    JLabel lblR = new JLabel("    r = ");
                                    panel_1.add(lblR);
                                }
                                {
                                    textFieldR = new JTextField();
                                    textFieldR.setEditable(false);
                                    panel_1.add(textFieldR);
                                    textFieldR.setColumns(5);
                                }
                            }
                        }
                    }
                }
            }
            {
                tableResiduals = new JTable();
                model = new DefaultTableModel(
                    new Object[][] {
                        {null, "X", "Y", "Yb", "residual"},
                        {"1", null, null, null, null},
                        {"2", null, null, null, null},
                        {"3", null, null, null, null},
                        {"4", null, null, null, null},
                        {"5", null, null, null, null},
                        {"6", null, null, null, null},
                        {"7", null, null, null, null},
                        {"8", null, null, null, null},
                        {"9", null, null, null, null},
                        {"10", null, null, null, null},
                    },
                    new String[] {
                        "New column", "New column", "New column", "New column", "New column"
                    }
                    
                 );
                 tableResiduals.setModel(model);
                 /*{ 
                    boolean[] columnEditables = new boolean[] {
                        true, true, false, true, true
                    };
                    public boolean isCellEditable(int row, int column) {
                        return columnEditables[column];
                    }
                });*/
                tableResiduals.setColumnSelectionAllowed(true);
                tableResiduals.setCellSelectionEnabled(true);
                panel.add(tableResiduals, BorderLayout.CENTER);
            }
            
            
            
            
            
            
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1, BorderLayout.SOUTH);
            }
        }
        {
            tableCoef = new JTable();
            contentPanel.add(tableCoef, BorderLayout.SOUTH);
            coModel = new DefaultTableModel(
                new Object[][] {
                    {"Power", "Coefficient", "Error"},
                    {"Constant", null, null},
                    {"X", null, null},
                },
                new String[] {
                    "New column", "New column", "New column"
                }
            );
            tableCoef.setModel(coModel);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new BorderLayout(0, 0));
            {
                JPanel panel = new JPanel();
                buttonPane.add(panel, BorderLayout.EAST);
                {
                    JButton okButton = new JButton("Done");
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            gSess.repaint();     
                            gSess.getGrafPanel().repaint();
                            dispose();
                            }
                    });
                    panel.add(okButton);
                    okButton.setActionCommand("OK");
                    getRootPane().setDefaultButton(okButton);
                }
            }
        }
    }
    
        
    
    
    public void doRegression(){
            int order = 0;
            int input = inputComboBox.getSelectedIndex();
            int output = outputComboBox.getSelectedIndex();
            if (input == 0) { JOptionPane.showMessageDialog(null,
                    "You must choose an input column for regression.",
                    "Choose an input column",
                    JOptionPane.ERROR_MESSAGE);
                    return;
            }
            if (output == 0) { JOptionPane.showMessageDialog(null,
                    "You must choose an output column for your regression.",
                    "Choose an output column",
                    JOptionPane.ERROR_MESSAGE);
                    return;
            }
            int inputLength = GrafStats.getRidOfNulls(gSess.getData().getColumnValues(input)).length;
            int outputLength = GrafStats.getRidOfNulls(gSess.getData().getColumnValues(output)).length;
            if (inputLength != outputLength) { 
                    JOptionPane.showMessageDialog(null, "X and Y columns must have an equal number of items", "Need Paired Data!", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            
           
                     
            
            String curveType = (String) curveComboBox.getSelectedItem(); 
            switch (curveType){
                case "linear": { order = 1; break; }
                case "quadratic": { order = 2; break; }
                case "cubic": {order = 3; break; }
                case "quartic": {order = 4; break;}
                case "poly5": {order = 5; break;}
                case "poly6": {order = 6; break;}
                case "poly7": {order = 7; break;}
                case "poly8": {order = 8; break;}
            }
            
                 
            Vector xVector = new Vector(gSess.getData().getColumnValues(inputComboBox.getSelectedIndex())); //takes a Double[]
            Vector yVector = new Vector(gSess.getData().getColumnValues(outputComboBox.getSelectedIndex()));
            if (!curveType.equals("exponential")) {
                        
                RegressionMath rm = new RegressionMath(xVector, yVector, order);
                model.setRowCount(inputLength+1);
                for (int i=0; i<inputLength; i++) {
                    tableResiduals.setValueAt(rm.getXVector().getValue(i+1),i+1,1);
                    tableResiduals.setValueAt(rm.getYVector().getValue(i+1),i+1,2);
                    tableResiduals.setValueAt(String.format("%.4f%n",rm.getYCalc().getValue(i+1)),i+1,3);
                    tableResiduals.setValueAt(String.format("%.4f%n",rm.getResiduals().getValue(i+1)),i+1,4);
                }  
                textFieldR.setText(""+String.format("%.4f%n",rm.getRValue()));
                Vector yVect = rm.getCoefficients();
                String yString = "";
                for (int i = yVect.length(); i>2; i--)
                    yString = yString + String.format("%.2f%n",yVect.getValue(i))+"x^"+(i-1)+"+";
                yString = yString + String.format("%.2f%n",yVect.getValue(2))+"x"+"+"+String.format("%.2f%n",yVect.getValue(1));  
                textFieldEquation.setText(yString);     
                coModel.setRowCount(yVect.length()+1);
                for (int i = 3; i <= yVect.length(); i++)
                     tableCoef.setValueAt("x^"+(i-1),i,0);
                //System.out.println("yvect="+yVect.toString());
                for (int i = 1; i <=yVect.length(); i++)    // this not working
                     tableCoef.setValueAt(String.format("%.4f%n",yVect.getValue(i)),i,1);
                for (int i = 1; i <=yVect.length(); i++)    // this not working
                     tableCoef.setValueAt(String.format("%.4f%n",rm.getErrors().getValue(i)),i,2);     
                
                
            } else {
                //exponential stuff
            }
            
            //perform appropriate regression
        
            
           
                
    }
    
    
    
        
            
    private String[] createFunctionListArray(){
        ArrayList<String> functionList = new ArrayList<String>();
        functionList.add("choose");
        for (GrafObject gObject: gSess.getGrafList()){
            if (gObject.getType() == GrafType.FUNCTION){
                GrafFunction funct = (GrafFunction)gObject; 
                functionList.add(funct.getFunction());
            }
        }
        String [] fLArray = new String[functionList.size()];
        fLArray = functionList.toArray(fLArray);
        return fLArray;
    }
    
    public static void createInputDialog(GrafProg gs){
       RegressionDialog rd = new RegressionDialog(gs); 
       rd.setVisible(true); 
       rd.setModal(true); 
    
    }
    
            
}
