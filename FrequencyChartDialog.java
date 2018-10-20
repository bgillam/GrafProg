
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FrequencyChartDialog extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private ArrayList<Integer> boxplotIndex = new ArrayList<Integer>();  // indexes which grafList objects are boxplots
    private GrafProg gSess;
    private JComboBox columnComboBox;
    private JTable table;
    private JTextField textFieldEnd;
    private JTextField textFieldBegin;
    private JTextField textFieldClassNumber;
    private JTextField textFieldClassSize;
    private JRadioButton numberOfClassesRadioBtn;
    private JRadioButton classSizeRadioBtn;
    private JButton chckbxRound;
    
    
        
    /**
     * Create the dialog.
     */
    public FrequencyChartDialog(GrafProg sess) {
        gSess=sess;
        setBounds(100, 100, 543, 359);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        setModal(true);
        setTitle("Frequency Table");
        //indexBoxplots();
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
                                JLabel lblChooseColumnFor = new JLabel("Choose Column for Input Values");
                                lblChooseColumnFor.setForeground(Color.BLUE);
                                panel_3.add(lblChooseColumnFor);
                            }
                            {
                                columnComboBox = new JComboBox();
                                panel_3.add(columnComboBox);
                                columnComboBox.setModel(new javax.swing.DefaultComboBoxModel(gSess.getData().getHeaderArray()));
                                columnComboBox.addItemListener(new ItemListener() {
                                    public void itemStateChanged(ItemEvent event) {
                                        if (event.getStateChange() == ItemEvent.SELECTED){
                                            updateMaxMin();
                                        }
                                    }
                                });
                            }
                        }
                        {
                            JPanel panel_3 = new JPanel();
                            panel_3.setBackground(UIManager.getColor("Button.background"));
                            panel_2.add(panel_3, BorderLayout.SOUTH);
                            panel_3.setLayout(new BorderLayout(0, 0));
                        }
                        {
                            JLabel lblHighlightAndCtrl = new JLabel("Highlight and Ctrl C to copy cells");
                            lblHighlightAndCtrl.setForeground(Color.BLUE);
                            lblHighlightAndCtrl.setFont(new Font("Arial", Font.BOLD, 14));
                            panel.add(lblHighlightAndCtrl, BorderLayout.EAST);
                        }
                        {
                        }
                    }
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
            JPanel panel = new JPanel();
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
                            }
                        }
                    }
                }
            }
            {
                table = new JTable();
                table.setForeground(Color.BLACK);
                table.setCellSelectionEnabled(true);
                table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                DefaultTableModel model =new DefaultTableModel(
                        new Object[][] {
                                {"Class", "freq", "%", "cumFr", "cumFr%"},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {"Totals:", null, null, null, null},
                            },
                            new String[] {
                                "New column", "New column", "New column", "New column", "New column"
                            }
                        ){
                            private static final long serialVersionUID = 1L;

                            @Override
                            public boolean isCellEditable(int row, int column){
                                return false;
                            }
                        }; 
                table.setModel(model);
                
                table.setColumnSelectionAllowed(true);
                
                panel.add(table, BorderLayout.CENTER);
            }
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1, BorderLayout.EAST);
                panel_1.setLayout(new BorderLayout(0, 0));
                {
                    JPanel panel_2 = new JPanel();
                    panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                    panel_1.add(panel_2, BorderLayout.NORTH);
                    panel_2.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_3 = new JPanel();
                        panel_2.add(panel_3, BorderLayout.SOUTH);
                        panel_3.setLayout(new BorderLayout(0, 0));
                        {
                            JLabel lblNewLabel_1 = new JLabel("End:");
                            lblNewLabel_1.setForeground(Color.BLACK);
                            panel_3.add(lblNewLabel_1, BorderLayout.WEST);
                        }
                        {
                            textFieldEnd = new JTextField();
                            panel_3.add(textFieldEnd, BorderLayout.CENTER);
                            textFieldEnd.setColumns(10);
                        }
                        {
                            JPanel panel_5 = new JPanel();
                            panel_3.add(panel_5, BorderLayout.SOUTH);
                            {
                                chckbxRound = new JButton("Round Max and Min");
                                chckbxRound.setForeground(Color.BLUE);
                                panel_5.add(chckbxRound);
                                chckbxRound.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        double min,max;
                                        if (GrafInputHelpers.isADoubleWithMessage(textFieldBegin.getText())) min = Double.parseDouble(textFieldBegin.getText()); else return;
                                        if (GrafInputHelpers.isADoubleWithMessage(textFieldEnd.getText())) max = Double.parseDouble(textFieldEnd.getText()); else return;
                                        long p =  -Math.round(Math.log10(Math.abs(min)));
                                        min = Math.round(min*Math.pow(10,p))/(Math.pow(10, p));
                                        p =  -Math.round(Math.log10(Math.abs(max)));
                                        max = Math.round(max*Math.pow(10,p))/(Math.pow(10,p));
                                        textFieldBegin.setText(""+min);
                                        textFieldEnd.setText(""+max);
                                    }
                                });
                                                                
                            }
                        }
                    }
                    {
                        JPanel panel_3 = new JPanel();
                        panel_2.add(panel_3, BorderLayout.NORTH);
                        panel_3.setLayout(new BorderLayout(0, 0));
                        {
                            JLabel lblNewLabel = new JLabel("Begin:");
                            lblNewLabel.setForeground(Color.BLACK);
                            panel_3.add(lblNewLabel, BorderLayout.WEST);
                        }
                        {
                            textFieldBegin = new JTextField();
                            panel_3.add(textFieldBegin, BorderLayout.CENTER);
                            textFieldBegin.setColumns(10);
                        }
                    }
                }
                {
                    JPanel panel_2 = new JPanel();
                    panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                    panel_1.add(panel_2, BorderLayout.SOUTH);
                    panel_2.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_3 = new JPanel();
                        panel_2.add(panel_3, BorderLayout.NORTH);
                        panel_3.setLayout(new BorderLayout(0, 0));
                        {
                            numberOfClassesRadioBtn = new JRadioButton("#Classes");
                            numberOfClassesRadioBtn.setForeground(Color.BLACK);
                            numberOfClassesRadioBtn.setSelected(true);
                            panel_3.add(numberOfClassesRadioBtn, BorderLayout.WEST);
                            numberOfClassesRadioBtn.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent arg0) {
                                    if (numberOfClassesRadioBtn.isSelected()) {
                                        numberOfClassesRadioBtn.setSelected(true);
                                        textFieldClassNumber.setEditable(true);
                                        classSizeRadioBtn.setSelected(false);
                                        textFieldClassSize.setEditable(false);
                                    }else{
                                        numberOfClassesRadioBtn.setSelected(false);
                                        textFieldClassNumber.setEditable(false);
                                        classSizeRadioBtn.setSelected(true);
                                        textFieldClassSize.setEditable(true);
                                    }
                                }
                            });;
                                
                        }
                        {
                            textFieldClassNumber = new JTextField();
                            textFieldClassNumber.setText("10");
                            panel_3.add(textFieldClassNumber, BorderLayout.CENTER);
                            textFieldClassNumber.setColumns(10);
                        }
                        {
                            JPanel panel_3_1 = new JPanel();
                            panel_3.add(panel_3_1, BorderLayout.SOUTH);
                            panel_3_1.setLayout(new BorderLayout(0, 0));
                            {
                                classSizeRadioBtn = new JRadioButton("Class Size");
                                classSizeRadioBtn.setForeground(Color.BLACK);
                                panel_3_1.add(classSizeRadioBtn, BorderLayout.WEST);
                                classSizeRadioBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        if (numberOfClassesRadioBtn.isSelected()) {
                                            numberOfClassesRadioBtn.setSelected(false);
                                            textFieldClassNumber.setEditable(false);
                                            classSizeRadioBtn.setSelected(true);
                                            textFieldClassSize.setEditable(true);
                                        }else{
                                            numberOfClassesRadioBtn.setSelected(true);
                                            textFieldClassNumber.setEditable(true);
                                            classSizeRadioBtn.setSelected(false);
                                            textFieldClassSize.setEditable(false);
                                        }
                                    }
                                });;
                            }
                            {
                                textFieldClassSize = new JTextField();
                                panel_3_1.add(textFieldClassSize, BorderLayout.CENTER);
                                textFieldClassSize.setColumns(10);
                            }
                        }
                        {
                            JPanel panel_4 = new JPanel();
                            panel_3.add(panel_4, BorderLayout.NORTH);
                            panel_4.setLayout(new BorderLayout(0, 0));
                            {
                                JLabel lblChooseAMethod = new JLabel("Choose method of class creation:");
                                lblChooseAMethod.setForeground(Color.BLUE);
                                panel_4.add(lblChooseAMethod);
                            }
                        }
                    }
                }
            }
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
                    JButton okButton = new JButton("Close");
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                dispose();  
                        }
                    });
                    JButton saveBtn = new JButton("Calc");
                    panel.add(saveBtn);
                    saveBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            checkForInput();
                        }
                    });
                    panel.add(okButton);
                    okButton.setActionCommand("OK");
                    getRootPane().setDefaultButton(okButton);
                }
            }
        }
        //resetListModels();
    }
    
    private void updateMaxMin(){
        int chosen = columnComboBox.getSelectedIndex();
        if (chosen != 0) 
        {   
            Double[] columnValues = gSess.getData().getColumnValues(chosen);
            columnValues = GrafStats.getRidOfNulls(columnValues);
            if (columnValues.length == 0){  
                JOptionPane.showMessageDialog(null, "You chose an empty column.", "I Feel So Empty!" , JOptionPane.ERROR_MESSAGE);
                return;
            }
            double min = GrafStats.getMin(columnValues);
            double max = GrafStats.getMax(columnValues);
            textFieldBegin.setText(""+min);
            textFieldEnd.setText(""+max);
        }
    }
    
    
    private void checkForInput(){
            int column = columnComboBox.getSelectedIndex();
            if (column == 0) { JOptionPane.showMessageDialog(null,
                    "You must choose an input column for your Frequency Distribution Table.",
                    "Choose an input column",
                    JOptionPane.ERROR_MESSAGE);
                    return;
            }
            
            double min, max;
            if (GrafInputHelpers.isADoubleWithMessage(textFieldBegin.getText())) min = Double.parseDouble(textFieldBegin.getText()); else return;
            if (GrafInputHelpers.isADoubleWithMessage(textFieldEnd.getText())) max = Double.parseDouble(textFieldEnd.getText()); else return;
            findFrequencies(min, max, column);
            //findPercents();
      }
    
        
    private void findFrequencies(double min, double max, int column){
        double[] classArray = getClassBoundries(min, max);  // returns { min, next boundry, next boundry.....max}
            
        int numClasses = classArray.length-1;               //don't count max
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(numClasses+2);                    //+2 for header and total rows
        Double[] temp = GrafStats.getRidOfNulls(gSess.getData().getColumnValues(column));
        Arrays.sort(temp);
                    
        int binCount = 0;
        int totalCount = 0;
        double percent;
        double totalPercent = 0;
        int cumf = 0;
        double upperLimit;
        int numValues = temp.length;
        int upperBoundIndex = 1;
        int i = 0;
        while(i < numValues)  
        {
            //try{
            //  double t = temp[i];
            //  t = classArray[upperBoundIndex];
            //}catch (Exception e) {
            //  System.out.println("i: "+i+"upper: "+upperBoundIndex+"temp[i] "+temp[i]+"classArray[upperBoundIndex]"+classArray[upperBoundIndex]);
            //  return;
            //}
            if (temp[i] < classArray[upperBoundIndex]) {
                binCount++;
                i++;
            }
            else {
                table.setValueAt(classArray[upperBoundIndex-1]+" - ", upperBoundIndex, 0);
                table.setValueAt(binCount, upperBoundIndex, 1);
                percent = (double) binCount/numValues;
                totalPercent = totalPercent + percent;
                table.setValueAt(percent, upperBoundIndex, 2);
                cumf = cumf + binCount;
                table.setValueAt(cumf , upperBoundIndex, 3);
                table.setValueAt((double) cumf/numValues*100, upperBoundIndex, 4 );
                binCount = 0;
                upperBoundIndex++;
            }
            
        }
        int last = classArray.length;
        percent = (double) binCount/(numValues);
        totalPercent = totalPercent + percent;
        
        table.setValueAt(classArray[last-2], last-1, 0);
        table.setValueAt(cumf+binCount, last-1, 3);
        table.setValueAt(binCount, last-1, 1);
        table.setValueAt(percent, last - 1, 2);
        table.setValueAt((cumf+binCount)/numValues*100, last - 1, 4);
        table.setValueAt("Totals", last, 0);
        table.setValueAt(numValues, last, 1);
        table.setValueAt(totalPercent, last, 2);
        table.setValueAt("", last, 3);
        table.setValueAt("", last, 4);
        
    }
    
    
    private double[] getClassBoundries(double min, double max){
        double[] emptyArray = {};
        int numClasses;
         if (numberOfClassesRadioBtn.isSelected())  {
                //int numClasses;
                if (GrafInputHelpers.isAnIntegerWithMessage(textFieldClassNumber.getText())) numClasses = Integer.parseInt(textFieldClassNumber.getText());
                else return emptyArray;
                return GrafStats.getClassesByNumber(numClasses, min , max);
            }
            else{
                double classSize;
                if (GrafInputHelpers.isADoubleWithMessage(textFieldClassSize.getText())) classSize = Double.parseDouble(textFieldClassSize.getText());
                else return emptyArray;
                return  GrafStats.getClassesByClassSize(classSize, min, max);
            }
            
    }
    
    public static void createInputDialog(GrafProg gs){
        System.out.println("in call");
        FrequencyChartDialog fcDialog = new FrequencyChartDialog(gs); 
        fcDialog.setVisible(true); 
        fcDialog.setModal(true); 
        
       }
        
            
        
        
}
