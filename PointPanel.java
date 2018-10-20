
/**
 * PointPanel input panel for shape types
 * 
 * @author (Bill Gillam) 
 * @version (1/12/2017)
 */
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PointPanel extends JPanel
{
    private JLabel x1Label = new JLabel("x1:");
    private  JLabel x2Label = new JLabel("x2:");
    private JLabel y1Label = new JLabel("y1:");
    private JLabel y2Label = new JLabel("y2:");
    private JLabel hLabel = new JLabel("height:");
    private JLabel wLabel = new JLabel("width:");
    private JLabel rLabel = new JLabel("radius:");
    private JLabel fLabel = new JLabel("f(x)= ");
    private JLabel nLabel = new JLabel("n: ");
    
    private JTextField x1 = new JTextField();
    private JTextField x2 = new JTextField();
    private JTextField y1 = new JTextField();
    private JTextField y2 = new JTextField();
    private JTextField h = new JTextField();
    private JTextField w = new JTextField();
    private JTextField r = new JTextField();
    private JTextField f = new JTextField();
    private JTextField n = new JTextField();
    private JButton copyButton = new JButton("Select");
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel leftPanel2 = new JPanel();
    private JPanel rightPanel2 = new JPanel();
    private JComboBox fComboBox = new JComboBox();
    private JLabel functionText = new JLabel("Choose f(x)");
    private ArrayList<GrafObject> tempList;
    //private GrafType gType;
    
    public static void main(String[] args){
    PointPanel gpp = new PointPanel(new ArrayList<GrafObject>());
         gpp.setVisible(true);
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        
        jf.add(gpp);
        jf.pack();
        //m.setModal();
    }
  
   
   
    /**
     * Constructor for objects of class PointPanel
     */
    public PointPanel(ArrayList<GrafObject> tempListPassed)
    {
        tempList = tempListPassed;
        //gType = gTypePassed;
        x1.setColumns(8);
        
        y1.setColumns(8);
        setBackground(new Color(220, 220, 220));
        setLayout(new BorderLayout());
        leftPanel.setLayout(new BorderLayout()); 
        rightPanel.setLayout(new BorderLayout()); 
        topPanel.setLayout(new BorderLayout()); 
        bottomPanel.setLayout(new BorderLayout());
        rightPanel2.setLayout(new BorderLayout());
        leftPanel2.setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }
    
    public void initFx(){
       if (!(fComboBox.getItemCount()==0)) {
       String fString = (String)fComboBox.getItemAt(0);
       fString = fString.substring(4);
       f.setText(fString) ;
       }
    }
    
    public void addF(){
        f.setColumns(20);
        topPanel.add(fLabel, BorderLayout.WEST);
        topPanel.add(f, BorderLayout.EAST);
    }
    public void setupChord(){
        setupTan();
        x2.setColumns(8);
        rightPanel.add(x2Label, BorderLayout.WEST);
        rightPanel.add(x2, BorderLayout.CENTER);
        bottomPanel.add(rightPanel, BorderLayout.CENTER);
    }
    public void setupIntegral(){
        setupTan();
        x2.setColumns(8);
        n.setColumns(8);
        rightPanel.add(x2Label, BorderLayout.WEST);
        rightPanel.add(x2, BorderLayout.CENTER);
        rightPanel2.add(nLabel, BorderLayout.WEST);
        rightPanel2.add(n, BorderLayout.CENTER);
        bottomPanel.add(rightPanel, BorderLayout.CENTER);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);
    }
    public void setupZeros(){
        setupTan();
        x1Label.setText("Start x:");
        x2Label.setText("End x:");
        nLabel.setText("dx:");
        setDx(.01);
        x2.setColumns(8);
        n.setColumns(8);
        rightPanel.add(x2Label, BorderLayout.WEST);
        rightPanel.add(x2, BorderLayout.CENTER);
        rightPanel2.add(nLabel, BorderLayout.WEST);
        rightPanel2.add(n, BorderLayout.CENTER);
        bottomPanel.add(rightPanel, BorderLayout.CENTER);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);
    }
    
    public void setupTan(){
            leftPanel.add(functionText, BorderLayout.WEST);
            fComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafDeletePanel.getPlotList(tempList, GrafType.FUNCTION)));
            leftPanel.add(fComboBox, BorderLayout.CENTER);
            copyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0    ) {
                   f.setText(((String)fComboBox.getSelectedItem()).substring(4));
                }
            });
            leftPanel.add(copyButton, BorderLayout.EAST);
            topPanel.add(leftPanel, BorderLayout.NORTH);
            topPanel.add(fLabel, BorderLayout.WEST);
            f.setEditable(false);
            topPanel.add(f,BorderLayout.CENTER);
            x1.setColumns(8);
            leftPanel2.add(x1Label, BorderLayout.WEST);
            leftPanel2.add(x1, BorderLayout.CENTER);
            bottomPanel.add(leftPanel2, BorderLayout.WEST);
}
    public void addX1Y1(){
        leftPanel.add(x1Label, BorderLayout.WEST);
        leftPanel.add(x1, BorderLayout.CENTER);
        rightPanel.add(y1Label,BorderLayout.WEST);
        rightPanel.add(y1, BorderLayout.CENTER);
        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
    }
    public void addX2Y2(){
        x2.setColumns(8);
        y2.setColumns(8);
        leftPanel2.add(x2Label, BorderLayout.WEST);
        leftPanel2.add(x2, BorderLayout.CENTER);
        rightPanel2.add(y2Label, BorderLayout.WEST);
        rightPanel2.add(y2, BorderLayout.CENTER);
        bottomPanel.add(leftPanel2, BorderLayout.WEST);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);  
    }
    public void addWH(){
        w.setColumns(8);
        h.setColumns(8);
        leftPanel2.add(wLabel, BorderLayout.WEST);
        leftPanel2.add(w, BorderLayout.CENTER);
        rightPanel2.add(hLabel, BorderLayout.WEST);
        rightPanel2.add(h, BorderLayout.CENTER);
        bottomPanel.add(leftPanel2, BorderLayout.WEST);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);
    }
    public void addR(){
        r.setColumns(8);
        bottomPanel.add(rLabel,BorderLayout.WEST);
        bottomPanel.add(r, BorderLayout.EAST);
    }
    
    public double getX1(){
        if (GrafInputHelpers.isDouble(x1.getText()))
            return Double.parseDouble(x1.getText());
        else
        return Double.NaN;
    }
    public void setX1(double x){
        x1.setText(x+"");
    }
    public void blankX1(){
         x1.setText("");
    }
    
    public double getY1(){
       if (GrafInputHelpers.isDouble(y1.getText()))
            return Double.parseDouble(y1.getText());
        else
        return Double.NaN;
    }
     public void setY1(double y){
        y1.setText(y+"");
    }
    public void blankY1(){
         y1.setText("");
    }
    public double getX2(){
       if (GrafInputHelpers.isDouble(x2.getText()))
            return Double.parseDouble(x2.getText());
        else
        return Double.NaN;
    }
    public void setX2(double x){
        x2.setText(x+"");
    }
     public void blankX2(){
         x2.setText("");
    }
    public double getY2(){
       if (GrafInputHelpers.isDouble(y2.getText()))
            return Double.parseDouble(y2.getText());
        else
        return Double.NaN;
    }
      public void setY2(double y){
        y2.setText(y+"");
    }
     public void blankY2(){
         y2.setText("");
    }
    public double getW(){
        if (GrafInputHelpers.isDouble(w.getText()))
            return Double.parseDouble(w.getText());
        else
        return Double.NaN;
    }
    public void setW(double width){
        w.setText(width+"");
    }
     public void blankW(){
         w.setText("");
    }
    public double getH(){
        if (GrafInputHelpers.isDouble(h.getText()))
            return Double.parseDouble(h.getText());
        else
        return Double.NaN;
    }
    public void setH(double height){
        h.setText(height+"");
    }
     public void blankH(){
         h.setText("");
    }
    public double getR(){
        if (GrafInputHelpers.isDouble(r.getText()))
            return Double.parseDouble(r.getText());
        else
        return Double.NaN;
    }
    public void setR(double radius){
        r.setText(radius+"");
    }
     public void blankR(){
         r.setText("");
    }
    public String getF(){
        return f.getText();
    }
    public void blankF(){
        f.setText("");
    }
    public void setF(String s){
        f.setText(s);
    }
    public String getNString(){
          return n.getText();
        }
    public Integer getN(){
        if (GrafInputHelpers.isInt(n.getText()))
            return Integer.parseInt(n.getText());
        else
        return null;
    }
    public void setN(int num){
        n.setText(num+"");
    }
     public void blankN(){
         n.setText("");
    }
    public double getDx(){
        if (GrafInputHelpers.isDouble(n.getText()))
            return Double.parseDouble(n.getText());
        else
        return Double.NaN;
    }
    public void setDx(double num){
        n.setText(num+"");
    }
     public void blankDx(){
         n.setText("");
    }
}   
