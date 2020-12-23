package de.j3d;

import de.j2d.utils.Mesh2D;
import de.j2d.utils.Vector2D;

public class Mesh3D {

	private float x, y, z;
	
	private float scale;
	
	private Vector3D[] vertecies;
	private Mesh3D[] mesh3ds;


	public Mesh3D(Vector3D... v3d) {
		vertecies = v3d;
	}

	public Mesh3D(Mesh3D... mesh3ds) {
		this.mesh3ds = mesh3ds;
	}

	public Mesh3D[] getSubMeshes() {
		return mesh3ds;
	}

	public void scale(float f) {
		scale = f;
		if(mesh3ds != null) {
			for(Mesh3D m : mesh3ds)m.scale(f);
		}
	}

	public void transform(float x, float y, float z) {
		this.x=x;
		this.y=y;
		this.z=z;
		if(mesh3ds != null) {
			for(Mesh3D m : mesh3ds)m.transform(x, y, z);;
		}
	}
	
	public float getDistance() {
		float distSum = 0;
		int vertamount = 0;
		
		if (vertecies != null) {
			for (Vector3D v : vertecies) {
				distSum+=v.getZ();
				vertamount++;
			}
		}
		return distSum/vertamount;
	}

	public Mesh2D project2D() {
		Vector2D[] matrix2D = new Vector2D[vertecies.length];
		for (int i = 0; i < vertecies.length; i++) {
			Vector3D v = vertecies[i];
			float[][] v2D = Math3D.multiplyMatrix(Math3D.ORTHOGRAPHIC_PROJECTION, Math3D.vec3DtoMatrix(v));
			Vector2D vec2D = new Vector2D(v2D[0][0]*scale+x, v2D[1][0]*scale+y);
			matrix2D[i] = vec2D;
		}
		return new Mesh2D(matrix2D);
	}

	public void rotateX(float r) {
		rotate3D(Math3D.rotationMatrixZ3D(r));
	}

	public void rotateY(float r) {
		rotate3D(Math3D.rotationMatrixY3D(r));
	}

	public void rotateZ(float r) {
		rotate3D(Math3D.rotationMatrixX3D(r));
	}

	public void rotate3D(float[][] matrix) {
		if (vertecies != null) {
			for (Vector3D v : vertecies) {
				float[][] vrot = Math3D.multiplyMatrix(matrix, Math3D.vec3DtoMatrix(v));
				v.setX(vrot[0][0]);
				v.setY(vrot[1][0]);
				v.setZ(vrot[2][0]);
			}
		}
		if (mesh3ds != null) {
			for (Mesh3D mesh3d : mesh3ds)
				mesh3d.rotate3D(matrix);
		}
	}

	public Vector3D[] getVertecies() {
		return vertecies;
	}

	public void setVertecies(Vector3D[] vertecies) {
		this.vertecies = vertecies;
	}

	public float getScale() {
		return scale;
	}

}
