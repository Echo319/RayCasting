package rays;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

public class Particle {
	public int x,y;
	public List<Line> rays = new ArrayList<>();
	public final int numRays = 360; 
	
	public Particle(int x, int y) {
		// starting position
		this.x = x;
		this.y = y;
		
		//init rays
		double angle = 0;
		double angleDif = (double) 360 / numRays;
		
		for(int i = 0; i < numRays; i++) {
			rays.add(new Line(x, y, angle));
			angle += angleDif;
		}
		
	}
	
	public void render(Graphics2D g2d) {
		for (Line line : rays) {
			line.rayRender(g2d);
		}
	}
	
}
