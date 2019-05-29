package rays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
	
	
	//
	private List<Line> barriers = new ArrayList<Line>(); 
	private Particle player;
	
	public Map () {
		
		//Walls
		barriers.add(new Line(0, 0, 0, 600));
		barriers.add(new Line(0, 0, 800, 0));
		barriers.add(new Line(800, 600, 0, 600));
		barriers.add(new Line(800, 600, 800, 0));
		
		
		barriers.add(new Line(600, 400, 600, 200));
		
		// particle that shoots the rays
		player = new Particle(400, 300);
	}
	
	public void render(Graphics g, int width, int height){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		Graphics2D g2d = (Graphics2D)g;
		
		for (Line line : barriers) {
			line.render(g2d);
		}
		
		player.render(g2d);
	}

	public void update() {
		for(Line r: player.rays){
			int intersectingPoints = 0;
			for(Line s: barriers){
				Point a = new Point(s.x1, s.y1);
				Point b = new Point(s.x2, s.y2);
				
				Point intersection = r.findIntersection(a, b);
				
				if(intersection != null){
					intersectingPoints++;
					int rayLength = (int)Math.sqrt((intersection.getX() - r.x1)*(intersection.getX() - r.x1) +
							(intersection.getY() - r.y1)*(intersection.getY() - r.y1));
					
					if(rayLength <= r.rayLength || intersectingPoints == 1){
						r.intersection = intersection;
						r.Update(player.x, player.y);
					}
				}
				r.Update(player.x, player.y);
			}
		}		
	}
		
}
