package de.j2d.core;




import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JFrame;


public abstract class Game implements Runnable{
	
	
	
	public static final double 
			SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
			SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public int FPS = 60, widht, height;
	private long maxLoopTime = 1000 / FPS;
	public RenderUtils renderutils = new RenderUtils();
	public Random random = new SecureRandom();
	protected Thread gamethread;
	public static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
	public static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(2);
	private Window frame;
	boolean run = true;	
	
	
	public void setFps(int FPS) {
		this.FPS = FPS;
		this.maxLoopTime = 1000 / FPS; 
	}
	
	public Canvas getCanvas() {
		return this.frame.getCanvas();
	}
	
	public Window getWindow() {
		return frame;
	}
	
	public Game(String title, int width, int height) {
		
		frame = new Window(title, width, height);
		this.widht = frame.getJFrame().getWidth();
		this.height = frame.getJFrame().getHeight();
		gamethread = new Thread(this);
		
	}
	
	public Game start() {
		gamethread.start();
		return this;
	}
	
	public void stop() {
		run = false;
		System.out.println("QuickGame stopped!");
	}
	
	public void run() {
		long timelast = 0L;		
		long timestamp;
		long oldTimestamp;
		
		int i = 0;
		onStart();
		while (run) {
			i++;
			if (timelast < System.currentTimeMillis()) {
				timelast = System.currentTimeMillis() + 1000L;
				System.out.println("FPS: " +i);
				i = 0;
			}
			oldTimestamp = System.currentTimeMillis();
			
			onUpdate();
			preRender();

			timestamp = System.currentTimeMillis();
			if (timestamp - oldTimestamp <= maxLoopTime) {
				try {
					// TO FAST
					Thread.sleep(maxLoopTime - (timestamp - oldTimestamp));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	BufferStrategy bs;
	Graphics2D g;
	
	
	protected abstract void onUpdate();
	
	protected abstract void onStart();
	
	void preRender() {
		Canvas c = frame.getCanvas();
		bs = c.getBufferStrategy();
		if (bs == null) {
			frame.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();

		// Clearing
		g.clearRect(0, 0, widht, height);
		// dorendering
		render(g);
		

		bs.show();
		g.dispose();
		
	}
	protected abstract void render(Graphics2D g2d);
	public class RenderUtils{
		private RenderUtils() {}
		public void renderRectangleBorder(Graphics2D g2, int x,int y, int width, int height, float borderSize) {
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(borderSize));
			g2.drawRect(x, y, width, height);
			g2.setStroke(oldStroke);
		}
	}
	
	public class Window implements KeyListener, MouseMotionListener, MouseListener{
		JFrame frame = new JFrame();
		Canvas c = new Canvas();
		
		public static final int MOUSE_LEFT = 1, MOUSE_RIGHT = 3;
		
		public Window(String title, int width, int height) {
			
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    frame.addKeyListener(this);
	    frame.addMouseMotionListener(this);
	    frame.addMouseListener(this);
	    c.addMouseMotionListener(this);
	    c.addKeyListener(this);
	    c.addMouseListener(this);
		c.setBounds(frame.getBounds());
		frame.add(c);
		}
		

		public Set<Character> activeKeys = new HashSet<Character>();
		public Set<Integer> activeMouseKeys = new HashSet<Integer>();
		public Point mouseLocPoint = new Point();
		public boolean isKeyPresses(char key) {
			return activeKeys.contains(key);
		}

		
		public void keyPressed(KeyEvent a) {
			
		}

		
		public void keyReleased(KeyEvent a) {
			activeKeys.remove(a.getKeyChar());
		}

		
		public void keyTyped(KeyEvent a) {
			activeKeys.add(a.getKeyChar());
		}
		
		public JFrame getJFrame() {
			return frame;
		}
		
		public Canvas getCanvas() {
			return c;
		}

		
		public void mouseDragged(MouseEvent e) {
			mouseLocPoint = e.getPoint();			
		}

		
		public void mouseMoved(MouseEvent e) {
			mouseLocPoint = e.getPoint();
		}


		public void mouseClicked(MouseEvent e) {
			
		}


		public void mouseEntered(MouseEvent e) {
		}


		public void mouseExited(MouseEvent e) {
		}


		public void mousePressed(MouseEvent e) {
			activeMouseKeys.add(e.getButton());
		}


		public void mouseReleased(MouseEvent e) {
			activeMouseKeys.remove(e.getButton());
		}
		
	}
}


