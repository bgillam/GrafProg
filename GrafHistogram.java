
/*  GrafHistoGram for GrafProg Project *
*  @author Bill Gillam           *
*  2/3/17                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.util.Arrays;




//Class Header
public class GrafHistogram extends GrafObject {
    //Instance Variables
    private int columnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    //private FrequencyChartDialog fcd;
    
    private boolean relative = false;
    private boolean labelAxisByBoundries = true;
    private boolean byNumClasses = true;
    private boolean displayCounts = true;
    private double  begin;
    private double  end;
    private int numClasses = 10;
    private double classWidth = (begin - end)/10;
    private Color fill = Color.WHITE;
    private boolean fillFlag = false;
    private double[] classLimits;
    private double[] counts;
    
    
        
    //Constructor
    public GrafHistogram(){
       setGrafType(GrafType.HISTOGRAM);
       setMoveable(false);
       setGrafColor(Color.BLACK); 
    }
    
    public GrafHistogram(GrafProg sess){
        setGrafType(GrafType.HISTOGRAM);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        //fcd = 
        table = myOwner.getData();
        //sess.setMessage1("Histogram for Column "+columnNumber);
    }
    
    //constructor 
    public GrafHistogram(GrafProg sess, int column){
        this(sess);
        setColumnNumber(column);
        sess.setMessage1("Histogram for Column "+columnNumber);
    }
    
    public GrafHistogram(GrafProg sess, int column, double b, double e, int numCl, Color c, boolean rel){
        this(sess, column);
        relative = rel;
        begin = b;
        end = e;
        numClasses = numCl;
        setGrafColor(c);
        byNumClasses = true;
        classLimits = GrafStats.getClassesByNumber(numClasses, begin, end);    //problem here!
        classWidth = classLimits[1] - classLimits[0];
    }
    public GrafHistogram(GrafProg sess, int column, double b, double e, double classW, Color c, boolean rel){
        this(sess, column);
        relative = rel;
        begin = b;
        end = e;
        classWidth = classW;
        setGrafColor(c);
        byNumClasses = false;
        classLimits = GrafStats.getClassesByClassSize(classW, begin, end);  
        numClasses = classLimits.length;
    }
   //public GrafHistogram(GrafProg sess, int column, double b, double e, Color c, boolean byNC, int numCl, double classW){
    //    this(sess, column);
    //    begin = b;
   //     end = e;
   //     setGrafColor(c);
    //    byNumClasses = byNC;
   //     numClasses = numCl;
   //     classWidth = classW;
     
   // }
    
    //drawGraf overriding method in parent GrafObject
    public void drawGraf(Graphics2D gc){
        gc.setColor(super.getGrafColor());
        Double[] temp = GrafStats.getRidOfNulls(myOwner.getData().getColumnValues(columnNumber));
        Arrays.sort(temp);
        int binCount = 0;
        int totalCount = 0;
        int upperBoundIndex = 1;
        int i = 0;
        int numValues = temp.length;
        String formatString = "%."+gStuff.getDecPlaces()+"f";
        double[] counts = new double[classLimits.length];
        while (i < numValues){
            if (temp[i] < classLimits[upperBoundIndex]){  
                binCount++;
                i++;
            }
            else{ 
                counts[upperBoundIndex-1] = binCount;
                binCount = 0;
                upperBoundIndex++;
            }
            
        }
        counts[upperBoundIndex-1] = binCount;
        double height;       
        for (int j = 0; j < classLimits.length-1; j++){
            if (relative) height = counts[j]/numValues; else height = counts[j];
            if (labelAxisByBoundries){
               if (myOwner.getGrafSettings().getReverseXY()){
                   GrafPrimitives.grafLine(gStuff,gStuff.getGrafHeight()/50, classLimits[j],   -gStuff.getGrafHeight()/50, classLimits[j], gc);
                   GrafPrimitives.grafString(gStuff,-gStuff.getGrafHeight()/25, classLimits[j], ""+String.format(formatString, classLimits[j], gc), gc);
               }
               else{
                   GrafPrimitives.grafLine(gStuff,classLimits[j], gStuff.getGrafHeight()/50, classLimits[j], -gStuff.getGrafHeight()/50, gc);
                   GrafPrimitives.grafString(gStuff,classLimits[j], -gStuff.getGrafHeight()/25, ""+String.format(formatString, classLimits[j], gc), gc);
               }
            }
            if ((j == classLimits.length - 2) && (counts[j] == 0)) break;
            if (fillFlag) {
                gc.setColor(fill);
                if (myOwner.getGrafSettings().getReverseXY())  GrafPrimitives.fillRect(gStuff,0, classLimits[j]+classWidth, height, classWidth, gc ); 
                else GrafPrimitives.fillRect(gStuff,classLimits[j], height, classWidth, height, gc );
            }
            gc.setColor(super.getGrafColor());
            if (myOwner.getGrafSettings().getReverseXY())  GrafPrimitives.grafRect(gStuff,0, classLimits[j]+classWidth, height, classWidth, gc ); 
            else GrafPrimitives.grafRect(gStuff,classLimits[j], height, classWidth, height, gc );
            if (displayCounts){
                if (myOwner.getGrafSettings().getReverseXY()){GrafPrimitives.grafString(gStuff,height+gStuff.getGrafHeight()/50,classLimits[j]+classWidth/2, ""+counts[j], gc);}
                else GrafPrimitives.grafString(gStuff,classLimits[j],height+gStuff.getGrafHeight()/50, ""+counts[j], gc);
            }
            gc.setColor(super.getGrafColor());
            
        }
           //gStuff.grafLine(GrafStats.getMin(table.getColumnValues(columnNumber)), , x2, y2, gc);
        myOwner.setMessage2("");
        myOwner.incrementBoxPlotsPlotted();     
        gc.setColor(Color.BLACK);
        
    }
    
   
    
    //Setters and Getters
    public void setColumnNumber(int c){ columnNumber = c;}
    public int getColumnNumber(){ return columnNumber;}
    public void setFillFlag(boolean tf){
          fillFlag = tf;
    }
    public boolean getFillFlag(){
        return fillFlag;
    }
    public Color getFill(){
        return fill;
    }
    public void setFill(Color c){
        fill = c;
    }
    public double getBegin(){
        return begin;
    }
    public double getEnd(){
        return end;
    }
    public boolean getByNumClassChecked(){
        return byNumClasses;
    }
    public int getNumClasses(){
            return numClasses;
    }
    public double getClassWidth(){
        return classWidth;
    }
    public boolean relativeHisto(){
        return relative;
    }
    public void setRelativeHisto(boolean tf){
        relative = tf;
    }
    public void setShowCounts(boolean tf){
        displayCounts = tf;
    }
    public boolean getShowCounts(){
        return displayCounts;
    }
    public boolean labelAxis(){
        return labelAxisByBoundries;
    }
    public void setLabelAxis(boolean tf){
        labelAxisByBoundries = tf;
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
