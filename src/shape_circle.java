
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;


public class shape_circle extends base_shape {
	//private static Point mousePt;
	protected Ellipse2D.Double e2; //= new Ellipse2D.Double();
	shape_circle(Point mousePt,int depth){
		this.group_index.add(-1);
		this.x=mousePt.x;
		this.y=mousePt.y;
		this.width=200;
		this.height=150;
		this.depth=depth;
		e2 = new Ellipse2D.Double(x,y,width,height);
	}
	
	@Override
	public void paint(Graphics g) {  
		super.paint(g);
	    Graphics2D g2 = (Graphics2D)g.create();
	    g2.setColor(Color.BLACK);
	    g2.drawOval(x, y, width, height);
	    g2.drawString(use_name, x+width/2-20, y+height/2);
	}  
	
//	@Override
//	public void select(int x, int y){
//		//System.out.println(this.x+" "+x);
//		if(e2.contains(x,y)) {
//			 isSelected=true; 
//		}else {
//			this.isSelected();
//		}
//	}  
	
	@Override
	public void move(int x, int y) {  
	    if(isSelected || israngeSelected || isgroup) {  
	        this.x += x;  
	        this.y += y;    
	        this.e2.x=this.x;
	        this.e2.y=this.y;        	        
	    }  
	}  	

}
