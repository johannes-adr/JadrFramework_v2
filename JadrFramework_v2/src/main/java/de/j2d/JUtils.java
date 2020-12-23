package de.j2d;

import java.util.Random;

import de.j2d.core.Element;
import de.j2d.utils.Vector2D;

public class JUtils {
	private static Random r = new Random();

	
	public static class Trigonometry{
		public static Vector2D getPointFromAngleWithSpeed(float degree, float speed) {
			float x = (float) Math.cos(Math.toRadians(degree));
			float y = (float) Math.sin(Math.toRadians(degree));

			x += x * speed;
			y += y * speed;
			return new Vector2D(x, y);
		}

		public static Vector2D getPointFromAngleWithSpeedWithRadians(float radians, float speed) {
			float x = (float) Math.cos(radians);
			float y = (float) Math.sin(radians);
			x += x * speed;
			y += y * speed;
			return new Vector2D(x, y);
		}

		public static float getAngleFromTwoPoints(float x1, float y1, float x2, float y2) {
			float r = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));
			if(r < 0) {
				r = 360 + r;
			}
			return r;
		}
		

		public static double getDistanceFromTwoPoints(double vhit, double vhit2, double x, double y) {
			double xAdd = vhit - x;
			double yAdd = vhit2 - y;
			return Math.sqrt(xAdd * xAdd + yAdd * yAdd);
		}
		
	}
	/**
	 * @param gForce -> final float gravity of object
	 * @param m -> mass of object
	 * @param r -> distance between two objects
	 * @param a -> speed
	 */
	public static class Physics{
		public static enum gForce{
			EARTH(9.807f),
			MARS(3.711f),
			MOON(1.62f),
			JUPITER(24.79f);
			private float d;
			gForce(float d) {
				this.d = d;
			}
			public float getGravity() {
				return d;
			}
		}
		public static float getForce(float m1, float m2, float r, float gForce) {
			return gForce*(m1*m2/r);
		}
		public static float getForce(float m1, float m2, float r, gForce gf) {
			return gf.getGravity()*(m1*2/r);
		}
		
		public static float getForce(float m, float a) {
			return m*a;
		}
	}
	
	public static int getRandomNumber(int min, int max) {
		int randomNum = r.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static Random random() {
		return r;
	}
	
	public static int toPositive(int i) {
		return i>0?i:i*-1;
	}
	public static float toPositive(float i) {
		return i>0?i:i*-1;
	}
	public static double toPositive(double i) {
		return i>0?i:i*-1;
	}
	public static long toPositive(long i) {
		return i>0?i:i*-1;
	}
}
