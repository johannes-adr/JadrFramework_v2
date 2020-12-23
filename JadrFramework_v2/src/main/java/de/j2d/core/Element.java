package de.j2d.core;


import java.awt.Graphics2D;

public class Element {
	protected float x, y;
	protected int width, height;

	public Element(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public float getX() {
		return x;
	}
	
	public int getXRound() {
		return Math.round(x);
	}

	public float getY() {
		return y;
	}
	
	public int getYRound() {
		return Math.round(y);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidht(int widht) {
		this.width = widht;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @param other object of type Element
	 * @return true if current object intersects with other element
	 */
	public boolean intersecting(Element other) {
		return(this.x < other.x + other.width &&
				this.x + this.width > other.x &&
		        this.y < other.y + other.height &&
		        this.y + this.height > other.y);
	}

	public void render(Graphics2D g, int x, int y) {g.fillRect(x, y, getWidth(), getHeight());}

}
