package de.j2d.core;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {	
	public static enum node {
		NORTH_LEFT(0), NORTH_RIGHT(1), SOUTH_LEFT(2), SOUTH_RIGHT(3);
		int value;
		node(int v) {
			value = v;
		}
	}
	private static int maxLevel = 1;
	private int maxObj, x, y, w, h;
	private int level;
	private boolean hasSubNode = false;
	private ArrayList<Element> elements;
	private ArrayList<Element> ignoringElements;

	private QuadTree[] subNodes;

	private QuadTree(int maxObj, int x, int y, int w, int h, int level) {
		elements = new ArrayList<Element>(maxObj);
		this.maxObj = maxObj;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.level = level;
	}
	
	public void ignoreElement(Element e) {
		if(ignoringElements == null) {
			ignoringElements = new ArrayList<Element>();
		}
		ignoringElements.add(e);
	}
	
	/**
	 * @param elements -> List of elements which get inserted in the quad tree
	 * @param maxObj -> How many elements should get in an area, until it get divided into four sub sections (8 is a good value)
	 * @param maxxLevel -> Which should the maximum depth level of the quad tree (30 is a good value)
	 * @param w -> Width of the root area
	 * @param h -> Height of the root area
	 * @return built quad tree
	 */
	public static QuadTree build(List<? extends Element> elements, int maxObj,int maxxLevel, int w, int h) {
		QuadTree qt = new QuadTree(maxObj, 0,0, w, h, 0);
		maxLevel = maxxLevel;
		for (Element qte : elements) {
			qt.insert(qte);
		}
		return qt;
	}
	/**
	 * !IMPORTANT! This method will only return the elements in the current area of its x and y value! 
	 * The height and the width are not taken into account!
	 * @param Element the tree will search for
	 * @return Array of all elements in the given element area
	 */
	@Deprecated
	public Element[] getElementNeighbours(Element qte) {
		if (hasSubNode) {
			return subNodes[getElementSection(qte).value].getElementNeighbours(qte);
		} else {
			return elements.toArray(new Element[elements.size()]);
		}
	}
	/**
	 * @param Element the tree will search for
	 * @return Array of all elements the given element could intersect
	 */
	public Element[] getElementsAtLocation(Element e) {
		return getElementsAtLocation(e.x, e.y, e.width, e.height);
	}
	/**
	 * @param Element the tree will search for
	 * @return Array of all elements the given element could intersect
	 */
	public Element[] getElementsAtLocation(Rectangle e) {
		return getElementsAtLocation(e.x, e.y, e.width, e.height);
	}
	/**
	 * @param Element the tree will search for
	 * @return Array of all elements the given element could intersect
	 */
	public Element[] getElementsAtLocation(float x, float y, float w, float h) {
		ArrayList<Element> es = new ArrayList<Element>(50);
		addElementsfromSubLocation(x, y, w, h, es);
		if(ignoringElements!=null) {
			es.removeAll(ignoringElements);
		}
		return es.toArray(new Element[es.size()]);
	}
	
    private void addElementsfromSubLocation(float x, float y, float w, float h, ArrayList<Element> e) {
		if(intersecting(x, y, w, h)) {
			if(!hasSubNode) {
				e.addAll(elements);
			}else {
				for(QuadTree subNode : subNodes)subNode.addElementsfromSubLocation(x, y, w, h, e);
			}
		}
	}

	private node getElementSection(Element e) {
		if (e.getX() < x + w / 2) {
			if (e.getY() < y + h / 2) {
				return node.NORTH_LEFT;
			} else {
				return node.SOUTH_LEFT;
			}
		} else {
			if (e.getY() < y + h / 2) {
				return node.NORTH_RIGHT;
			} else {
				return node.SOUTH_RIGHT;
			}
		}
	}

	
	/**
	 * This method will insert the given Element e in an built quad tree
	 * @param e
	 */
	public void insert(Element e) {
		if (hasSubNode) {
			insertInSubNode(e);
			return;
		}
		if (elements.size() < maxObj || level >= maxLevel) {
			elements.add(e);
		} else {
			hasSubNode = true;
			genSubNodeAndFill();
			insertInSubNode(e);
			for (Element qte : elements) {
				insertInSubNode(qte);
			}
			elements.clear();
			elements = null;
		}
	}

	private void genSubNodeAndFill() {
		QuadTree northLeft = new QuadTree(maxObj, x, y, w / 2, h / 2, level+1);
		QuadTree northRight = new QuadTree(maxObj, x + w / 2, y, w / 2, h / 2, level+1);
		QuadTree southLeft = new QuadTree(maxObj, x, y + h / 2, w / 2, h / 2, level+1);
		QuadTree southRight = new QuadTree(maxObj, x + w / 2, y + h / 2, w / 2, h / 2, level+1);

		subNodes = new QuadTree[4];
		subNodes[node.NORTH_LEFT.value] = northLeft;
		subNodes[node.NORTH_RIGHT.value] = northRight;
		subNodes[node.SOUTH_LEFT.value] = southLeft;
		subNodes[node.SOUTH_RIGHT.value] = southRight;
	}

	private void insertInSubNode(Element e) {
		subNodes[getElementSection(e).value].insert(e);
	}
	
	private boolean intersecting(float x, float y, float width, float height) {
			return(this.x < x + width &&
					this.x + this.w > x &&
			        this.y < y + height &&
			        this.y + this.h > y);
	}

	@Deprecated
	public void render(Graphics2D g, Game qg) {
		qg.renderutils.renderRectangleBorder(g, x, y, w, h, 0.1f);
		if (elements == null) {
			for (QuadTree qt : subNodes)
				qt.render(g, qg);
		}

	}
	@Deprecated
	public void renderRelative(Graphics2D g, Game qg, int rX, int rY) {
		qg.renderutils.renderRectangleBorder(g, x+rX, y+rY, w, h, 0.1f);
		if (elements == null) {
			for (QuadTree qt : subNodes)
			qt.renderRelative(g, qg,rX,rY);
		}

	}
}
