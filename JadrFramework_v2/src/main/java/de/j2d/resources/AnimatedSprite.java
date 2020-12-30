package de.j2d.resources;

import java.awt.image.BufferedImage;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnimatedSprite implements JadrImage{
	private static ScheduledExecutorService scheduler;
	
	private BufferedImage img;
	private Spritesheet s;
	
	private int frame = 0;
	

	public AnimatedSprite(Spritesheet s, long changeSpeed) {
		this.s = s;
		scheduler.scheduleAtFixedRate( new Runnable() {
			
			public void run() {
				update();
			}
		} , changeSpeed, changeSpeed, TimeUnit.MILLISECONDS);
		
		img = s.getFrame(0);
	}
	
	public static void setScheduler(ScheduledExecutorService s) {
		scheduler = s;
	}
	
	
	private void update() {
		frame++;
		if(frame >= s.getFrameAmount()) {
			frame = 0;
		}
		img = s.getFrame(frame);
	}
	
	public BufferedImage getCurrentImage() {
		return img;
	}

	public BufferedImage getImage() {
		return getCurrentImage();
	}


}