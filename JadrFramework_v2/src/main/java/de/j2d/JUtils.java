package de.j2d;

import java.awt.Point;
import java.security.SecureRandom;
import java.util.Random;

import de.j2d.utils.Vector2D;

public class JUtils {
	private static Random r = new Random();

	public static Vector2D getPointFromAngleWithSpeed(float degree, float speed) {
		double x = Math.cos(Math.toRadians(degree));
		double y = Math.sin(Math.toRadians(degree));

		x += x * speed;
		y += y * speed;
		return new Vector2D(x, y);
	}

	public static Vector2D getPointFromAngleWithSpeedWithRadians(float radians, float speed) {
		double x = Math.cos(radians);
		double y = Math.sin(radians);

		x += x * speed;
		y += y * speed;
		return new Vector2D(x, y);
	}

	public static float getAngleFromTwoPoints(float x1, float y1, float x2, float y2) {
		return (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));
	}

	public static double getDistanceFromTwoPoints(float x1, float y1, float x2, float y2) {
		float xAdd = x1 - x2;
		float yAdd = y1 - y2;
		return Math.sqrt(xAdd * xAdd + yAdd * yAdd);
	}

	public static int getRandomNumber(int min, int max) {
		int randomNum = r.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static Random random() {
		return r;
	}
}
