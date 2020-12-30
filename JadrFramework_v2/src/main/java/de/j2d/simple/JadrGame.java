package de.j2d.simple;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import de.j2d.core.Element;
import de.j2d.core.Game;
import de.j2d.core.GameCam;
import de.j2d.core.QuadTree;
import de.j2d.core.RenderingLayer;
import de.j2d.resources.AnimatedSprite;
import de.j2d.utils.JadrList;

/**
 * 
 * @author johnr Simple implementation of the Game class
 */
public abstract class JadrGame extends Game {

	private final ArrayList<Element> GAME_ELEMENTS = new ArrayList<Element>();
	private final ArrayList<Element> COLLIDING_ELEMENTS = new ArrayList<Element>();

	private final ArrayList<Element> REMOVING_ELEMENTS = new JadrList<Element>();
	private final ArrayList<Element> ADDING_ELEMENTS = new JadrList<Element>();

	private final GameCam cam;
	private QuadTree quadTree;

	protected int quadTreeMaxObj = 8, quadTreeMaxLevel = 30, worldWith, worldHeight;

	public JadrGame(String title, int width, int height) {
		super(title, width, height);
		cam = new GameCam(this);
		worldHeight = height;
		worldWith = width;
		AnimatedSprite.setScheduler(SCHEDULER);
	}

	@Override
	protected void onUpdate() {
		build();
		for (Element e : GAME_ELEMENTS)
			if (e instanceof Entity)
				((Entity) e).act();
		onPostUpdate();
	}

	@Override
	protected void render(Graphics2D g) {
		onPreRender(g);
		ArrayList<Element> onScreen = new ArrayList<Element>();
		Element foc = cam.foc;
//		if (foc != null) {
//			onScreen.addAll(Arrays.asList(
//					quadTree.getElementsAtLocation(foc.getX() - widht / 2, foc.getY() - height / 2, widht, height)));
//		} else {
//			onScreen.addAll(Arrays.asList(quadTree.getElementsAtLocation(0, 0, widht, height)));
//		}
		if (!cam.render(g, GAME_ELEMENTS))
			for (Element e : GAME_ELEMENTS) {
				if(e.isVisible())e.render(g, e.getXRound(), e.getYRound());
			}
				
		onPostRender(g);
	}

	public GameCam getGameCamera() {
		return cam;
	}

	public void setQuadtreePreferences(int maxObjPerSection, int maxQuadTreeLevel, int worldWidht, int worldHeight) {
		this.quadTreeMaxLevel = maxQuadTreeLevel;
		this.quadTreeMaxObj = maxObjPerSection;
		this.worldHeight = worldHeight;
		this.worldWith = worldWidht;
	}

	/**
	 * @param e Add the given element e to the world (Can get called async)
	 */
	public void addElement(Element e, boolean hasCollision) {
		ADDING_ELEMENTS.add(e);
		if(hasCollision)COLLIDING_ELEMENTS.add(e);
	}

	/**
	 * @return the current game tick QuadTree
	 */
	public QuadTree getCollisionTree() {
		return quadTree;
	}

	/**
	 * @param e remove the given element e from the world (Can get called async)
	 */
	public void removeElement(Element e) {
		REMOVING_ELEMENTS.add(e);
		COLLIDING_ELEMENTS.remove(e);
	}

	/**
	 * Sort all game elements by their rendering layers
	 */
	public void sortElements() {
		HashMap<Integer, ArrayList<Element>> layerMap = new HashMap<Integer, ArrayList<Element>>();
		ArrayList<Element> newElementList = new ArrayList<Element>(GAME_ELEMENTS.size());
		for (Element e : GAME_ELEMENTS) {
			int layer = 0;

			if (e instanceof RenderingLayer)
				layer = ((RenderingLayer) e).getLayer();

			if (!layerMap.containsKey(layer))
				layerMap.put(layer, new ArrayList<Element>());
			layerMap.get(layer).add(e);
		}

		while (layerMap.size() > 0) {
			int smallest = Integer.MAX_VALUE;

			for (Integer key : layerMap.keySet()) {
				if (key < smallest) {
					smallest = key;
				}
			}

			newElementList.addAll(layerMap.get(smallest));
			layerMap.remove(smallest);
		}

		GAME_ELEMENTS.clear();
		GAME_ELEMENTS.addAll(newElementList);
	}

	@Override
	protected void onStart() {
		build();
	}

	private void build() {

		if (ADDING_ELEMENTS.size() > 0 || REMOVING_ELEMENTS.size() > 0) {
			GAME_ELEMENTS.addAll(ADDING_ELEMENTS);
			ADDING_ELEMENTS.clear();
			GAME_ELEMENTS.removeAll(REMOVING_ELEMENTS);
			REMOVING_ELEMENTS.clear();
			sortElements();
		}
		quadTree = QuadTree.build(COLLIDING_ELEMENTS, quadTreeMaxObj, quadTreeMaxLevel, worldWith, worldHeight);
	}

	protected abstract void onPostUpdate();

	protected abstract void onPostRender(Graphics2D g);

	protected abstract void onPreRender(Graphics2D g);

}
