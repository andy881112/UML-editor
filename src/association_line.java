

import java.awt.Graphics;
import java.awt.Graphics2D;

public class association_line extends base_line {
	
	association_line(connection_port src_port, connection_port dest_port){
		super(src_port, dest_port);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g.create();
		double nx, ny;
        double lineLength = Math.hypot(x1 - x2, y1 - y2);
        nx = (x1 - x2) / lineLength;
        ny = (y1 - y2) / lineLength;
        int c1x, c1y, c2x, c2y, c3x, c3y;
        int w=8, h=16;
        c1x = (int)(x2+nx*w) + (int) (ny * w);
        c1y = (int)(y2+ny*w) + (int) (-nx * w);
        c2x = (int)(x2+nx*w) + (int) (-ny * w);
        c2y = (int)(y2+ny*w) + (int) (nx * w);
        c3x = (int)(x2+nx*h);
        c3y = (int)(y2+ny*h);
        g.drawLine(c1x, c1y, x2, y2);
        g.drawLine(c2x, c2y, x2, y2);
        g.drawLine(x1,y1,c3x,c3y);
	}
}
