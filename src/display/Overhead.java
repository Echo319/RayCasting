package display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import rays.Map;

public class Overhead implements Runnable {

	// Creates a JFrame then imports a map 
	// frame should just be a black screen with some barriers that create some shapes.
	// load in a particle that shoots rays in all directions.
	
	private static JFrame frame;
	private static Canvas canvas;
	private static BufferStrategy canvasBufferStrategy;
	private Graphics graphics;
	private int width;
	private int height;
	private boolean running = false;
	private Thread thread;
	
	// The map holds the shape and particle.
	private Map map;
	
	
	public Overhead (int width, int height) {
		this.width = width;
		this.height = height;
		
		//init frame
		frame = new JFrame("Overhead grid");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//init canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(true);
		
		frame.add(canvas);
		frame.pack();
	}
	
	@Override
	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			lastTime = now;
			if(delta >= 1){
				update();
				render();
				delta --;
			}
		}
		stop();
	}
	
	private void init(){
		//init control system
		
		//load map
		map = new Map();
	}
	
	private void update(){
		//recalc intersections.
	}
	
	private void render(){
		canvasBufferStrategy = canvas.getBufferStrategy();
		
		if(canvasBufferStrategy == null){
			canvas.createBufferStrategy(3);
			return;
		}
		
		graphics = canvasBufferStrategy.getDrawGraphics();
		
		graphics.clearRect(0, 0, width, height);
			
		
		map.render(graphics, width, height);
		
		graphics.dispose();
		canvasBufferStrategy.show();
		
	}
	
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop(){
		if(!running)
			return;
		
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
}
