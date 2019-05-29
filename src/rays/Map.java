package rays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;

public class Map {
	
	public Map () {
		
	}
	

	public void render(Graphics g, int width, int height){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
	}
	
}
