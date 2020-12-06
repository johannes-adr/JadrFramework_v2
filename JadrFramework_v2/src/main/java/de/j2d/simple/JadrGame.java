package de.j2d.simple;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import de.j2d.core.Element;
import de.j2d.core.Game;
import de.j2d.core.GameCam;
import de.j2d.core.QuadTree;
import de.j2d.utils.JadrList;

public class JadrGame extends Game{

	private final ArrayList<Element> GAME_ELEMENTS = new ArrayList<Element>();
	
	private final ArrayList<Element> REMOVING_ELEMENTS = new JadrList<Element>();
	private final ArrayList<Element> ADDING_ELEMENTS = new JadrList<Element>();
	
	private final GameCam cam;
	private QuadTree quadTree;
	
	private int quadTreeMaxObj = 8, quadTreeMaxLevel = 30, worldWith, worldHeight;
	
	public JadrGame(String title, int width, int height) {
		super(title, width, height);
		cam = new GameCam(this);
		worldHeight = height;
		worldWith = width;
		
	}

	@Override
	protected void onUpdate() {
		GAME_ELEMENTS.addAll(ADDING_ELEMENTS);
		GAME_ELEMENTS.removeAll(REMOVING_ELEMENTS);
		buildQuadTree();
		for(Element e : GAME_ELEMENTS)if(e instanceof Entity)((Entity)e).act();
	}
	
	public void addElement(Element e) {
		ADDING_ELEMENTS.add(e);
	}
	
	public QuadTree getCollisionTree() {
		return quadTree;
	}
	
	public void removeElement(Element e) {
		REMOVING_ELEMENTS.add(e);
	}
	@Override
	protected void onStart() {
		buildQuadTree();
	}
	
	private void buildQuadTree() {
		quadTree = QuadTree.build(GAME_ELEMENTS,quadTreeMaxObj, quadTreeMaxLevel, worldWith, worldHeight);
	}

	@Override
	protected void render(Graphics2D g) {
		ArrayList<Element> onScreen = new ArrayList<Element>();
		Element foc = cam.foc;
		if(foc != null) {
			onScreen.addAll(Arrays.asList(quadTree.getElementsAtLocation(foc.getX() - widht/2,foc.getY() - height/2, widht, height)));
		}else {
			onScreen.addAll(Arrays.asList(quadTree.getElementsAtLocation(0, 0, widht, height)));
		}
		if(!cam.render(g, onScreen))for(Element e : onScreen)e.render(g,e.getXRound(), e.getYRound());
	}

}
