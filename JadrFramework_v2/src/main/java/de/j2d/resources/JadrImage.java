package de.j2d.resources;

import java.awt.image.BufferedImage;

public interface JadrImage {

	public BufferedImage getImage();

	public static class GeneralJadrImage extends BufferedImage implements JadrImage {

		public GeneralJadrImage(int arg0, int arg1) {
			super(arg0, arg1, BufferedImage.TYPE_INT_ARGB);
		}
		
		public GeneralJadrImage(BufferedImage i) {
			super(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_ARGB);
			super.getGraphics().drawImage(i, 0, 0, null);
		}

		public BufferedImage getImage() {
			return this;
		}

	}
}
