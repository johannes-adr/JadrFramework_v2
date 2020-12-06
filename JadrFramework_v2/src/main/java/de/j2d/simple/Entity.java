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
	
	/**
	 * This method will get called on every gametick
	 */
	public abstract void act();
	
	/**
	 * move the object in the direction with its rotation. The speed will be the current object speed
	 */
	public void move() {
		move(speed);
	}
    /**
	 * move the object in the direction with its rotation. 
     * @param speed -> how fast should the object move
     */
	public void move(float speed) {
		Vector2D change = JUtils.getPointFromAngleWithSpeed(rotation, speed);
		x+=change.getX();
		y+=change.getY();
	}
	/**
	 * Setter for object speed
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/**
	 * @return object speed
	 */
	public float getSpeed() {
		return this.speed;
	}
	/**
	 * Set object rotation in degree (0 - 360)
	 * @param degree
	 */
	public void setRotation(float degree) {
		this.rotation = degree;
	}
	/**
	 * 
	 * @return the current object rotation in degrees (0 - 360)
	 */
	public float getRotation() {
		return rotation;
	}

}
