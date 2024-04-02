
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class group extends Rectangle {
	protected boolean isSelected = false;
	protected boolean israngeSelected = false;
	protected int original_x;
	protected int original_y;
	
	group(int x1, int y1, int x2, int y2){
		this.x = x1;
		this.y = x2;
		this.width = x2-x1;
		this.height = y2-y1;
	}

	public void paint(Graphics g) {  
	    Graphics2D g2 = (Graphics2D)g.create();
	    g2.drawRect(this.x, this.y, this.width, this.height);
	    
	}  
	
	public void move(int x, int y) {  
			if(isSelected || israngeSelected)
	        this.x += x;  
	        this.y += y;  	  	        
	}  
	
	public boolean is_select_contain(int select_start_x, int select_start_y, int select_end_x, int select_end_y) {
		boolean result = false;
		
		if(select_start_x <= this.x && (this.x+width) <= select_end_x) {
			if(select_start_y <= this.y && (this.y+height)  <= select_end_y) {
				result = true;
				israngeSelected = true;
			}
		}
		if(select_end_x <= this.x && (this.x+width) <= select_start_x) {
			if(select_end_y <= this.y && (this.y+height)  <= select_start_y) {
				result = true;
				israngeSelected = true;
			}
		}
		if(select_end_x <= this.x && (this.x+width) <= select_start_x) {
			if(select_start_y <= this.y && (this.y+height)  <= select_end_y) {
				result = true;
				israngeSelected = true;
			}
		}
		if(select_start_x <= this.x && (this.x+width) <= select_end_x) {
			if(select_end_y <= this.y && (this.y+height)  <= select_start_y) {
				result = true;
				israngeSelected = true;
			}
		}		
		
		return result;
	}
	
	public void unselect(){  
		this.isSelected = false; 
	}
	
	public void select(int x, int y){
		if(this.contains(x,y)) {
			isSelected = true; 
	        this.original_x = x;
	        this.original_y = y;
		}else {
			this.unselect();			
		}
	}
	
	public void ungroup() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	
	
}
