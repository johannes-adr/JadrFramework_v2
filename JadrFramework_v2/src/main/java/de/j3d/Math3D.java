package de.j3d;

import java.util.Arrays;

public class Math3D{

		
	public static float[][] rotationMatrixX3D(double r) {
		return new float[][] {
			{1,0,0},
			{0,(float) Math.cos(r), (float) -Math.sin(r)},
			{0,(float) Math.sin(r), (float) Math.cos(r)}
			
		};
	}
	
	public static float[][] rotationMatrixY3D(double r) {
		return new float[][] {
			{(float) Math.cos(r),0,(float) Math.sin(r)},
			{0,1,0},
			{(float) -Math.sin(r), 0, (float) Math.cos(r)}			
		};
	}
	
	public static float[][] rotationMatrixZ3D(double r) {
		return new float[][] {
			{(float) Math.cos(r), (float) -Math.sin(r), 0},
			{(float) Math.sin(r), (float) Math.cos(r), 0},
			{0,0,1}
		};
	}
	
	
	public static final float[][] ORTHOGRAPHIC_PROJECTION = {
			{1,0,0},
			{0,1,0},
	};
	
	public static Vector3D matixToVec3D(float[][] v) {
		return new Vector3D(v[0][0], v[1][0], v[2][0]);
	}
	
	public static float[][] vec3DtoMatrix(Vector3D v) {
		return new float[][] {
			{v.getX()},
			{v.getY()},
			{v.getZ()}	
		};
	}
	public static String printMatix(float[][] v) {
		StringBuilder m = new StringBuilder();
		m.append("Matrix: rows: " + v.length + " cols: " + v[0].length);
		m.append("{\n");
		for (float[] fs : v) {
			m.append("[");
			m.append(Arrays.toString(fs));
			m.append("]\n");
		}
		m.append("}");
		return m.toString();
	}
	
	public static float[][] multiplyMatrix(float[][] a, float[][] b) {
		int rowsA = a.length;
		int colsA = a[0].length;
		
		int rowsB = b.length;
		int colsB = b[0].length;	
		
		if (colsA != rowsB) {
			System.err.println("Collums of a have to match rows of b");
			return null;
		}
		
		float result[][] = new float[rowsA][colsB];
		
		for(int r = 0; r < rowsA; r++) {
			for(int c = 0; c < colsB; c++) {			
				float sum = 0;
				for(int i = 0; i < rowsB; i++) {
					float aval = a[r][i];
					float bval = b[i][c];
					sum+=aval*bval;
				}
				result[r][c] = sum;
				
			}
		}
		return result;
	}
	
	
}
