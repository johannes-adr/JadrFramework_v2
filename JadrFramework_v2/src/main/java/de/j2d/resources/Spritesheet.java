package de.j2d.resources;

 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class Spritesheet implements JadrImage{


	protected BufferedImage spritesheet;
	protected int frames;
	protected int tilesize;
	
	public Spritesheet(Assetspicture ap, int frames) {
		BufferedImage sprite = ap.getImage();
		
		this.frames = frames;
		
		spritesheet = sprite;
		this.tilesize = spritesheet.getWidth() / frames;
		System.out.println("loaded Spritesheet");
	} 
	
	public Spritesheet(BufferedImage bi, int frames) {
		BufferedImage sprite = bi;	
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
	
	public JadrImage getFrameAsAsset(int frame) {
		if(spritesheet == null) {
			System.out.println("Spritesheet is null");
			return null;
		}
		if(frame > this.frames) {
			System.out.println("Frame " + frame + " not found! Frame searched: " + frame + " Total Frames: " + this.frames);
			return null;
		}
		System.out.println(frame*tilesize);
		return new JadrImage.GeneralJadrImage(spritesheet.getSubimage(tilesize * frame, 0, tilesize, spritesheet.getHeight()));
	}
	
	public BufferedImage getImage() {
		return spritesheet;
	}
	
}
