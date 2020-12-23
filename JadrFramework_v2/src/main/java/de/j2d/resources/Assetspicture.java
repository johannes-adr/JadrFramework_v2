package de.j2d.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Assetspicture {
	private BufferedImage img;

	public Assetspicture(String path) {
		if (path == null) {
			return;
		}
		try {
			this.img = ImageIO.read(new File("assets/" + path)); 

		} catch (IOException e) {
			System.out.println("Error loading Image: assets/" + path);
		} 
	}

	public BufferedImage getImage() {
		return this.img;
	}

	public Assetspicture setSize(int width, int height) {
		if (img != null) {
			Image resultingImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		    img = outputImage;
		}

		return this;
	} 

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	public int getWidth() {
		return img.getWidth();
	}

	public int getHeight() {
		return img.getHeight();
	}
}
