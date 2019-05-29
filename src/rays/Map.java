package rays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

public class Map {
	
	
	//
	private List<Line> barriers = new ArrayList<Line>(); 
	
	
	public Map () {
		barriers.add(new Line(600, 400, 600, 200));
	}
	

	
	
	
	public void render(Graphics g, int width, int height){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		Graphics2D g2d = (Graphics2D)g;
		
		for (Line line : barriers) {
			line.render(g2d);
			
		}
	}
	
}
