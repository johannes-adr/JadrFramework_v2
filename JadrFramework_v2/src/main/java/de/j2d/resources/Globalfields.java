package de.j2d.resources;

import java.awt.Color;

public class Globalfields {
	public static enum FLAT_COLORS{
		DARK_GREEN("#27ae60"),
		EMERALD("#2ecc71"),
		ORANGE("#f39c12"),
		DARK_GRAY("#34495e"),
		DARK_GRAY2("#2c3e50"),
		LIGHT_GRAY("#95a5a6"),
		SILVER("#bdc3c7"),
		BLUE("#3498db"),
		RED("#e74c3c"),
		DARK_RED("#c0392b");
		private Color c;
		private FLAT_COLORS(String hex) {
			c = Color.decode(hex);
		}
		public Color getColor() {
			return c;
		}
	}
}
