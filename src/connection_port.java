
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;

public class connection_port {
	protected Point mousePt;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected connection_port(int x,int y){
		this.x = x;
		this.y = y;
		this.width = 13;
		this.height = 13;
	}

	public void paint(Graphics g) {  
	    Graphics2D g2 = (Graphics2D)g.create();
	    g2.setColor(Color.BLACK);
		g2.fillRect(this.x, this.y, width, height);
	}  
}
