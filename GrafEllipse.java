import java.awt.Color;
import java.awt.Graphics2D;

//import GrafProg.GrafType;


public class GrafEllipse extends GrafRectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GrafProg myOwner;
	private GrafSettings gStuff;
	//private Color fill = Color.WHITE;
	//private boolean fillFlag = false;
	
	

	public GrafEllipse(GrafProg sess) {
		super(sess);
		myOwner = sess;
		gStuff = super.getGrafSettings();
		this.setGrafType(GrafType.ELLIPSE);
		// TODO Auto-generated constructor stub
	}
	
	 public GrafEllipse(GrafProg sess, double x1, double y1, double width, double height){
		    super(sess, x1, y1, width, height);
		    setGrafType(GrafType.ELLIPSE);
			setMoveable(false);
			setGrafColor(Color.BLACK);
			myOwner = sess;
			gStuff = myOwner.getGrafSettings();
			setX(x1);
			setY(y1);
			setWidth(width);
			setHeight(height);
		}
		
		public GrafEllipse(GrafProg sess, double x1, double y1, double width, double height, Color gColor){
		    super(sess, x1, y1, width, height);
		    setGrafType(GrafType.ELLIPSE);
			setMoveable(false);
			setGrafColor(gColor);
			myOwner = sess;
			gStuff = myOwner.getGrafSettings();
			setX(x1);
			setY(y1);
			setWidth(width);
			setHeight(height);
		}
	
	
	 public void drawGraf(Graphics2D gc){
		 //System.out.println(super.getFillFlag());
		 if (super.getFillFlag()) {
			   gc.setColor(super.getFill());
			   GrafPrimitives.fillEllipse(gStuff,getX(), getY(), getWidth(), getHeight(), gc );
		   }
		   gc.setColor(super.getGrafColor());
		   GrafPrimitives.grafEllipse(gStuff,getX(), getY(), getWidth(), getHeight() ,gc);
		   gc.setColor(Color.BLACK);
		   gc.setPaint(Color.WHITE);
		   
		}
	 
	 public String toString(){
		   return "Ellipse("+getX()+", "+getY()+"); ("+getWidth()+", "+getHeight()+ " "+getGrafColor()+")";
	   }
}
