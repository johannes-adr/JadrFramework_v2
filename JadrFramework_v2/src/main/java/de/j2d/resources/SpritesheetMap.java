package de.j2d.resources;

import java.awt.image.BufferedImage;

public class SpritesheetMap{

	private int rows;
	private int colls;
	private int xSize;
	private int ySize;
	
	private Assetspicture ap;
	
	public SpritesheetMap(Assetspicture ap, int colls, int rows) {
		this.rows = rows;
		this.ap = ap;
		this.colls = colls;
		
		xSize = ap.getImage().getWidth() / colls;
		ySize = ap.getImage().getHeight() / rows;
	}
	
	public Spritesheet[] asSpriteSheetArray() {
		Spritesheet[] sss = new Spritesheet[rows];
		for(int y = 0;y <= rows;y++) {
			BufferedImage strip = ap.getImage().getSubimage(0, y*ySize, ap.getImage().getWidth(), ySize);
			sss[y] = new Spritesheet(strip, colls);
		}
		
		return sss;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColls() {
		return colls;
	}
	

	public BufferedImage getFrame(int coll, int row) {
		if(coll > getColls())throw new IllegalArgumentException("maxcolls of " + ap + " ");
		return ap.getImage().getSubimage(coll*xSize, ySize, xSize, ySize);
	}
	
	

}
