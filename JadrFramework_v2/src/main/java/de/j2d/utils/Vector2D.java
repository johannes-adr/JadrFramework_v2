package de.j2d.utils;

public class Vector2D {
	private float x = 0;
	private float y = 0;
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D() {}
	
	public float getX() {
		return x;
	}
	public int getXint() {
		return (int)x;
	}
	public int getYint() {
		return (int)y;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
}
