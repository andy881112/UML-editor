
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;

public class shape_Rectangle extends base_shape {
	
	shape_Rectangle(Point mousePt,int depth){
		this.group_index.add(-1);
		this.x=mousePt.x;
		this.y=mousePt.y;
		this.width=150;
		this.height=200;
		this.depth=depth;
	}
	
	@Override
	public void paint(Graphics g) {  
		super.paint(g);
	    Graphics2D g2 = (Graphics2D)g.create();
	    g2.setColor(Color.BLACK);
	    g2.drawRect(x, y, width, height);
	    g2.drawLine(x, y+(height/3*2), x+width, y+(height/3*2));
	    g2.drawLine(x, y+(height/3*1), x+width, y+(height/3*1));
	    g2.drawString(class_name,  x+(width/2)-20, y+(height/3*1/2));
	    
	}  
	

	
	@Override
	public boolean is_Rectangle() {
		return true;
	}
	
}
