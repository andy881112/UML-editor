
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;


public class base_shape extends Rectangle {
	protected Point mousePt;
	protected boolean isSelected = false;
	protected boolean israngeSelected = false;
	protected boolean isgroup = false;
	protected String name;
	protected String class_name="class";
	protected String use_name="use case";
	protected ArrayList<Integer> group_index = new ArrayList();
	protected int depth;

	

	connection_port port_s = new connection_port(0,0);
	connection_port port_e = new connection_port(0,0);
	connection_port port_w = new connection_port(0,0);
	connection_port port_n = new connection_port(0,0);
	
	
	base_shape(){
	}
	
	public void paint(Graphics g) {  
	    Graphics2D g2 = (Graphics2D)g.create();
	    this.port_n.x = (x+(x+width))/2-5;
	    this.port_n.y = y-11; 
	    
	    this.port_s.x = (x+(x+width))/2-5;
	    this.port_s.y = y+height;

	    this.port_w.x = x-11;
	    this.port_w.y = (y+y+height)/2-9;

	    this.port_e.x = x+width;
	    this.port_e.y = (y+y+height)/2-9;
	    
	    if( (isSelected || israngeSelected ) && group_index.get(group_index.size()-1)<0 ) {
		    port_n.paint(g2);
		    port_s.paint(g2);
		    port_w.paint(g2);
		    port_e.paint(g2);
	    }	
	}  
	
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public void unisSelected(){  
		this.isSelected = false;  
	}
	
	public void unallselect(){  
		this.isSelected = false;  
		this.israngeSelected = false;
	}
	
	public void range_selection(int select_start_x, int select_start_y, int select_end_x, int select_end_y) {
		if(select_start_x <= this.x && (this.x+width) <= select_end_x) {
			if(select_start_y <= this.y && (this.y+height)  <= select_end_y) {
				//this.isSelected = true;
				this.israngeSelected = true;
			}
		}
		if(select_end_x <= this.x && (this.x+width) <= select_start_x) {
			if(select_end_y <= this.y && (this.y+height)  <= select_start_y) {
				//this.isSelected = true;
				this.israngeSelected = true;
			}
		}
		if(select_end_x <= this.x && (this.x+width) <= select_start_x) {
			if(select_start_y <= this.y && (this.y+height)  <= select_end_y) {
				//this.isSelected = true;
				this.israngeSelected = true;
			}
		}
		if(select_start_x <= this.x && (this.x+width) <= select_end_x) {
			if(select_end_y <= this.y && (this.y+height)  <= select_start_y) {
				//this.isSelected = true;
				this.israngeSelected = true;
			}
		}		
	}
	
	public boolean is_group_select(int select_start_x, int select_start_y, int select_end_x, int select_end_y) {
		boolean result = false;
		
		return result;
	}
		
	public boolean is_inside_group(int x1, int y1, int x2, int y2) {
		boolean result = false;
		if(this.x>=x1 && this.x<=x2 && this.y>=y1 && this.y<=y2) {
			result = true;
		}
		
		return result;
	}
	
	public void select(int x, int y){
		if(this.contains(x,y)) {
			isSelected = true; 
		}else {
			this.unisSelected();			
		}
	}

	public void move(int x, int y) {  
	    if(isSelected || israngeSelected ||isgroup) { 
	        this.x += x;  
	        this.y += y;  	  	        
	    }  
	}  
	
	public void ungroup() {
		group_index.remove(group_index.size()-1);
		if(last_group_index() == -1){
			isgroup = false;		
		}
	}
	
	public int last_group_index() {
		return this.group_index.get(group_index.size()-1);
	}
	
	public boolean is_Rectangle() {
		return false;
	}
	
	public void set_name(String name) {
		if(is_Rectangle()==true) {
			class_name = name;
		}
		else {
			use_name = name;
		}
	}
	
	public boolean is_can_move(){
		return this.isSelected || this.israngeSelected;
	}
}
