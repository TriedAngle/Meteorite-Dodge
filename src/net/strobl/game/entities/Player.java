package net.strobl.game.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.strobl.game.Handler;
import net.strobl.game.managers.KeyManager;
import net.strobl.game.utils.ImageLoader;

public class Player {

	private float x, y;
	private int width = 40, height = 30;
	private float vx, vy;
	private float ax;
	public static int score = 0;
	Handler handler;
	private static boolean alive = true;
	public int hp = 3;
	private BufferedImage ship;

	public static Font pixelfont;

	public Player(Handler handler) {
		this.handler = handler;
		x = Handler.getWidth() - (Handler.getWidth() + width) / 2;
		y = Handler.getHeight() - Handler.getWidth() / 5;
		vx = 8;
		vy = 2;
		
		ship = ImageLoader.loadImage("/SpaceShip.png");
	}

	public void tick() {
		move();
	}

	public void move() {
		Handler.getKeyManager();

		if (alive) {

			if (KeyManager.left) {
				x -= vx;
			}

			if (KeyManager.right) {
				x += vx;
			}

			if (KeyManager.up) {
				y -= 2 * vy;
			}

			if (KeyManager.down) {
				y += 3 * vy;
			}

			if (x + width > Handler.getWidth()) {
				x += -vx;
			}

			if (x < 0) {
				x += vx;
			}

			if (y + height > Handler.getHeight()) {
				y += -3 * vy;
			}

			if (y < Handler.getWidth() - Handler.getWidth() / 5) {
				y += 2 * vy;
			}
			
			if (hp == 0) {
				alive = false;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(ship, (int) x, (int) y, null);
		//g.fillRect((int) x, (int) y, width, height);

	}

	// GETTERS AND SETTERS

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean getAlive() {
		return alive;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void reduceHP(int amount) {
		hp -= amount;
	}
	
	public void setHP(int amount) {
		hp = amount;
	}
}
