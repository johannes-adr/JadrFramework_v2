package de.j2d.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class JadrList<T> extends ArrayList<T>{
	private static final long serialVersionUID = 1L;

	
	@Override
	public Iterator<T> iterator() {
		ArrayList<T> cloned = new ArrayList<T>();
		cloned.addAll(this);
		return cloned.iterator();
	}
	public T getSafe(int index) {
		if(index < this.size()) {
			return this.get(index);
		}else {
			return null;
		}
	}
	
	public boolean removeSafe(int index) {
		if(index <= this.size()) {
			this.remove(index);
			return true;
		}
		return false;
	}
	
	public boolean removeSafe(T t) {
		if(this.contains(t)) {
			this.remove(t);
			return true;
		}
		return false;
	}
	
	@Override
	public JadrList<T> clone() {
		JadrList<T>  cloned = new JadrList<T> ();
		cloned.addAll(this);
		return cloned;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.toArray());
	}
	
}
