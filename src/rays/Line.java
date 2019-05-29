package rays;

import java.awt.Graphics2D;
import java.awt.Point;


public class Line {
	
	public int x1, x2, y1, y2;
	public double angle;

	public int endX, endY;
	public static final int maxRayLength = 10000;
	public int rayLength;
	public Point intersection;
	
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
		endX = 0;
		endY = 0;
		intersection = null;
		rayLength = (int) Math.sqrt((x2 - x1)*(x2-x1) + (y2 - y1)*(y2 - y1));
		
	}

	// Algorithm from wikipedia.
	// Find u and t as constants based on the location of our 4 points.
	public Point findIntersection(Point c, Point d) {
		Point point = null;
		
		Point a = new Point(x1, y1);
		Point b = new Point(x2, y2);
		
		double r_x = b.getX() - a.getX();
		double r_y = b.getY() - a.getY();
		
		double s_x = d.getX() - c.getX();
		double s_y = d.getY() - c.getY();
		
		double denom = r_x*s_y - r_y*s_x;
		
		double u = ((c.getX() - a.getX())*r_y - (c.getY() - a.getY())*r_x) / denom;
		double t = ((c.getX() - a.getX())*s_y - (c.getY() - a.getY())*s_x) / denom;
		
		if(t >= 0 && t <= 1 && u >= 0 && u <= 1){
			point = new Point((int)(a.getX() + t*r_x), (int)(c.getY() + u*s_y));
		}
		
		return point; 
	}
	
	public void Update(int x, int y){
		x1 = x;
		y1 = y;
		
		x2 = (int) (x1 + Math.cos(Math.toRadians(angle)) * maxRayLength);
		y2 = (int) (y1 + Math.sin(Math.toRadians(angle)) * maxRayLength);
		
		if(intersection != null){
			endX = intersection.x;
			endY = intersection.y;
			rayLength = (int)Math.sqrt((intersection.getX() - x1)*(intersection.getX() - x1) +
					(intersection.getY() - y1)*(intersection.getY() - y1));
			
		}else{
			rayLength = maxRayLength;
			
			endX = x2;
			endY = y2;
		}
	}
	
	
	public void render(Graphics2D g2d) {
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	public void rayRender(Graphics2D g2d) {
		g2d.drawLine(x1, y1, endX, endY);
	}
	
}
