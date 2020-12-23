package de.j3d;

public class Vector3D {
	private float x=0,y=0,z=0;
	
	public Vector3D() {}
	
	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}
	
	public int getXint() {
		return (int)x;
	}

	public float getY() {
		return y;
	}
	
	public int getYint() {
		return (int)y;
	}

	public float getZ() {
		return z;
	}
	
	public int getZint() {
		return (int)z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
}
