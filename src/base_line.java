
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class base_line {
	boolean isSelected_formshape1;
	
	protected int x1;
	protected int x2;
	protected int y1;
	protected int y2;
	connection_port src_port; 
	connection_port dest_port;

	
	base_line(connection_port src_port, connection_port dest_port){
		this.src_port = src_port;
		this.dest_port = dest_port;
	}
	
	public void paint(Graphics g) {
		update(src_port,dest_port);
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setColor(Color.black);
		g2.drawLine(x1,y1,x2,y2);
	}
	
	
	public void update(connection_port src_port, connection_port dest_port) { 
		this.x1=src_port.x+(src_port.width/2);
		this.y1=src_port.y+(src_port.height/2);
		this.x2=dest_port.x+(src_port.width/2);
		this.y2=dest_port.y+(src_port.height/2);
	}
	
	
}
