

import java.awt.Graphics;
import java.awt.Graphics2D;

public class generalization_line extends base_line {
	private int arrowW = 10, arrowH = 10;
	generalization_line(connection_port src_port, connection_port dest_port){
		super(src_port, dest_port);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g.create();
		g2.drawLine(x1, y1, x2, y2);
		
		// 三角形的點, 考慮線條角度
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - arrowW, xn = xm, ym = arrowH, yn = -arrowH, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g2.fillPolygon(xpoints, ypoints, 3);
	}
}