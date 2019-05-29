package rays;

import java.awt.Graphics2D;

public class Line {
	
	private int x1, x2, y1, y2;
	private double angle;
	
	public static final int maxRayLength = 10000;
	public int rayLength;
	
	public Line (int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public Line (int x1, int y1, double angle) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = (int) (x1 + Math.cos(Math.toRadians(angle)) * maxRayLength);
		this.y2 = (int) (y1 + Math.sin(Math.toRadians(angle)) * maxRayLength);
		
		this.angle = angle;
		
		
		
	}
	
	
	public void render(Graphics2D g2d) {
		g2d.drawLine(x1, y1, x2, y2);
	}
	
}
