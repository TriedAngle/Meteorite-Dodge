package net.strobl.game.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	static BufferedImage image;

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage scale(BufferedImage imageToScale, int width, int height) {
		BufferedImage scaledImage = null;
		if (imageToScale != null) {
			scaledImage = new BufferedImage(width, height, imageToScale.getType());
			Graphics2D graphics2D = scaledImage.createGraphics();
			graphics2D.drawImage(imageToScale, 0, 0, width, height, null);
			graphics2D.dispose();
		}
		return scaledImage;
	}

	public static BufferedImage crop(String path, int x, int y, int width, int height) {
		try {
			image = ImageIO.read(ImageLoader.class.getResource(path));
			image = image.getSubimage(x, y, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
