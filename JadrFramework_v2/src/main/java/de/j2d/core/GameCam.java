package de.j2d.core;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameCam {

	public Element foc;
	
	
	private Game game;
	
	private ArrayList<Render> extraRenderes = new ArrayList<GameCam.Render>();
	public GameCam(Game game) {
		this.game = game;
	}

	public Point getPointOnScreen(Element e) {
		if(foc == null) {
			return new Point(e.getXRound(), e.getYRound());
		}else {
			if(foc.equals(e)) {
				return new Point(game.widht / 2 - foc.getWidthRound() / 2, game.height / 2 - foc.getHeightRound() / 2);
			}else {
				return new Point(Math.round(game.widht / 2 - foc.getX() - foc.height / 2),Math.round(game.height / 2 - foc.getY() - foc.height / 2));
			}
		}
	}
	
	public void renderCustom(Render r) {
		extraRenderes.add(r);
	}
	
	public boolean render(Graphics2D g, List<? extends Element> elements) {
		int rendered = 0;
		if (foc != null) {
			int gjfocX = Math.round(game.widht / 2 - foc.getX() - foc.height / 2);
			int gjfocY = Math.round(game.height / 2 - foc.getY() - foc.height / 2);
			for (Element e : elements) {
				rendered++;
				if (e.equals(foc)) {
					foc.render(g, game.widht / 2 - foc.getWidthRound() / 2, game.height / 2 - foc.getHeightRound() / 2);
					continue;
				}
				e.render(g,gjfocX + e.getXRound(), gjfocY + e.getYRound());
			}
			for(Render extra : extraRenderes) {
				extra.onRender(g, gjfocX, gjfocY);
			}
			return true;
		}else {
			for(Render extra : extraRenderes) {
				extra.onRender(g, 0, 0);
			}
		}
		return false;
	}
	
	public static interface Render {
		public void onRender(Graphics2D g, int x, int y);
	}

}
