package net.strobl.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import net.strobl.game.Handler;

public class Bullet {

	private float x, y;
	private int width, height;
	private float vy;
	private int deltaWidth;
	private Handler handler;

	public Bullet(Handler handler, float x, float y) {
		this.handler = handler;
		width = 5;
		deltaWidth = 30;
		height = 15;
		vy = 4;
		this.y = y;
		this.x = x;
	}

	public void tick() {
		y -= vy;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(((int) x), ((int) y), width, height);
		g.fillRect(((int) x) + deltaWidth, ((int) y), width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public int getDeltaWidth() {
		return deltaWidth;
	}

}
