package net.strobl.game.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Timer;

import net.strobl.game.Handler;

public class Background {
	
	private Handler handler;
	private Timer timer;
	
	private int x, y1, y2, width, height1;
	
	private BufferedImage img1, img2;
	
	private Image image;
	
	public Background(Handler handler) {
		this.handler = handler;
		width = Handler.getWidth();
		height1 = Handler.getHeight();
		img1 = ImageLoader.loadImage("/nebula.png");
		img2 = ImageLoader.loadImage("/nebula.png");
		y2 = -Handler.getHeight();
		
	}
	
	public void tick() {
		y1 += 2;
		y2 += 2;
		
		if(y1 >= Handler.getHeight()) {
			y1 = 0;
		}
		if(y2 >= 0) {
			y2 = 0 - Handler.getHeight();
		}
	}
	
	
	public void render(Graphics g) {
		g.drawImage(img1, x, y1, width, height1, null);
		g.drawImage(img2, x, y2, width, height1, null);
	}
}
