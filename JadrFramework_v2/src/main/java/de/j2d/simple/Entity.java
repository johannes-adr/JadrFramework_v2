package de.j2d.simple;

import de.j2d.JUtils;
import de.j2d.core.Element;
import de.j2d.utils.Vector2D;

public abstract class Entity extends Element{

	
	private float rotation = 0;
	private float speed = 5;
	public Entity(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public abstract void act();
	
	public void move() {
		move(speed);
	}
	
	public void move(float speed) {
		Vector2D change = JUtils.getPointFromAngleWithSpeed(rotation, speed);
		x+=change.getX();
		y+=change.getY();
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void setRotation(float r) {
		this.rotation = r;
	}
	
	public float getRotation() {
		return rotation;
	}

}
