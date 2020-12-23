package de.j3d.geom;

import de.j3d.Vector3D;

public class Triangle3D{	

	private Vector3D v1;
	private Vector3D v2;
	private Vector3D v3;
	
	public Triangle3D(Vector3D v1, Vector3D v2, Vector3D v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public Vector3D getV1() {
		return v1;
	}

	public Vector3D getV2() {
		return v2;
	}

	public Vector3D getV3() {
		return v3;
	}

	public void setV1(Vector3D v1) {
		this.v1 = v1;
	}

	public void setV2(Vector3D v2) {
		this.v2 = v2;
	}

	public void setV3(Vector3D v3) {
		this.v3 = v3;
	}

}
