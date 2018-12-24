package net.strobl.game.utils;

import net.strobl.game.entities.Bullet;
import net.strobl.game.entities.Meteorite;
import net.strobl.game.entities.Player;

public class CollisionDetection {

	public static boolean testMeteoriteBulletCollision(Meteorite meteor, Bullet bullet) {
		int xB = (int) bullet.getX();
		int yB = (int) bullet.getY();
		int widthB = bullet.getWidth();
		int heightB = bullet.getHeight();
		int deltaWidthB = bullet.getDeltaWidth();

		int xM = (int) meteor.getX();
		int yM = (int) meteor.getY();
		int widthM = meteor.getWidth();
		int heightM = meteor.getHeight();
		
		if (xB + widthB + deltaWidthB < xM || xB > xM + widthM || yB + heightB < yM || yB > yM + heightM) {
			return false;
		}

		return true;
	}

	public static boolean testMeteoriteShipCollision(Meteorite meteor, Player ship) {
		int xS = (int) ship.getX();
		int yS = (int) ship.getY();
		int widthS = ship.getWidth();
		int heightS = ship.getHeight();

		int xM = (int) meteor.getX();
		int yM = (int) meteor.getY();
		int widthM = meteor.getWidth();
		int heightM = meteor.getHeight();

		if (xS + widthS < xM || xS > xM + widthM || yS + heightS < yM || yS > yM + heightM) {
			return false;
		}
		return true;
	}

}
