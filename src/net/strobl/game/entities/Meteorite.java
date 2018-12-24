package net.strobl.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import net.strobl.game.Handler;

public class Meteorite {
	private float x, y;
	private int width, height;
	private int size;
	private float vy;
	private Handler handler;
	private int nmbr;

	public Meteorite(Handler handler, float x, float y, int speed, int size) {
		this.handler = handler;
		width = 20*size;
		height = 20*size;
		vy = speed;
		this.y = y;
		this.x = x;
	}
	
	
	public void tick() {
		y += vy;
		
		nmbr++;
		
		if (nmbr > 1000) {
			nmbr = 0;
		}
	}

	public void render(Graphics g) {
		//g.drawImage(ImageLoader.scale(ImageLoader.loadImage("/pp.png"), width, height), (int)x, (int)y, null);
		g.setColor(Color.ORANGE);
		g.fillRect(((int) x), ((int) y), width, height);
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
	
}
