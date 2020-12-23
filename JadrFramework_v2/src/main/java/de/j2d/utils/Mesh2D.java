package de.j2d.utils;

public class Mesh2D {
	
	private Vector2D[] vertecies;
	private double rotation = 0;
	
	
	public Mesh2D(Vector2D... v3d) {
		vertecies = v3d;
	}
	
	
	
	
	public void scale(float f) {
		for (Vector2D v : vertecies) {
			v.setX(v.getX()*f);
			v.setY(v.getY()*f);
		}
	}
	
	public float[] getAllX() {
		float[] f = new float[vertecies.length];
		for(int i = 0; i < vertecies.length; i++) {
			f[i] = vertecies[i].getX();
		}
		return f;
	}
	
	public int[] getAllXint() {
		int[] f = new int[vertecies.length];
		for(int i = 0; i < vertecies.length; i++) {
			f[i] = vertecies[i].getXint();
		}
		return f;
	}

	public float[] getAllY() {
		float[] f = new float[vertecies.length];
		for(int i = 0; i < vertecies.length; i++) {
			f[i] = vertecies[i].getY();
		}
		return f;
	}
	
	public int[] getAllYint() {
		int[] f = new int[vertecies.length];
		for(int i = 0; i < vertecies.length; i++) {
			f[i] = vertecies[i].getYint();
		}
		return f;
	}
	

	
	public void transform(float x, float y, float z) {
		for (Vector2D v : vertecies) {
			v.setX(v.getX()+x);
			v.setY(v.getY()+y);
		}
	}
	

	
	public void rotateY(float r) {
		for (Vector2D v : vertecies) {
			v.setX(v.getX()*r);
			v.setY(v.getY()*r);
		}
	}
	



	public Vector2D[] getVertecies() {
		return vertecies;
	}

	public void setVertecies(Vector2D[] vertecies) {
		this.vertecies = vertecies;
	}
	
	
}
