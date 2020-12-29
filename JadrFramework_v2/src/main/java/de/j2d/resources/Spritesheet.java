package de.j2d.resources;

 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class Spritesheet {


	private BufferedImage spritesheet;
	private int frames;
	private int tilesize;
	private static HashMap<Integer,Spritesheet> spritesheets = new HashMap<Integer,Spritesheet>(); 
	
	public Spritesheet(String path, int frames) {
		BufferedImage sprite = null;
		
		try {
			sprite = ImageIO.read(new File("assets/" + path));
		}catch(IOException e) {
			System.out.println("Error loading sprite on path: assets/" +path);
		}
		this.frames = frames;
		
		spritesheet = sprite;
		this.tilesize = spritesheet.getWidth() / frames;
		System.out.println("loaded Spritesheet");
	} 
	public BufferedImage getSprite(int x, int y) {
		if(spritesheet == null) {
			System.out.println("Spritesheet is null");
			return null;
		}
		return spritesheet.getSubimage(x * frames, y * frames, frames, frames);
	}

	public int getWith() {
		return this.getFrame(0).getWidth();
	}
	public int getHeight() {
		return this.getFrame(0).getHeight();
	}

	public Spritesheet saveAsSpritesheet(int id) {
		spritesheets.put(id, this);
		return this;
	}
	
	
	public static Spritesheet getSpritesheet(int id) {
		if(spritesheets.containsKey(id)) {
			return spritesheets.get(id);
		}else {
			System.out.println("[Error] Spritesheet with id " + id + " not found");
			return null;
		}
	}
	
	public int getFrameAmount() {
		return this.frames;
	}
	public BufferedImage getFrame(int frame) {
		if(spritesheet == null) {
			System.out.println("Spritesheet is null");
			return null;
		}
		if(frame > this.frames) {
			System.out.println("Frame " + frame + " not found! Frame searched: " + frame + " Total Frames: " + this.frames);
			return null;
		}
		return spritesheet.getSubimage(tilesize * frame, 0, tilesize, spritesheet.getHeight());
	}
	
}
