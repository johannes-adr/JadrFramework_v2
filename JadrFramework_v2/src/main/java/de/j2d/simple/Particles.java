package de.j2d.simple;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import de.j2d.JUtils;
import de.j2d.utils.Vector2D;

public class Particles {
	private JadrGame jdr;
	public Particles(float x, float y,int minLifeTime, int maxlifeTime, int size, JadrGame zr, int amount, Color c) {
			this.jdr = zr;
			float angle = 360;
			float minusAngle = 360/(float)amount;
			int lamount = amount;
			for(;lamount > 0;lamount--) {
				angle-=minusAngle;
				zr.addElement(new Particle(x, y, size, angle,c, System.currentTimeMillis() +minLifeTime+ JUtils.random().nextInt(maxlifeTime-minLifeTime), zr));
			}
	}
	public static class Particle extends Entity{
		private int size;
		private float angle;
		private Color c;
		private long endLive;
		private JadrGame jdr;
		public Particle(float x, float y, int size, float angle, Color c, long endLive, JadrGame jdr) {
			super(x, y, size, size);
			this.jdr = jdr;
			this.size = size;
			this.angle = angle;
			Random r = JUtils.random();
			if(c != null) {
				this.c = c;
			}else {
				this.c = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
			}
			this.endLive = endLive;
			super.setSpeed(1+r.nextInt(7));
		}

		@Override
		public void act() {
			if(endLive < System.currentTimeMillis()) {
				jdr.removeElement(this);
				return;
			}
			Vector2D change = JUtils.getPointFromAngleWithSpeed(angle, getSpeed());
			x+=change.getX();
			y+=change.getY();
		}
		
		@Override
		public void render(Graphics2D g, int x, int y) {
			g.setColor(c);
			g.fillOval(x, y, size, size);
		}
		
	}
}
